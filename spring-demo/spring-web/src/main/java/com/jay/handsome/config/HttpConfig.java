/*
 * Copyright (c) 2021-2030 丰尚智慧农牧科技有限公司
 * 不能修改和删除上面的版权声明
 * 此代码属于丰尚智慧农牧科技有限公司编写，在未经允许的情况下不得传播复制
 */
package com.jay.handsome.config;

import io.undertow.Undertow;
import io.undertow.UndertowOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.undertow.UndertowServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 同时支持http和https
 *
 * @author jay
 * @date 2021/11/29 10:07:43
 */
@Configuration
public class HttpConfig {

    @Value("${http.port}")
    private Integer port;

    @Bean
    public ServletWebServerFactory servletContainer() {
        UndertowServletWebServerFactory undertow = new UndertowServletWebServerFactory();
        undertow.addBuilderCustomizers((Undertow.Builder builder) -> {
            builder.addHttpListener(port, "0.0.0.0");
            // 开启HTTP2
//            builder.setServerOption(UndertowOptions.ENABLE_HTTP2, true);
        });
//        undertow.addDeploymentInfoCustomizers(deploymentInfo -> {
//
//// 开启HTTP自动跳转至HTTPS
//
//            deploymentInfo.addSecurityConstraint(new SecurityConstraint()
//
//                            .addWebResourceCollection(new WebResourceCollection().addUrlPattern("/*"))
//
//                            .setTransportGuaranteeType(TransportGuaranteeType.CONFIDENTIAL)
//
//                            .setEmptyRoleSemantic(SecurityInfo.EmptyRoleSemantic.PERMIT))
//
//                    .setConfidentialPortManager(exchange -> httpsPort);
//
//        });
        return undertow;
    }


//    @Bean
//    public ServletWebServerFactory servletContainer() {
//        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
//        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
//        connector.setPort(7001);
//        connector.setRedirectPort(8000); // http转https
//        connector.addUpgradeProtocol(new Http2Protocol());
//        tomcat.addAdditionalTomcatConnectors(connector);
//        return tomcat;
//    }

}
