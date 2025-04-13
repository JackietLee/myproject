package com.handsome.jay.security;

import com.handsome.jay.common.Rt;
import com.handsome.jay.feign.User;
import com.handsome.jay.feign.UserInfoFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author ceshi
 * @date 2021/3/9 14:03
 * @description 用户登录处理
 * @version 1.0.0
 */
@Service
public class UserDetailsServiceImpl implements ReactiveUserDetailsService {

    @Resource
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserInfoFeign userInfoFeign;

    private static final ExecutorService executorService = Executors.newCachedThreadPool();
    private final WebClient webClient= WebClient.builder().baseUrl("lb://mysql").build();
    @Override
    public Mono<UserDetails> findByUsername(String username) {
//        SecurityUserDetails securityUserDetails = new SecurityUserDetails(
//                "user",
//                passwordEncoder.encode("user"),
//                true, true, true, true, new ArrayList<>(),
//                1L
//        );
//        Mono<String> userMono = webClient
//                .get()
//                .uri("/user/name/{name}", "user1")
//                .retrieve().bodyToMono(String.class);
        Future future = executorService.submit((Callable<Rt<User>>) () ->
             userInfoFeign.getByName("user1"));
        Rt<User> result = null;
        try {
            result = (Rt<User>) future.get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        if (result == null) {
            throw new UsernameNotFoundException("User not found");
        }
        User data = result.getData();
        SecurityUserDetails securityUserDetails = new SecurityUserDetails(
                data.getName(),
        passwordEncoder.encode(data.getPassword()),
        true, true, true, true, authorities(data),
                data.getId(),data.getPaths()
        );
        return Mono.just(securityUserDetails);
    }

    private Collection<? extends GrantedAuthority> authorities(User user) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (user.getPaths() != null) {
            for (String path : user.getPaths()) {
                authorities.add(new SimpleGrantedAuthority(path));
            }
        }
        return authorities;
    }
}
