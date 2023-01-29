package com.maveric.balanceservice.service;

import com.maveric.balanceservice.dto.BalanceDto;
import com.maveric.balanceservice.entity.Balance;

import com.maveric.balanceservice.exception.AccountIdMismatchException;
import com.maveric.balanceservice.exception.BalanceAlreadyExistException;
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
    public BalanceDto createBalance(String accountId, BalanceDto balanceDto) {
        if ((accountId.equals(balanceDto.getAccountId()))){
          if(repository.findByAccountId(accountId) == null){
                Balance balance = mapper.dtoToEntity(balanceDto);
                //Balance balanceResult = repository.save(balance);
                log.error("Created new Balance successfully");
                return mapper.entityToDto(repository.save(balance));
            }
            else{
                    throw new BalanceAlreadyExistException("Balance already exsists exception");
                }

        }
        else {
            throw new BalanceAlreadyExistException("This AccountId Id should be match");
        }


    }


}

