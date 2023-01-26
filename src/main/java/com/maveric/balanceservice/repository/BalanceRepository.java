package com.maveric.balanceservice.repository;

import com.maveric.balanceservice.dto.BalanceDto;
import com.maveric.balanceservice.entity.Balance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BalanceRepository extends MongoRepository<Balance,String>{

//    Balance findByAccountId(String accountId);
    Page<Balance> findByAccountId(Pageable pageable, String accountId);
}