package com.handsome.jay.security;

import com.handsome.jay.util.JwtTokenUtil;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;

/**
* @author ShiLei
* @version 1.0.0
* @date 2021/3/11 13:23
* @description token 认证处理
*/
@Component
@Primary
public class TokenAuthenticationManager implements ReactiveAuthenticationManager {

@Override
@SuppressWarnings("unchecked")
public Mono<Authentication> authenticate(Authentication authentication) {
    return Mono.just(authentication)
            .map(auth -> JwtTokenUtil.parseJwtRsa256(auth.getPrincipal().toString()))
            .map(claims -> {
//                Collection<? extends GrantedAuthority> roles = (Collection<? extends GrantedAuthority>) claims.get("roles");
                Collection<? extends LinkedHashMap> roles1 = (Collection<? extends LinkedHashMap >)claims.get("authorities");
                Collection<? extends GrantedAuthority> roles = roles1.stream().map(role->new SimpleGrantedAuthority(role.get("authority").toString())).collect(Collectors.toList());
                return new UsernamePasswordAuthenticationToken(
                        claims.getSubject(),
                        null,
                        roles);
            });
}
}
