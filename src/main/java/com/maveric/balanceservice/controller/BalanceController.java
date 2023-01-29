package com.maveric.balanceservice.controller;

import com.maveric.balanceservice.dto.BalanceDto;
import com.maveric.balanceservice.exception.AccountIdMismatchException;
import com.maveric.balanceservice.exception.BalanceIDNotFoundException;
import com.maveric.balanceservice.repository.BalanceRepository;
import com.maveric.balanceservice.service.BalanceService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class BalanceController {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(BalanceController.class);

    @Autowired
    BalanceService balanceService;


    @DeleteMapping("accounts/{accountId}/balances/{balanceId}")
    public ResponseEntity<String> deleteBalanceByAccountId(@PathVariable("accountId") String accountId,
                                                           @PathVariable("balanceId") String balanceId)
            throws BalanceIDNotFoundException, AccountIdMismatchException {
        balanceService.deleteBalanceByAccountId(accountId, balanceId);
        return new ResponseEntity<>("Balance deleted successfully", HttpStatus.OK);
    }
}