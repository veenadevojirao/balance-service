package com.maveric.balanceservice.mapper;

import com.maveric.balanceservice.dto.BalanceDto;
import com.maveric.balanceservice.entity.Balance;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static com.maveric.balanceservice.BalanceServiceApplicationTests.getBalance;
import static com.maveric.balanceservice.controller.BalanceControllerTest.getBalanceDto;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BalanceMapperImplTest {
    private BalanceMapperImpl balanceMapper = new BalanceMapperImpl();

    @Test
    void map() {
        BalanceDto balanceDto=getBalanceDto();
        Balance balance = balanceMapper.dtoToEntity(balanceDto);
        assertEquals(getBalanceDto().getAccountId(), balance.getAccountId());
        assertEquals(getBalanceDto().get_id(), balance.get_id());
    }

    @Test
    void testMap() {
        Balance balance=getBalance();
        BalanceDto balancedto = balanceMapper.entityToDto(balance);
        assertEquals(getBalance().getAccountId(), balancedto.getAccountId());
        assertEquals(getBalance().get_id(), balancedto.get_id());
    }
    @Test
    void mapToEntity_failure() {
        List<Balance> balances = balanceMapper.dtoToEntity(Arrays.asList());
        assertEquals(0,balances.size());
    }
    @Test
    void mapToModel() {

        List<Balance> balances = balanceMapper.dtoToEntity(Arrays.asList(getBalanceDto(),getBalanceDto()));
        assertEquals(2,balances.size());
    }
    @Test
    void mapToDto_failure() {
        List<BalanceDto> balanceDtos = balanceMapper.entityToDto(Arrays.asList());
        assertEquals(0,balanceDtos.size());
    }

}
