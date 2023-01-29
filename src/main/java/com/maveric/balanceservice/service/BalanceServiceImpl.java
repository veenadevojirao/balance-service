package com.maveric.balanceservice.service;

import com.maveric.balanceservice.entity.Balance;
import com.maveric.balanceservice.exception.AccountIdMismatchException;
import com.maveric.balanceservice.exception.BalanceIDNotFoundException;
import com.maveric.balanceservice.mapper.BalanceMapper;
import com.maveric.balanceservice.repository.BalanceRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import static com.maveric.balanceservice.enums.Constants.getCurrentDateTime;

@Service
public  class BalanceServiceImpl implements BalanceService {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(BalanceServiceImpl.class);
    @Autowired
    BalanceRepository repository;
    @Autowired
    BalanceMapper mapper;


    @Override
    public String deleteBalanceByAccountId(String accountId, String balanceId) throws BalanceIDNotFoundException, AccountIdMismatchException {
        Balance balance = repository.findById(balanceId).orElseThrow(
                () -> new BalanceIDNotFoundException("Balance ID not available")
        );
        if(accountId.equals(balance.getAccountId())) {
            repository.deleteById(balanceId);
        } else {
            throw new AccountIdMismatchException("Account ID not available");
        }
        return accountId;
    }

    @Override
    public Object deleteBalance(Object any, Object any1) {
        return deleteBalance("2","2");
    }


}
