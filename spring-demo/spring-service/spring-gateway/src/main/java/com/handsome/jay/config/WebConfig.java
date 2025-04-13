package com.fs.fig.gateway.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

@Configuration
public class WebConfig  {

    /**
     * 激活的配置不是正式环境时，才配置跨域过虑器，正式环境时，不配置！
     *
     * @return
     */
    /*@Bean
//    @ConditionalOnExpression("#{!'prod'.equalsIgnoreCase(environment.getProperty('spring.profiles.active'))}")
    public FilterRegistrationBean<Filter> corsFilter() {
        FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new FamsunCorsFilter());
        registrationBean.setName(FamsunCorsFilter.class.getSimpleName());
        //过滤器顺序
        registrationBean.setOrder(FilterRegistrationBean.HIGHEST_PRECEDENCE);
        //拦截规则
        registrationBean.setUrlPatterns(Collections.singletonList("/*"));
        return registrationBean;
    }*/


    /**
     * 1.允许cookies跨域
     * 2.允许向该服务器提交请求的URI，*表示全部允许，在SpringMVC中，如果设成*，会自动转成当前请求头中的Origin
     * 3.允许访问的头信息,*表示全部
     * 4.预检请求的缓存时间（秒），即在这个时间段里，对于相同的跨域请求不会再预检了
     * 5.允许提交请求的方法，*表示全部允许
     *
     * @return 返回 reactive 包下的 CorsWebFilter 对象
     */
    @Bean
    public CorsWebFilter corsWebFilter() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOriginPattern("*");
        config.addAllowedHeader("*");
        config.setMaxAge(3600L);
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsWebFilter(source);
    }





}
