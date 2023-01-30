package com.maveric.balanceservice.service;

import com.maveric.balanceservice.dto.BalanceDto;
import com.maveric.balanceservice.entity.Balance;

import com.maveric.balanceservice.exception.BalanceAlreadyExistException;

import com.maveric.balanceservice.exception.AccountIdMismatchException;

import com.maveric.balanceservice.exception.BalanceIdNotFoundException;

import com.maveric.balanceservice.exception.BalanceAlreadyExistException;
//import com.maveric.balanceservice.exception.BalanceIdNotFoundException;
import com.maveric.balanceservice.exception.BalanceIdNotFoundException;

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
    public List<BalanceDto> getBalanceByAccountId(int page, int pageSize, String accountId) {

        if (repository.findById(accountId).isPresent()) {
            throw new BalanceNotFoundException("Balance details not found");

        } else {
            Pageable pageable = PageRequest.of(page, pageSize);
            Page<Balance> balancePage = repository.findByAccountId(pageable, accountId);

            List<Balance> balanceList = balancePage.getContent();
            return balanceList.stream().map(balance -> mapper.entityToDto(balance)).toList();
        }
    }

    @Override
    public String deleteBalanceByAccountId(String accountId, String balanceId) throws BalanceIdNotFoundException, AccountIdMismatchException {
        Balance balance = repository.findById(balanceId).orElseThrow(
                () -> new BalanceIdNotFoundException("Balance ID not available")
        );
        if (accountId.equals(balance.getAccountId())) {
            repository.deleteById(balanceId);
        } else {
            throw new AccountIdMismatchException("Account ID not available");
        }
        return accountId;

    }

    @Override
    public BalanceDto updateBalance(String accountId, String balanceId, BalanceDto balanceDto) {
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


    @Override
    public BalanceDto createBalance(String accountId, BalanceDto balanceDto) {
        if ((accountId.equals(balanceDto.getAccountId()))) {
            if (repository.findByAccountId(accountId) == null) {
                Balance balance = mapper.dtoToEntity(balanceDto);
                //Balance balanceResult = repository.save(balance);
                log.error("Created new Balance successfully");
                return mapper.entityToDto(repository.save(balance));
            } else {
                throw new BalanceAlreadyExistException("Balance already exsists exception");
            }

        } else {
            throw new BalanceAlreadyExistException("This AccountId Id should be match");
        }


    }


    @Override

    public BalanceDto getBalanceIdByAccountId(String accountId, String balanceID) throws BalanceIdNotFoundException, AccountIdMismatchException {
        Balance balance = repository.findById(balanceID).orElseThrow(
                () -> new BalanceIdNotFoundException("Balance id not available")
        );
        if (accountId.equals(balance.getAccountId())) {
            return mapper.entityToDto(balance);
        } else {
            throw new AccountIdMismatchException("Account Id not available");
        }
    }

    public Object deleteBalance(Object any, Object any1) {
        return deleteBalance("2", "2");

    }

}



