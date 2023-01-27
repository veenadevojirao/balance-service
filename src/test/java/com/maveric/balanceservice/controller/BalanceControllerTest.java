package com.maveric.balanceservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.maveric.balanceservice.dto.BalanceDto;
import com.maveric.balanceservice.entity.Account;
import com.maveric.balanceservice.entity.Balance;
import com.maveric.balanceservice.enums.Currency;
import com.maveric.balanceservice.feignclient.AccountFeignService;
import com.maveric.balanceservice.repository.BalanceRepository;
import com.maveric.balanceservice.service.BalanceService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.mongodb.core.aggregation.AggregationExpression;
import org.springframework.data.mongodb.core.aggregation.ConditionalOperators;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

//import static com.maveric.balanceservice.BalanceServiceApplicationTests.*;
//import static jdk.jfr.internal.jfc.model.Constraint.any;
import java.util.ArrayList;
import java.util.List;

import static org.aspectj.lang.reflect.DeclareAnnotation.Kind.Type;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.data.mongodb.core.aggregation.ConditionalOperators.Cond.when;
import static org.springframework.http.RequestEntity.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ContextConfiguration(classes=BalanceController.class)
//@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@WebMvcTest(BalanceController.class)
public class BalanceControllerTest {
    private static final String API_V1_BALANCE = "/api/v1/accounts/1/balances";

    @Autowired
    MockMvc mvc;
    @Autowired
    ObjectMapper mapper;

    @MockBean
    private BalanceService balanceService;

    @MockBean
    private BalanceRepository balanceRepository;

    @MockBean
    private AccountFeignService accountFeignService;

    @Test
    void ToCreateBalance() throws Exception{
        Mockito.when(accountFeignService.getAccountsbyId("1")).thenReturn(getSampleAccount());
        mvc.perform(post(API_V1_BALANCE).contentType(MediaType.APPLICATION_JSON).header("userId",1).content(mapper.writeValueAsString(getSampleBalance())))
                .andExpect(status().isCreated())
                .andDo(print());
    }


    public Balance getSampleBalance(){

        Balance balance = new Balance();
        balance.setCurrency(Currency.INR);
        balance.setAccountId("4");
        balance.setAmount(200);
        return balance;
    }

    public ResponseEntity<List<Account>> getSampleAccount(){

        List<Account> accountList = new ArrayList<>();
        Account account = new Account();
        account.setCustomerId("1");

        Account account1 = new Account();
        account1.setCustomerId("2");


        accountList.add(account1);
        accountList.add(account);
        return ResponseEntity.status(HttpStatus.OK).body(accountList);
    }
    }






