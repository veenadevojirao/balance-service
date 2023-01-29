package com.maveric.balanceservice.service;

import com.maveric.balanceservice.exception.AccountIdMismatchException;

public interface BalanceService {


    String deleteBalanceByAccountId(String accountId, String balanceId) throws AccountIdMismatchException;

    Object deleteBalance(Object any, Object any1);


}
