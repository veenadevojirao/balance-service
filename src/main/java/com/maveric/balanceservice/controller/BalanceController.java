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
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class BalanceController {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(BalanceController.class);

    @Autowired
    BalanceService balanceService;


    @GetMapping("accounts/{accountId}/Balances")
    public List<BalanceDto> getAllTransactionByAccountId(@RequestParam(value = "page", defaultValue = "0", required = false) int page,
                                                         @RequestParam(value = "pageSize", defaultValue = "5", required = false) int pageSize, @PathVariable("accountId")@Valid String accountId) {
        return balanceService.getBalanceByAccountId(page, pageSize, accountId);}
}