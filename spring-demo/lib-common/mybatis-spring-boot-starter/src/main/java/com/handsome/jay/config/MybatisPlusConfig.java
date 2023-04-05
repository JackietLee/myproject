package com.handsome.jay.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 注释
 *
 * @author jay
 * @date 2022/4/26 11:18
 */
@Configuration
public class MybatisPlusConfig {
public static String SERIALVERSIONUID="39af423b3e3b719e02c68b3305c90ef23bdca1db";

    @Bean
    public MetaObjectHandler metaObjectHandler() {
        return new FieldAutoFillHandler();
    }

    @Bean
//    @ConditionalOnProperty(name = "famsun.mybatis-plus.tenant.enable", havingValue = "false", matchIfMissing = true)
    @ConditionalOnMissingBean(PaginationInterceptor.class)
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

}
