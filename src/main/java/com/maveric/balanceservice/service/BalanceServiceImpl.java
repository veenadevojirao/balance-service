package com.maveric.balanceservice.service;

import com.maveric.balanceservice.dto.BalanceDto;
import com.maveric.balanceservice.entity.Balance;
import com.maveric.balanceservice.exception.BalanceIdNotFoundException;
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

//    @Override
//    public BalanceDto updateBalance(String accountId, String balanceId, BalanceDto balanceDto) {
//        return BalanceDto.builder().build();
//    }

    @Override
    public BalanceDto updateBalance(String accountId,String balanceId,BalanceDto balanceDto) {
        System.out.println(accountId);
        System.out.println(balanceDto.getAccountId());
        if ((accountId.equals(balanceDto.getAccountId()))) {

            Optional<Balance> balanceFromDb = repository.findById(balanceId);
            if (balanceFromDb.isPresent()) {
                Balance newBal = repository.findById(balanceId).orElseThrow(() -> new BalanceNotFoundException("Balance not found"));
                newBal.set_id(balanceDto.get_id());
                newBal.setAccountId(balanceDto.getAccountId());
                newBal.setCurrency(balanceDto.getCurrency());
                newBal.setAmount(balanceDto.getAmount());
                newBal.setUpdatedAt(balanceDto.getUpdatedAt());
                newBal.setCreatedAt(balanceDto.getCreatedAt());


                return mapper.entityToDto(repository.save(newBal));
            } else {
                throw new BalanceNotFoundException("BalanceId is not Exisists For " + balanceId);
            }
        } else {
            throw new BalanceIdNotFoundException("AccountId is not equal");
        }


    }
}




//        private Date getCurrentDateTime () {
//            return getCurrentDateTime();
//        }






