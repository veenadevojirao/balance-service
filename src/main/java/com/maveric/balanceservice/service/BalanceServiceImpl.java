package com.maveric.balanceservice.service;

import com.maveric.balanceservice.enums.SuccessMessageConstant;
import com.maveric.balanceservice.exception.BalanceNotFoundException;
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
    BalanceRepository balanceRepository;
    @Autowired
    BalanceMapper mapper;

    @Override


    public String deleteBalance(String accountId){
        balanceRepository.findById(accountId).orElseThrow(() -> new BalanceNotFoundException(accountId));
        balanceRepository.deleteById(accountId);
        return SuccessMessageConstant.DELETE_SUCCESS_MESSAGE;
    }


}