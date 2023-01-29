package com.maveric.balanceservice.service;


import com.maveric.balanceservice.dto.BalanceDto;
import com.maveric.balanceservice.exception.AccountIdMismatchException;

import java.util.List;

public interface BalanceService {

    List<BalanceDto>getBalanceByAccountId(int page, int pageSize, String accountId);



    String deleteBalanceByAccountId(String accountId, String balanceId) throws AccountIdMismatchException;

    Object deleteBalance(Object any, Object any1);


    public BalanceDto createBalance(String accountId, BalanceDto balanceDto);
    public BalanceDto updateBalance(String accountId,String balanceId,BalanceDto balanceDto);


}
