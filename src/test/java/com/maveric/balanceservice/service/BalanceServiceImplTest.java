package com.maveric.balanceservice.service;

import com.maveric.balanceservice.dto.BalanceDto;
import com.maveric.balanceservice.entity.Balance;
import com.maveric.balanceservice.enums.Currency;
import com.maveric.balanceservice.exception.AccountIdMismatchException;
import com.maveric.balanceservice.mapper.BalanceMapper;
import com.maveric.balanceservice.repository.BalanceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;

import java.util.Optional;

import static com.maveric.balanceservice.BalanceServiceApplicationTests.getBalance;
import static com.maveric.balanceservice.BalanceServiceApplicationTests.getBalanceDto;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BalanceServiceImplTest {
    @Mock
    private BalanceRepository repository;

    @Mock
    private BalanceMapper balanceMapper;

    @Mock
    private Page page;

    @InjectMocks
    private BalanceServiceImpl service;
    @Test
    void shouldReturnBalanceWhenGetBalanceInvoked() throws Exception {
        when(repository.findByAccountIdAndBalanceId("1","631061c4c45f78545a1ed04")).thenReturn(Optional.of(getSampleBalance()));

        String balance = String.valueOf(service.getBalanceIdByAccountId("1","631061c4c45f78545a1ed04"));

        assertNotNull(balance);
        assertSame(balance,getSampleBalance().getAmount());

    }

    private BalanceDto any(Class<Balance> balanceClass) {
        return null;
    }

    public Balance getSampleBalance(){
        Balance balance = new Balance();

        balance.setCurrency(Currency.INR);
        balance.setAccountId("1");
        balance.setAmount(200);
        return balance;
    }

    public BalanceDto getSampleDtoBalance(){
        BalanceDto balanceDto = new BalanceDto();
        balanceDto.setCurrency(Currency.INR);
        balanceDto.setAccountId("1");
        balanceDto.setAmount(200);
        return balanceDto;
    }
}
