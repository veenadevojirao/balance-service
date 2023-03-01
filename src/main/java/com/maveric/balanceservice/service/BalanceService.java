package com.maveric.balanceservice.service;


import com.maveric.balanceservice.dto.BalanceDto;
import com.maveric.balanceservice.entity.Account;
import com.maveric.balanceservice.exception.AccountIdMismatchException;

import com.maveric.balanceservice.exception.BalanceIdNotFoundException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public interface BalanceService {

    BalanceDto getBalanceIdByAccountId(String accountId, String balanceId) throws BalanceIdNotFoundException, AccountIdMismatchException;

    BalanceDto getBalanceByAccountId(String accountId);

    String deleteBalanceByAccountId(String accountId, String balanceId) throws AccountIdMismatchException;

    Object deleteBalance(Object any, Object any1);


    public BalanceDto createBalance(String accountId, BalanceDto balanceDto);
    public BalanceDto updateBalance(String accountId,String balanceId,BalanceDto balanceDto);


    BalanceDto getBalanceForParticularAccount(String accountId);

void findAccountIdBelongsToCurrentUser(List<Account> body, String accountId);
    void deleteBalanceByAccountId(String accountId);

}
