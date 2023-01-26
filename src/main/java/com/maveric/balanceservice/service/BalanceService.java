package com.maveric.balanceservice.service;

import com.maveric.balanceservice.exception.AccountIdMismatchException;

public interface BalanceService {



    void deleteBalanceByAccountId(String accountId, String balanceId) throws AccountIdMismatchException;

//    void deleteTransactionIdByAccountId(String accountId, String transactionId) throws BalanceNotFoundException, AccountIdMismatchException;
}
