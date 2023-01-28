package com.maveric.balanceservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.maveric.balanceservice.dto.BalanceDto;
import com.maveric.balanceservice.entity.Account;
import com.maveric.balanceservice.entity.Balance;
import com.maveric.balanceservice.enums.Currency;
import com.maveric.balanceservice.enums.Type;
import com.maveric.balanceservice.feignAccount.AccountFeignService;
import com.maveric.balanceservice.repository.BalanceRepository;
import com.maveric.balanceservice.service.BalanceService;
import com.maveric.balanceservice.service.BalanceServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
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

import static com.maveric.balanceservice.BalanceServiceApplicationTests.getBalance;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes=BalanceController.class)
//@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@WebMvcTest(BalanceController.class)
public class BalancecontrolletTest {
//private static final String API_V1_BALANCE = "/api/v1/accounts/1/balances";
//    private static final String API_V1_BALANCE = "/api/v1/accounts/4/balances";

    @Autowired
    private MockMvc mock;

    @MockBean
    private BalanceService balanceService;
    @Mock
    ResponseEntity<BalanceDto> balanceDto;

    @Autowired
    ObjectMapper mapper;
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
        balance.setAccountId("1234");
        balance.setAmount(200);
        return balance;
    }
    public ResponseEntity<List<Account>> getSampleAccount(){

        List<Account> accountList = new ArrayList<>();
        Account account = new Account();
        account.setCustomerId("1234");
        account.setAccountType(Type.CURRENT);
        Account account1 = new Account();
        account1.setCustomerId("1234");
        account.setAccountType(Type.CURRENT);
        accountList.add(account1);
        accountList.add(account);
        return ResponseEntity.status(HttpStatus.OK).body(accountList);
    }
}
