package com.maveric.balanceservice.service;

import com.maveric.balanceservice.dto.BalanceDto;
import com.maveric.balanceservice.entity.Balance;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

public interface BalanceService {


    String deleteBalance(String accountId);
}
