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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
    @Mock
    private List<Account> account;
    @MockBean
    private BalanceService balanceService;

    @MockBean
    private BalanceRepository balanceRepository;

    @MockBean
    private AccountFeignService accountFeignService;

    @Autowired
    private MockMvc mock;


    @Mock
    ResponseEntity<BalanceDto> balanceDto;

    @Test
    void shouldGetBalanceWhenRequestMadeToGetBalance() throws Exception{
        when(accountFeignService.getAccountsbyId("1")).thenReturn(getSampleAccount());
        mvc.perform(get("/api/v1/accounts/1/balances/631061c4c45f78545a1ed042").header("userId",1))
                .andExpect(status().isOk())
                .andDo(print());

    }
    @Test
    void ToCreateBalance() throws Exception{
        Mockito.when(accountFeignService.getAccountsbyId("1")).thenReturn(getSampleAccount());
        mvc.perform(post(API_V1_BALANCE).contentType(MediaType.APPLICATION_JSON).header("userId",1).content(mapper.writeValueAsString(getSampleBalance())))
                .andExpect(status().isCreated())
                .andDo(print());
    }
    @Test
    void MadeToDeleteBalance() throws Exception{
        when(accountFeignService.getAccountsbyId("1")).thenReturn(getSampleAccount());
        mvc.perform(delete(API_V1_BALANCE+"/631061c4c45f78545a1ed042").header("userId",1))
                .andExpect(status().isOk())
                .andDo(print());

    }
    @Test
    void UpdateBalance() throws Exception{
        when(accountFeignService.getAccountsbyId("1")).thenReturn(getSampleAccount());
        mvc.perform(put(API_V1_BALANCE+"/631061c4c45f78545a1ed042").header("userId",1).contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(getSampleBalance())))
                .andExpect(status().isOk())
                .andDo(print());
    }
    @Test
    void WrongForUpdate() throws Exception{
        when(accountFeignService.getAccountsbyId("1")).thenReturn(getSampleAccount());
        Balance balance = new Balance();
        balance.setCurrency(Currency.INR);
        balance.setAccountId(null);
        balance.setAmount(200);
        mvc.perform(put(API_V1_BALANCE+"/631061c4c45f78545a1ed042").header("userId",1).contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(balance)))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }
    @Test
    public void getAccounts() throws Exception
    {

        mock.perform(get("/api/v1/accounts/1234/balances")
                        .contentType(MediaType.APPLICATION_JSON).header("userId", "1234"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void notgetAccounts() throws Exception {
//        String invalidApiV1 = new String();
        mock.perform(get("/api/v1/accounts/12346/balances")
                .contentType(MediaType.APPLICATION_JSON));


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
