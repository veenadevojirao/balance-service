package com.maveric.balanceservice.controller;

import com.maveric.balanceservice.dto.BalanceDto;
import com.maveric.balanceservice.entity.Balance;
import com.maveric.balanceservice.service.BalanceService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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


    @DeleteMapping("accounts/{accountId}/balances/{balanceId}")
    public ResponseEntity<Object> deleteBalance(@PathVariable String accountID){
        String desc = balanceService.deleteBalance(accountID);
        return (ResponseEntity<Object>) ResponseEntity.status(HttpStatus.OK);
    }
}