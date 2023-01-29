package com.maveric.balanceservice.repository;

import com.maveric.balanceservice.entity.Balance;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.List;

import static com.maveric.balanceservice.BalanceServiceApplicationTests.getBalance;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataMongoTest
public class BalanceRepositoryTest {
    @Autowired
    BalanceRepository balanceRepository;
    @Test
    public void testSave() {
        Balance balance = balanceRepository.save(getBalance());
        assertEquals("1234",balance.getAccountId());
    }

@Test

    public void testFindAll() {
        List<Balance> balance = balanceRepository.findAll();
        assertNotNull(balance);
        assert(balance.size()>0);
    }
}
