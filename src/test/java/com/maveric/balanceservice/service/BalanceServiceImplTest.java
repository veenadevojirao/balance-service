package com.maveric.balanceservice.service;

import com.maveric.balanceservice.dto.BalanceDto;
import com.maveric.balanceservice.entity.Balance;
import com.maveric.balanceservice.enums.Currency;
import com.maveric.balanceservice.mapper.BalanceMapper;
import com.maveric.balanceservice.repository.BalanceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;

import static com.maveric.balanceservice.BalanceServiceApplicationTests.getBalance;
import static com.maveric.balanceservice.BalanceServiceApplicationTests.getBalanceDto;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
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
    void getAccountByUserID() {
        Page<Balance> pagedResponse = new PageImpl(Arrays.asList());
        when(repository.findByAccountId(any(Pageable.class),eq("1234"))).thenReturn(pagedResponse);

        List<BalanceDto> balance = service.getBalanceByAccountId(1,1,"1234");

        assertEquals(0, balance.size());
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
