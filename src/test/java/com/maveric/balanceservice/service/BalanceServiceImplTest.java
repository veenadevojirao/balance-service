package com.maveric.balanceservice.service;

import com.maveric.balanceservice.dto.BalanceDto;
import com.maveric.balanceservice.entity.Balance;
import com.maveric.balanceservice.enums.Currency;
import com.maveric.balanceservice.exception.BalanceNotFoundException;
import com.maveric.balanceservice.mapper.BalanceMapper;
import com.maveric.balanceservice.repository.BalanceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;

import javax.security.auth.login.AccountNotFoundException;
import java.util.Optional;

import static com.maveric.balanceservice.BalanceServiceApplicationTests.getBalance;
import static com.maveric.balanceservice.BalanceServiceApplicationTests.getBalanceDto;
//import static jdk.jfr.internal.jfc.model.Constraint.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
//@RunWith(SpringRunner.class)

public class BalanceServiceImplTest {
    @InjectMocks
    private BalanceServiceImpl service;

    @Mock
    private BalanceRepository repository;

    @Mock
    private BalanceMapper mapper;

    @Mock
    private Page pageResult;


    @Test
    void updateBalance() {
        when(repository.findById("2")).thenReturn(Optional.ofNullable(getBalance()));
        when(mapper.entityToDto(any(Balance.class))).thenReturn(getBalanceDto());
// when(repository.save(any())).thenReturn(getBalance());
        BalanceDto BalanceDto = service.updateBalance("1234","2",getBalanceDto());
        assertSame(BalanceDto.getAccountId(),getBalanceDto().getAccountId());
    }


    private Balance any(Class<Balance> balanceClass) {
        return ArgumentMatchers.any();
    }
    @Test
    void updateBalance_failure() {
        Throwable error = assertThrows(BalanceNotFoundException.class,()->service.updateBalance("1234","2",getBalanceDto()));//NOSONAR
        assertEquals("BalanceId is not Exisists For 2",error.getMessage());    }

}
