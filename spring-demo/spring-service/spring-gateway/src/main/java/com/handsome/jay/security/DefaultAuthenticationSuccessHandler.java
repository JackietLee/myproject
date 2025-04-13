package com.handsome.jay.security;

import com.alibaba.fastjson.JSONObject;
import com.handsome.jay.common.Rt;
import com.handsome.jay.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.ServerAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ShiLei
 * @version 1.0.0
 * @date 2021/3/11 15:00
 * @description 登录成功处理
 */
@Component
public class DefaultAuthenticationSuccessHandler implements ServerAuthenticationSuccessHandler {

    /**
     * token 过期时间
     */
    @Value("${jwt.token.expired}")
    private int jwtTokenExpired;

    /**
     * 刷新token 时间
     */
    @Value("${jwt.token.refresh.expired}")
    private int jwtTokenRefreshExpired;

    @Override
    public Mono<Void> onAuthenticationSuccess(WebFilterExchange webFilterExchange, Authentication authentication) {
        return Mono.defer(() -> Mono.just(webFilterExchange.getExchange().getResponse()).flatMap(response -> {
            DataBufferFactory dataBufferFactory = response.bufferFactory();
            // 生成JWT token
            Map<String, Object> map = new HashMap<>(2);
            SecurityUserDetails userDetails = (SecurityUserDetails) authentication.getPrincipal();
            map.put("userId", userDetails.getUserId());
            map.put("username", userDetails.getUsername());
            map.put("roles", userDetails.getRoles());
            map.put("authorities",userDetails.getAuthorities());
            String token = JwtTokenUtil.generateToken(map, userDetails.getUsername(), jwtTokenExpired);
            String refreshToken = JwtTokenUtil.generateToken(map, userDetails.getUsername(), jwtTokenRefreshExpired);
            Map<String, Object> tokenMap = new HashMap<>(2);
            tokenMap.put("token", token);
            tokenMap.put("refreshToken", refreshToken);
            DataBuffer dataBuffer = dataBufferFactory.wrap(JSONObject.toJSONString(Rt.ok(tokenMap)).getBytes());
            return response.writeWith(Mono.just(dataBuffer));
        }));
    }
}

