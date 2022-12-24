package com.jay.handsome.config;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

/**
 * 注释
 *
 * @author jay
 * @date 2022/12/20 10:41
 */
@Component
public class MyFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println(111);
        chain.doFilter(request, response);
    }
}
