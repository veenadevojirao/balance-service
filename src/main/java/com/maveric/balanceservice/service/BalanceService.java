package com.maveric.balanceservice.service;

import com.maveric.balanceservice.dto.BalanceDto;

import com.maveric.balanceservice.entity.Balance;

import com.maveric.balanceservice.exception.AccountIdMismatchException;
import com.maveric.balanceservice.exception.BalanceIDNotFoundException;
import org.springframework.data.domain.Pageable;

public interface BalanceService {

    public BalanceDto createBalance(String accountId, BalanceDto balanceDto);
    public BalanceDto updateBalance(String accountId,String balanceId,BalanceDto balanceDto);

}
