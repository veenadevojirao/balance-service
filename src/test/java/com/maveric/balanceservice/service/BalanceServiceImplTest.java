package com.maveric.balanceservice.service;

import com.maveric.balanceservice.dto.BalanceDto;
import com.maveric.balanceservice.entity.Balance;
import com.maveric.balanceservice.enums.Currency;
import com.maveric.balanceservice.exception.AccountIdMismatchException;
import com.maveric.balanceservice.exception.BalanceAlreadyExistException;
import com.maveric.balanceservice.exception.BalanceIdNotFoundException;
import com.maveric.balanceservice.exception.BalanceNotFoundException;
import com.maveric.balanceservice.mapper.BalanceMapperImpl;
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
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)

 class BalanceServiceImplTest {
    @InjectMocks
    private BalanceServiceImpl balanceService;

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

        BalanceDto balanceDto = balanceService.createBalance("123",getBalanceDto());

        assertSame(balanceDto.getAccountId(), getBalance().getAccountId());
    }
    @Test
    void createAccount_failure() {
        Throwable error = assertThrows(BalanceAlreadyExistException.class,()->balanceService.createBalance("1233",getBalanceDto()));  //NOSONAR
        assertEquals("This AccountId Id should be match",error.getMessage());
    }
    @Test

    void deleteBalanceByAccountId() throws AccountIdMismatchException {
        when(repository.findById("2")).thenReturn(Optional.of(getBalance()));
        willDoNothing().given(repository).deleteById("2");
        String balanceDto = balanceService.deleteBalanceByAccountId("123","2");
        assertSame( "123",balanceDto);
    }
    @Test
    void deleteAccount_failure() {
        Throwable error = assertThrows(BalanceIdNotFoundException.class,()->balanceService.deleteBalanceByAccountId("1234","123a4"));
        assertEquals("Balance ID not available",error.getMessage());
    }
    @Test
    void updateBalance() {
        when(repository.findById("2")).thenReturn(Optional.ofNullable(getBalance()));
        when(mapper.entityToDto(any(Balance.class))).thenReturn(getBalanceDto());
when(repository.save(any())).thenReturn(getBalance());
        BalanceDto BalanceDto = balanceService.updateBalance("123","2",getBalanceDto());
        assertSame(BalanceDto.getAccountId(),getBalanceDto().getAccountId());
    }
    @Test
    void updateBalance_failure() {
        Throwable error = assertThrows(BalanceNotFoundException.class,()->balanceService.updateBalance("123","2",getBalanceDto()));//NOSONAR
        assertEquals("BalanceId is not Exisists For 2",error.getMessage());    }
    @Test
    void getBalanceIdByAccountId() {
        when(repository.findByAccountId("1234")).thenReturn(getBalance());
        when(mapper.entityToDto(any(Balance.class))).thenReturn(getBalanceDto());
        BalanceDto balanceDto = balanceService.getBalanceByAccountId("1234");
        assertSame(balanceDto.getAccountId(),getBalanceDto().getAccountId());
    }
    @Test
    void getAccountDetailsById() throws BalanceIdNotFoundException {
        when(repository.findById(any(String.class))).thenReturn(Optional.empty());
        assertThrows(BalanceIdNotFoundException.class,()->{
            balanceService.getBalanceIdByAccountId("1","1");
        });
    }
    public Balance getSampleBalance(){
        Balance balance = new Balance();

        balance.setCurrency(Currency.INR);
        balance.setAccountId("1");
        balance.setAmount(200);
        return balance;
    }
}
