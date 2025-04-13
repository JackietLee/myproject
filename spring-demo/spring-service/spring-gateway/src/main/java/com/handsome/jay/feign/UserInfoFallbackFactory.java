package com.handsome.jay.feign;


import com.handsome.jay.common.Rt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserInfoFallbackFactory implements FallbackFactory<UserInfoFeign> {
    @Override
    public UserInfoFeign create(Throwable cause) {
        log.error("error: {}", cause.getMessage(), cause);
        return new UserInfoFeign() {
            @Override
            public Rt<UserInfoVO> getUserInfo(String token) {
                return Rt.fail(cause.getMessage());
            }

            @Override
            public Rt<User> getById(Integer id) {
                return Rt.fail(cause.getMessage());
            }

            @Override
            public Rt<User> getByName(String name) {
                return Rt.fail(cause.getMessage());
            }
        };
    }
}
