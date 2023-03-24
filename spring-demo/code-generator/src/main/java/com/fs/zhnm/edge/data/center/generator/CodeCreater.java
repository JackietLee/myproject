/*
 * Copyright (c) 2021-2030 丰尚智慧农牧科技有限公司
 * 不能修改和删除上面的版权声明
 * 此代码属于丰尚智慧农牧科技有限公司编写，在未经允许的情况下不得传播复制
 */
package com.fs.zhnm.edge.data.center.generator;

import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 代码生成
 *
 * @author Liam
 * @date 2021-08-11 09:50:08
 */
public class CodeCreater {
    private Connection conection;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String baseFields = "tid,state,create_date,update_date";

    public void createCode() throws Exception {
        try {
            String tables = ConfigUtils.getConfig().getTableNames();
            String[] tableNames = tables.split(",");

            String poPackage = ConfigUtils.getConfig().getPoPackage();
            poPackage = StringUtils.replace(poPackage, ".", "/");
            String poClassFolder = ConfigUtils.getConfig().getPoSourceFolder() + "/" + poPackage + "/entity/";
            checkFilePath(poClassFolder);

            String daoClassFolder = ConfigUtils.getConfig().getPoSourceFolder() + "/" + poPackage + "/dao/";
            checkFilePath(daoClassFolder);

            String boClassFolder = ConfigUtils.getConfig().getPoSourceFolder() + "/" + poPackage + "/service/impl/";
            checkFilePath(boClassFolder);

            String boInterfaceFolder = ConfigUtils.getConfig().getPoSourceFolder() + "/" + poPackage + "/service/";
            checkFilePath(boClassFolder);

            for (String tableName : tableNames) {
                System.out.println("正在生成：" + tableName);
                String poClassName = getPoName(tableName);
                System.out.println("PO：" + poClassName + ".java");
                String poClassFilePath = poClassFolder + poClassName + ".java";
                createPoClass(poClassFilePath, poClassName, tableName);

                String daoClassFilePath = daoClassFolder + poClassName + "Dao.java";
                System.out.println("DAO：" + poClassName + "Dao" + ".java");
                createDaoClass(daoClassFilePath, poClassName + "Dao", poClassName);

                String boClassFilePath = boClassFolder + poClassName + "ServiceImpl.java";
                System.out.println("BO：" + poClassName + "ServiceImpl.java");
                createBoClass(boClassFilePath, poClassName + "Service", poClassName + "Dao", poClassName);

                String boInterfaceFilePath = boInterfaceFolder + poClassName + "Service.java";
                System.out.println("BO：" + poClassName + "Service.java");
                createBoInterface(boInterfaceFilePath, poClassName + "Service",poClassName);
            }

            System.out.println("代码生成完成，请到各自模块下刷新查看！");
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e);
        }
    }

    private void createBoClass(String poClassPath, String className, String daoClassName, String poClassName)
            throws Exception {
        PrintWriter write = null;
        try {
            write = getPrintWriter(poClassPath);
            writeFileCommon(write);
            write.write("package ");
            write.write(ConfigUtils.getConfig().getPoPackage() + ".service");
            write.write(";\n");
            write.write("import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;\n");
            write.write("import " + ConfigUtils.getConfig().getPoPackage() + ".dao." + daoClassName + ";\n");
            write.write("import " + ConfigUtils.getConfig().getPoPackage() + ".entity." + poClassName + ";\n");
            write.write("import " + ConfigUtils.getConfig().getPoPackage() + ".service." + className + ";\n");
            write.write("import lombok.extern.slf4j.Slf4j;\n");
            write.write("import org.springframework.stereotype.Service;\n");
            writeTypeCommon(write);
            write.write("@Slf4j\n");
            write.write("@Service\n");
            write.write("public class " + className + "Impl extends ServiceImpl<" + daoClassName + ", " + poClassName + ">"+ " implements "+ className + "{\n");
            writeClassEnd(write);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (write != null)
                write.close();
        }
    }

    private void createBoInterface(String poClassPath, String className, String poClassName)
            throws Exception {
        PrintWriter write = null;
        try {
            write = getPrintWriter(poClassPath);
            writeFileCommon(write);
            write.write("package ");
            write.write(ConfigUtils.getConfig().getPoPackage() + ".service");
            write.write(";\n");
            write.write("import com.baomidou.mybatisplus.extension.service.IService;\n");
            write.write("import " + ConfigUtils.getConfig().getPoPackage() + ".entity." + poClassName + ";\n");
            writeTypeCommon(write);
            write.write("public interface " + className  + " extends IService<"+ poClassName +"> {\n");
            writeClassEnd(write);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (write != null)
                write.close();
        }
    }

    private void createDaoImplClass(String poClassPath, String className, String daoClassName, String poClassName)
            throws Exception {
        PrintWriter write = null;
        try {
            write = getPrintWriter(poClassPath);
            writeFileCommon(write);
            write.write("package ");
            write.write(ConfigUtils.getConfig().getDaoPackage() + ".impl");
            write.write(";\n");
            write.write("import org.springframework.stereotype.Repository;\n");
            write.write("import " + ConfigUtils.getConfig().getPoPackage() + "." + poClassName + ";\n");
            write.write("import " + ConfigUtils.getConfig().getPoPackage() + ".dao." + daoClassName + ";\n");
            write.write("import com.sip.common.dao.support.BaseDaoSupport;\n");
            writeTypeCommon(write);
            write.write("@Repository(\"" + lowerFirst(className) + "\")\n");
            write.write("public class " + className + " extends BaseDaoSupport<" + poClassName + "> implements "
                    + daoClassName + "{\n");
            write.write("\n");
            writeClassEnd(write);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (write != null)
                write.close();
        }
    }

    private void createDaoClass(String poClassPath, String className, String poClassName) throws Exception {
        PrintWriter write = null;
        try {
            write = getPrintWriter(poClassPath);
            writeFileCommon(write);
            write.write("package ");
            write.write(ConfigUtils.getConfig().getPoPackage() + ".dao");
            write.write(";\n");
            write.write("import com.baomidou.mybatisplus.core.mapper.BaseMapper;\n");
            write.write("import " + ConfigUtils.getConfig().getPoPackage() + ".entity." + poClassName + ";\n");
            write.write("import org.apache.ibatis.annotations.Mapper;\n");
            writeTypeCommon(write);
            write.write("@Mapper\n");
            write.write("public interface " + className + " extends BaseMapper<" + poClassName + "> {\n");
            write.write("\n");
            writeClassEnd(write);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (write != null)
                write.close();
        }
    }

    private void createPoClass(String poClassPath, String poClassName, String tableName) throws Exception {
        PrintWriter write = null;
        try {
            write = getPrintWriter(poClassPath);
            writeFileCommon(write);
            writePackageAndImport(write);
            writeTypeCommon(write);
            writeClassStart(write, poClassName, tableName);
            Map<String, String> fieldMap = new HashMap<>();
            writeClassFields(write, tableName, fieldMap);
            writeClassEnd(write);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (write != null)
                write.close();
        }
    }

    private void writeClassEnd(PrintWriter writer) {
        writer.write("\n}");
    }

    private void writeGeterAndSeter(PrintWriter writer, Map<String, String> fieldMap) {
        for (Map.Entry<String, String> entry : fieldMap.entrySet()) {
            String methodName = getMethodName((String) entry.getKey(), "get");
            writer.write("\n\n    public " + (String) entry.getValue() + " " + methodName + "() {\n        return "
                    + (String) entry.getKey() + ";\n    }");
            methodName = getMethodName((String) entry.getKey(), "set");
            writer.write("\n\n    public void " + methodName + "(" + (String) entry.getValue() + " "
                    + (String) entry.getKey() + " ) {\n        this." + (String) entry.getKey() + " = "
                    + (String) entry.getKey() + ";\n    }");
        }

        fieldMap.clear();
    }

    private PrintWriter getPrintWriter(String path) throws UnsupportedEncodingException, FileNotFoundException {
        OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(path), "UTF-8");
        PrintWriter writer2 = new PrintWriter(writer);
        return writer2;
    }

    private void writeClassFields(PrintWriter writer, String tableName, Map<String, String> fieldMap) throws Exception {
        writer.write("    private static final long serialVersionUID = ");
        writer.write(String.valueOf(Math.round(Math.random() * 10000000.0D * 556.0D)));
        writer.write("L;");
        String sql = "select * from " + tableName + " where 1 <> 1";
        PreparedStatement p = this.conection.prepareStatement(sql);
        ResultSetMetaData md = p.getMetaData();
        int count = md.getColumnCount();
        Map<String, String> remarks = getRemarks(tableName);
        String name = null;
        String remark = null;
        String dataType = null;
        for (int i = 1; i <= count; i++) {
            name = md.getColumnName(i);
//            if (baseFields.contains(name)) {
//                continue;
//            }
            remark = (String) remarks.get(name);
            dataType = getColumnType(md.getColumnType(i));
            writer.write("\n\n    /**\n     * " + remark + "\n     */");

            name = getFiledName(name);

            writer.write("\n    private " + dataType + " " + name + ";");

            fieldMap.put(name, dataType);
        }
    }


    //首字母转小写
    private static String toLowerCaseFirstOne(String s) {
        if (Character.isLowerCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
    }


    private static String getColumnType(int type) {
        String dataType = null;
        switch (type) {
            case 91:
            case 93:
                dataType = "java.util.Date";
                break;
            case 1:
            case -1:
            case 12:
                dataType = "java.lang.String";
                break;
            case -6:
            case 4:
            case 5:
                dataType = "java.lang.Integer";
                break;
            case -5:
                dataType = "java.lang.Long";
                break;
            case 3:
                dataType = "java.math.BigDecimal";
                break;
            case 8:
                dataType = "java.lang.Double";
                break;
            case 6:
            case 7:
                dataType = "java.lang.Float";
                break;
            case -7:
                dataType = "java.lang.Boolean";
                break;
        }

        return dataType;
    }

    private void writeClassStart(PrintWriter writer, String poClassName, String tableName) {
        writer.write("@Data\n");
        writer.write("@TableName(\"");
        writer.write(tableName);
        writer.write("\")\n");
        writer.write("public class ");
        writer.write(poClassName);
        writer.write(" extends BaseEntity {\n");
    }

    private void writeTypeCommon(PrintWriter writer) {
        writer.write("/**\n");
        writer.write(" * \n");
        writer.write(" * @author "+ConfigUtils.getConfig().getAuthor()+"\n");
        writer.write(" * @date " + this.sdf.format(Calendar.getInstance().getTime()) + "\n");
        writer.write(" */\n");
    }

    private void writePackageAndImport(PrintWriter writer) {
        writer.write("package ");
        writer.write(ConfigUtils.getConfig().getPoPackage() + ".entity");
        writer.write(";\n");
        writer.write("import com.baomidou.mybatisplus.annotation.TableName;\n");
        writer.write("import com.fs.zhnm.edge.cloud.parent.common.base.entity.BaseEntity;\n");
        writer.write("import lombok.Data;\n");
    }

    private void writeFileCommon(PrintWriter write) {
        write.write("/*\n");
        write.write(" * Copyright (c) 2021-2030 丰尚智慧农牧科技有限公司\n");
        write.write(" * 不能修改和删除上面的版权声明\n");
        write.write(" * 此代码属于丰尚智慧农牧科技有限公司编写，在未经允许的情况下不得传播复制\n");
        write.write(" */\n");
    }

    private void checkFilePath(String path) {
        File file = new File(path);
        if (!file.exists())
            file.mkdirs();
    }

    public void initConnection() throws Exception {
        Class c = Class.forName(ConfigUtils.getConfig().getDriverName());
        Driver driver = (Driver) c.getDeclaredConstructor().newInstance();
        DriverManager.registerDriver(driver);
        Properties props = new Properties();
        props.put("remarksReporting", "true");
        props.put("user", ConfigUtils.getConfig().getDbUserName());
        props.put("password", ConfigUtils.getConfig().getDbPassword());
        this.conection = DriverManager.getConnection(ConfigUtils.getConfig().getDbLink(), props);
    }

    private static String getMethodName(String field, String getOrSet) {
        char first = field.charAt(0);
        String prefix = String.valueOf(first).toUpperCase();
        String subfix = field.substring(1);
        return getOrSet + prefix + subfix;
    }

    private static String getPoName(String tableName) {
        String[] temps = tableName.split("_");
        StringBuilder sb = new StringBuilder();
        String[] arrayOfString1;
        int j = (arrayOfString1 = temps).length;
        for (int i = 0; i < j; i++) {
            String temp = arrayOfString1[i];
            sb.append(upperFirstChart(temp));
        }
        return sb.toString();
    }

    private static String getFiledName(String fieldName) {
        String[] temps = fieldName.split("_");
        StringBuilder sb = new StringBuilder();
        String[] arrayOfString1;
        int j = (arrayOfString1 = temps).length;
        for (int i = 0; i < j; i++) {
            String temp = arrayOfString1[i];
            if (i == 0) {
                sb.append(temp);
                continue;
            }
            sb.append(upperFirstChart(temp));
        }
        return sb.toString();
    }

    private static String upperFirstChart(String source) {
        char first = source.charAt(0);
        String firstStr = String.valueOf(first).toUpperCase();
        return firstStr + source.substring(1, source.length());
    }

    private Map<String, String> getRemarks(String table) throws SQLException {
        DatabaseMetaData meta = this.conection.getMetaData();

        ResultSet prs = meta.getPrimaryKeys(null, ConfigUtils.getConfig().getDatabase(), table);
        Map<String, String> remarkMap = new HashMap<>();
        if (prs.next()) {
            remarkMap.put("$PRIMARY_KEY$", prs.getString("COLUMN_NAME"));
        }
        ResultSet rs = meta.getColumns(null, ConfigUtils.getConfig().getDatabase(), table, null);
        while (rs.next()) {
            String name = rs.getString("COLUMN_NAME");
            remarkMap.put(name, rs.getString("REMARKS"));
        }
        prs.close();
        rs.close();
        return remarkMap;
    }

    private static String lowerFirst(String source) {
        char first = source.charAt(0);
        String firstStr = String.valueOf(first).toLowerCase();
        return firstStr + source.substring(1, source.length());
    }

    public void complet() {
        try {
            if (this.conection != null)
                this.conection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
