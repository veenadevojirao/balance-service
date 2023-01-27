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

    void deleteBalance() throws AccountIdMismatchException {
        when(repository.findById("2")).thenReturn(Optional.of(getBalance()));
        willDoNothing().given(repository).deleteById("2");
        String balanceDto = service.deleteBalanceByAccountId("1234","2");
        assertSame( "1234",balanceDto);
    }

    @Test
    void deleteAccount_failure() {
        Throwable error = assertThrows(BalanceIDNotFoundException.class,()->service.deleteBalanceByAccountId("1234","123a4"));
        assertEquals("Balance ID not available",error.getMessage());
    }
}
