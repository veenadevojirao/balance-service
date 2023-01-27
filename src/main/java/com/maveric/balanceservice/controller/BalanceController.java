package com.maveric.balanceservice.controller;

import com.maveric.balanceservice.dto.BalanceDto;
import com.maveric.balanceservice.entity.Balance;
import com.maveric.balanceservice.service.BalanceService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class BalanceController {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(BalanceController.class);

    @Autowired
    BalanceService balanceService;

    @PostMapping("accounts/{accountId}/balances")
    public ResponseEntity<BalanceDto> createBalance(@PathVariable String accountId,@Valid @RequestBody BalanceDto balanceDto) {
        log.info("API call to create a new Balance for given Account Id");
        BalanceDto balanceDtoResponse = balanceService.createBalance(accountId,balanceDto);
        System.out.println("In controller method");
        log.info("New Balance Created successfully");
        return new ResponseEntity<>(balanceDtoResponse, HttpStatus.CREATED);
    }

}
