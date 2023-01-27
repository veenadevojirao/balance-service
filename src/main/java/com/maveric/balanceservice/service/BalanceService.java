package com.maveric.balanceservice.service;

import com.maveric.balanceservice.exception.AccountIdMismatchException;

public interface BalanceService {



    String deleteBalanceByAccountId(String accountId, String balanceId) throws AccountIdMismatchException;

    Object deleteBalance(Object any, Object any1);

//    String deleteBalance(String s, String s1);

//    void deleteTransactionIdByAccountId(String accountId, String transactionId) throws BalanceNotFoundException, AccountIdMismatchException;
}
