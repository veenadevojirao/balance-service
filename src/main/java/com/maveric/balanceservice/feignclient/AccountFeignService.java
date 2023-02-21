package com.maveric.balanceservice.feignclient;

import com.maveric.balanceservice.dto.AccountDto;
import com.maveric.balanceservice.entity.Account;
import com.maveric.balanceservice.exception.CustomerIDNotFoundExistsException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.security.auth.login.AccountNotFoundException;
import javax.validation.Valid;
import java.util.List;

@FeignClient(value = "account-service")
public interface AccountFeignService {
    @GetMapping("api/v1/customers/{customerId}/accounts")
    ResponseEntity<List<Account>> getAccountantId(@PathVariable String customerId);
    @GetMapping("api/v1/customers/{customerId}/accounts/{accountId}")
    public AccountDto getAccount(@PathVariable("customerId") String customerId,
                                 @Valid @PathVariable("accountId") String accountId,
                                 @RequestHeader(value = "userid") String headerUserId) throws AccountNotFoundException, CustomerIDNotFoundExistsException;


}
