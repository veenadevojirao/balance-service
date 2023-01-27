package com.maveric.balanceservice.service;

import com.maveric.balanceservice.dto.BalanceDto;
import com.maveric.balanceservice.entity.Balance;
import com.maveric.balanceservice.enums.Currency;
import com.maveric.balanceservice.exception.AccountIdMismatchException;
import com.maveric.balanceservice.exception.BalanceIDNotFoundException;
import com.maveric.balanceservice.mapper.BalanceMapper;
import com.maveric.balanceservice.mapper.BalanceMapperImpl;
import com.maveric.balanceservice.repository.BalanceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;

import javax.security.auth.login.AccountNotFoundException;
import java.util.Optional;

import static com.maveric.balanceservice.BalanceServiceApplicationTests.getBalance;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BalanceServiceImplTest {
    @InjectMocks
    private BalanceServiceImpl service;

    @Mock
    private BalanceRepository repository;

    @Mock
    private BalanceMapperImpl mapper;

    @Mock
    private Page pageResult;

    @Test
    void shouldReturnMessageWhenDeleteBalanceInvoked() throws Exception {
        when(repository.deleteBalanceByAccountId("15","63d236497b27b56c3bc0c928")).thenReturn(Optional.of(getSampleBalance()));

        String message = service.deleteBalanceByAccountId("15","63d236497b27b56c3bc0c928");

        assertNotNull(message);
        assertSame( "Balance Deleted Successfully",message );
    }
    public Balance getSampleBalance(){
        Balance balance = new Balance();
        balance.setCurrency(Currency.INR);
        balance.setAccountId("1");
        balance.setAmount(200);
        return balance;
    }

    @Test
    void deleteAccount_failure() {
        Throwable error = assertThrows(BalanceIDNotFoundException.class,()->service.deleteBalanceByAccountId("1234","123a4"));
        assertEquals("Balance ID not available",error.getMessage());
    }
}
