FROM openjdk:11-jre
LABEL maintainer="softwaredevelop@famsungroup.com"
ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

WORKDIR /opt
#ARG JAR_NAME
#ARG PROFILE_ACTIVE
#ARG MODULE_NAME
#ENV JAR_NAME ${JAR_NAME}
#ENV PROFILE_ACTIVE ${PROFILE_ACTIVE}
#ENV MODULE_NAME ${MODULE_NAME}
#ENV LANG C.UTF-8

COPY target/classes .
CMD cd target/classes
CMD java com.jay.handsome.jdkproxy.ProxyInvocationHandler