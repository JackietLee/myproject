package org.handsome.jay.framework;

import org.handsome.jay.service.JayService;
import org.handsome.jay.service.JayServiceImpl;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.ServerSocket;
import java.net.Socket;

public class JayRpcFramework {
    public static void export(Object service, int port) throws Exception {
        ServerSocket server = new ServerSocket(port);
        while (true) {
            Socket socket = server.accept();
            new Thread(new Runnable() {

                @Override
                public void run() {
                    try {
                        ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
                        String serviceName = input.readUTF();
                        String methodName = input.readUTF(); //读取方法名
                        Class<?>[] parameterTypes = (Class<?>[]) input.readObject(); //参数类型
                        Object[] arguments = (Object[]) input.readObject(); //参数
                        Method method = service.getClass().getMethod(methodName, parameterTypes);  //找到方法
                        Object result = method.invoke(service, arguments); //调用方法
                        // 返回结果
                        ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                        output.writeObject(result);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    } catch (NoSuchMethodException e) {
                        throw new RuntimeException(e);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    } catch (InvocationTargetException e) {
                        throw new RuntimeException(e);
                    }
                }

            }).start();
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T refer(Class<T> interfaceClass, String host, int port) throws Exception {
        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class<?>[]{interfaceClass},
                new InvocationHandler() {
                    public Object invoke(Object proxy, Method method, Object[] arguments) throws Throwable {
                        Socket socket = new Socket(host, port);  //指定 provider 的 ip 和端口
                        ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                        output.writeUTF(interfaceClass.getName());
                        output.writeUTF(method.getName());  //传方法名
                        output.writeObject(method.getParameterTypes());  //传参数类型
                        output.writeObject(arguments);  //传参数值
                        ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
                        Object result = input.readObject();  //读取结果
                        return result;
                    }
                });
    }


}
