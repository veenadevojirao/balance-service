package com.maveric.balanceservice.service;

import com.maveric.balanceservice.dto.BalanceDto;
import com.maveric.balanceservice.entity.Balance;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

public interface BalanceService {
    public BalanceDto updateBalance(String accountId,String balanceId,BalanceDto balanceDto);

    BalanceDto updateBalance(Balance balance, String balanceId);
}
