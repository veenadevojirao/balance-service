package com.maveric.balanceservice.feignclient;

import com.maveric.balanceservice.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="user-service")
@Service
public interface UserServiceConsumer {
    @GetMapping("api/v1/users/{userId}")
    public ResponseEntity<User> getUserDetails(@PathVariable String userId);
}

