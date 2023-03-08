package com.maveric.balanceservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.maveric.balanceservice.dto.BalanceDto;
import com.maveric.balanceservice.dto.UserDto;
import com.maveric.balanceservice.entity.Account;
import com.maveric.balanceservice.entity.Balance;
import com.maveric.balanceservice.enums.Currency;
import com.maveric.balanceservice.feignclient.AccountFeignService;
import com.maveric.balanceservice.feignclient.UserServiceConsumer;
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
import org.springframework.web.util.NestedServletException;

import java.util.ArrayList;
import java.util.List;

import static com.maveric.balanceservice.BalanceServiceApplicationTests.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
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

    @MockBean
    private UserServiceConsumer userServiceConsumer;

    @Autowired
    private MockMvc mock;


    @Mock
    ResponseEntity<BalanceDto> balanceDto;

    @Test
    void getaccountbyBalanceId() throws Exception {
        ResponseEntity<UserDto> responseEntity = new ResponseEntity<>(getUserDto(), HttpStatus.OK);
        when(userServiceConsumer.getUserById(any(String.class), any(String.class))).thenReturn(responseEntity);
        mock.perform(get(apiV1)
                        .contentType(MediaType.APPLICATION_JSON).header("userid", "1234"))
                .andExpect(status().isOk())
                .andDo(print());
    }
    @Test
    public void NotgetBalances() throws Exception
    {
        mock.perform(get(invalidApiV1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }
    @Test
    void shouldThrowErrorWhenWrongDataPassedForCreateUser() throws Exception{
        mvc.perform(post(API_V1_BALANCE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(getUserDto()))
                        .header("userid", "1L")).andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Test
    void deleteBalance_failure() throws Exception {
        ResponseEntity<UserDto> responseEntity = new ResponseEntity<>(new UserDto(), HttpStatus.OK);
        when(userServiceConsumer.getUserById(any(String.class),any())).thenReturn(responseEntity);
        Throwable error = assertThrows(NestedServletException.class, () -> mock.perform(delete(apiV1 + "/accountId1")
                .contentType(MediaType.APPLICATION_JSON)
                .header("userid", "1234")).andReturn());
    }
    @Test
    void updateAccount_failure() throws Exception {
        ResponseEntity<UserDto> responseEntity = new ResponseEntity<>(new UserDto(), HttpStatus.OK);
        when(userServiceConsumer.getUserById(any(String.class),any())).thenReturn(responseEntity);
        Throwable error = assertThrows(NestedServletException.class, () -> mock.perform(put(apiV1 + "/accountId1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(getBalanceDto()))
                .header("userid", "1234")
        ).andReturn());
    }
    @Test
    public void getAccounts() throws Exception
    {

        mock.perform(get("/api/v1/accounts/1234/balances")
                        .contentType(MediaType.APPLICATION_JSON).header("userId", "1234"))
                .andExpect(status().isOk())
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