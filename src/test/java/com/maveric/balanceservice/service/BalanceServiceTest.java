package com.maveric.balanceservice.service;

import com.maveric.balanceservice.dto.BalanceDto;
import com.maveric.balanceservice.entity.Balance;
import com.maveric.balanceservice.exception.BalanceAlreadyExistException;
import com.maveric.balanceservice.mapper.BalanceMapperImpl;
import com.maveric.balanceservice.repository.BalanceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;

import static com.maveric.balanceservice.BalanceServiceApplicationTests.getBalance;
import static com.maveric.balanceservice.BalanceServiceApplicationTests.getBalanceDto;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
//@RunWith(SpringRunner.class)
public class BalanceServiceTest {
    @InjectMocks
    private BalanceServiceImpl service;

    @Mock
    private BalanceRepository repository;

    @Mock
    private BalanceMapperImpl mapper;

    @Mock
    private Page pageResult;


    @Test
    void createAccount() {
        when(mapper.dtoToEntity(any(BalanceDto.class))).thenReturn(getBalance());
        when(mapper.entityToDto(any(Balance.class))).thenReturn(getBalanceDto());
        when(repository.save(any())).thenReturn(getBalance());

       BalanceDto balanceDto = service.createBalance("1234",getBalanceDto());

        assertSame(balanceDto.getAccountId(), getBalance().getAccountId());
    }
    @Test
    void createAccount_failure() {
        Throwable error = assertThrows(BalanceAlreadyExistException.class,()->service.createBalance("1233",getBalanceDto()));  //NOSONAR
        assertEquals("This AccountId Id should be match",error.getMessage());
    }
}
