package com.maveric.balanceservice.controller;

import com.maveric.balanceservice.dto.BalanceDto;
import com.maveric.balanceservice.entity.Account;
import com.maveric.balanceservice.enums.Constants;
import com.maveric.balanceservice.exception.AccountIdMismatchException;
import com.maveric.balanceservice.exception.BalanceIdNotFoundException;
import com.maveric.balanceservice.feignclient.AccountFeignService;
import com.maveric.balanceservice.service.BalanceService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    AccountFeignService accountFeignService;
//    @GetMapping("accounts/{accountId}/balances/{balanceId}")
//    public ResponseEntity<Object> getBalanceDetails(@PathVariable String accountId , @PathVariable String balanceId,@RequestHeader(value = "userId") String userId) throws BalanceIdNotFoundException, AccountIdMismatchException{
//        ResponseEntity<List<Account>> accountList = accountFeignService.getAccount(accountId);
//        balanceService.findAccountIdBelongsToCurrentUser(accountList.getBody(),accountId);
//        String balance = String.valueOf(balanceService.getBalanceIdByAccountId(balanceId,accountId));
//        System.out.println(balance);
//        log.info(Constants.GET_BALANCE_LOG+accountId);
//        return ResponseEntity.status(HttpStatus.OK).body(balance);
//    }
    @GetMapping("accounts/{accountId}/balances/{balanceId}")
    public ResponseEntity<BalanceDto> getBalanceByAccountId(@PathVariable("accountId") String accountId,
                                                            @PathVariable("balanceId") String balanceId, String userId)
            throws BalanceIdNotFoundException, AccountIdMismatchException {

        return new ResponseEntity<>(balanceService.getBalanceIdByAccountId(accountId, balanceId), HttpStatus.OK);
    }



    @GetMapping("accounts/{accountId}/balances")
    public ResponseEntity<BalanceDto> getBalances
            (@PathVariable String accountId) {
        BalanceDto balanceDtoResponse = balanceService.getBalanceByAccountId(accountId);
        log.info("API call returning Balance details for given Account Id");
        return new ResponseEntity<>(balanceDtoResponse, HttpStatus.OK);}


    @DeleteMapping("accounts/{accountId}/balances/{balanceId}")
    public ResponseEntity<String> deleteBalanceByAccountId(@PathVariable("accountId") String accountId,
                                                           @PathVariable("balanceId") String balanceId)
            throws BalanceIdNotFoundException, AccountIdMismatchException {
        balanceService.deleteBalanceByAccountId(accountId, balanceId);
        return new ResponseEntity<>("Balance deleted successfully", HttpStatus.OK);
    }

    @PutMapping("/accounts/{accountId}/balances/{balanceId}")
    public ResponseEntity<BalanceDto> updateBalance(@Valid @RequestBody BalanceDto balanceDto, @PathVariable String accountId, @PathVariable String balanceId) {

        BalanceDto balanceDetails = balanceService.updateBalance(accountId, balanceId, balanceDto);
        return ResponseEntity.status(HttpStatus.OK).body(balanceDetails);

    }

        @PostMapping("accounts/{accountId}/balances")
        public ResponseEntity<BalanceDto> createBalance(@PathVariable String accountId,@Valid @RequestBody BalanceDto balanceDto) {
            log.info("API call to create a new Balance for given Account Id");
            BalanceDto balanceDtoResponse = balanceService.createBalance(accountId, balanceDto);
            log.info("New Balance Created successfully");
            return new ResponseEntity<>(balanceDtoResponse, HttpStatus.CREATED);

        }
    @GetMapping("accounts/{accountId}/balances/accountBalance")
    public ResponseEntity<BalanceDto> getBalanceAccountDetails(@PathVariable String accountId,@RequestHeader(value = "userId") String userId) throws AccountIdMismatchException {
        ResponseEntity<List<Account>> accountList = accountFeignService.getAccountsbyId(userId);
        balanceService.findAccountIdBelongsToCurrentUser(accountList.getBody(),accountId);
        BalanceDto balanceDto = balanceService.getBalanceForParticularAccount(accountId);
        log.info("getBalanceAccountDetails-> balance detail for accountID {}",accountId);
        return ResponseEntity.status(HttpStatus.OK).body(balanceDto);
    }

    }

