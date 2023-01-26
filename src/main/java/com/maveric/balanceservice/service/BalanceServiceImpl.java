package com.maveric.balanceservice.service;

import com.maveric.balanceservice.dto.BalanceDto;
import com.maveric.balanceservice.entity.Balance;
import com.maveric.balanceservice.exception.BalanceNotFoundException;
import com.maveric.balanceservice.mapper.BalanceMapper;
import com.maveric.balanceservice.repository.BalanceRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public BalanceDto updateBalance(String accountId, String balanceId, BalanceDto balanceDto) {
        return BalanceDto.builder().build();
    }

    @Override
    public BalanceDto updateBalance(Balance balance, String balanceId){
        Optional<Balance> balanceFromDb = repository.findById(balanceId);
        if(balanceFromDb.isPresent()) {
            Balance newBal = balanceFromDb.get();
            newBal.setAccountId(balance.getAccountId());
            newBal.setCurrency(balance.getCurrency());
            newBal.setAmount(balance.getAmount());


            return mapper.entityToDto(repository.save(newBal));
        }else{
            throw  new BalanceNotFoundException(balanceId);
        }

    }




}




//        private Date getCurrentDateTime () {
//            return getCurrentDateTime();
//        }






