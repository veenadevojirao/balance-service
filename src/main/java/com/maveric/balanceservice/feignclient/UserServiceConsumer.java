package com.maveric.balanceservice.feignclient;

import com.maveric.balanceservice.dto.UserDto;
import com.maveric.balanceservice.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name="user-service")
@Service
public interface UserServiceConsumer {
    @GetMapping("/users/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("userId") String id,
                                               @RequestHeader(value = "userid") String headerUserId);
}

