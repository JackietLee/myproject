package com.handsome.jay.security;

import com.alibaba.fastjson.JSONObject;
import com.handsome.jay.common.Rt;
import com.handsome.jay.common.RtEnum;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.ServerAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * @author ShiLei
 * @version 1.0.0
 * @date 2021/3/11 15:14
 * @description 登录失败处理
 */
@Component
public class DefaultAuthenticationFailureHandler implements ServerAuthenticationFailureHandler {

    @Override
    public Mono<Void> onAuthenticationFailure(WebFilterExchange webFilterExchange, AuthenticationException exception) {
        return Mono.defer(() -> Mono.just(webFilterExchange.getExchange().getResponse()).flatMap(response -> {
            DataBufferFactory dataBufferFactory = response.bufferFactory();
            Rt<Map<String, Object>> resultVO = Rt.error();
            // 账号不存在
            if (exception instanceof UsernameNotFoundException) {
                resultVO = new Rt<>(RtEnum.ACCOUNT_OR_PASSWORD_ERROR);
                // 用户名或密码错误
            } else if (exception instanceof BadCredentialsException) {
                resultVO = new Rt<>(RtEnum.ACCOUNT_OR_PASSWORD_ERROR);
                // 账号已过期
            } else if (exception instanceof AccountExpiredException) {
                resultVO = new Rt<>(RtEnum.ACCOUNT_OR_PASSWORD_ERROR);
                // 账号已被锁定
            } else if (exception instanceof LockedException) {
                resultVO = new Rt<>(RtEnum.ACCOUNT_OR_PASSWORD_ERROR);
                // 用户凭证已失效
            } else if (exception instanceof CredentialsExpiredException) {
                resultVO = new Rt<>(RtEnum.ACCOUNT_OR_PASSWORD_ERROR);
                // 账号已被禁用
            } else if (exception instanceof DisabledException) {
                resultVO = new Rt<>(RtEnum.ACCOUNT_OR_PASSWORD_ERROR);
            }

            DataBuffer dataBuffer = dataBufferFactory.wrap(JSONObject.toJSONString(resultVO).getBytes());
            return response.writeWith(Mono.just(dataBuffer));
        }));
    }
}
