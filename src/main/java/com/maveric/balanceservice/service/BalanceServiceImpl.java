package com.maveric.balanceservice.service;

import com.maveric.balanceservice.dto.BalanceDto;
import com.maveric.balanceservice.entity.Balance;
import com.maveric.balanceservice.exception.BalanceAlreadyExistException;
import com.maveric.balanceservice.exception.BalanceNotFoundException;
import com.maveric.balanceservice.mapper.BalanceMapper;
import com.maveric.balanceservice.repository.BalanceRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

//import static com.maveric.balanceservice.enums.Constants.getCurrentDateTime;

@Service
public  class BalanceServiceImpl implements BalanceService {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(BalanceServiceImpl.class);
    @Autowired
    BalanceRepository repository;
    @Autowired
    BalanceMapper mapper;

    @Override
    public List<BalanceDto>getBalanceByAccountId(int page, int pageSize, String accountId) {

        if (repository.findById(accountId).isPresent()) {
            throw new BalanceNotFoundException("Balance details not found");

        } else {
            Pageable pageable = PageRequest.of(page, pageSize);
            Page<Balance> balancePage = repository.findByAccountId(pageable, accountId);

            List<Balance> balanceList = balancePage.getContent();
            return balanceList.stream().map(balance -> mapper.entityToDto(balance)).toList();
        }
    }



}