package com.maveric.balanceservice.service;

import com.maveric.balanceservice.dto.BalanceDto;
import com.maveric.balanceservice.entity.Balance;
import com.maveric.balanceservice.exception.AccountIdMismatchException;
import com.maveric.balanceservice.exception.BalanceIDNotFoundException;
import com.maveric.balanceservice.mapper.BalanceMapper;
import com.maveric.balanceservice.repository.BalanceRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transaction;
import java.util.Optional;

//import static com.maveric.balanceservice.enums.Constants.getCurrentDateTime;

@Service
public  class BalanceServiceImpl implements BalanceService {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(BalanceServiceImpl.class);
    @Autowired
    BalanceRepository repository;
    @Autowired
    BalanceMapper mapper;



    @Override
    public BalanceDto getBalanceIdByAccountId(String accountId, String balanceID) throws BalanceIDNotFoundException, AccountIdMismatchException {
        Balance balance = repository.findById(balanceID).orElseThrow(
                () -> new BalanceIDNotFoundException("Transaction id not available")
        );
        if(accountId.equals(balance.getAccountId())) {
            return mapper.entityToDto(balance);
        } else {
            throw new AccountIdMismatchException("Account Id not available");
        }
    }
}