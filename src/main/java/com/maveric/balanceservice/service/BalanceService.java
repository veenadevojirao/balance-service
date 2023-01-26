package com.maveric.balanceservice.service;

import com.maveric.balanceservice.dto.BalanceDto;
import com.maveric.balanceservice.exception.AccountIdMismatchException;
import com.maveric.balanceservice.exception.BalanceIDNotFoundException;
import org.springframework.data.domain.Pageable;

public interface BalanceService {

    BalanceDto getBalanceIdByAccountId(String accountId, String balanceId) throws BalanceIDNotFoundException, AccountIdMismatchException;
}
