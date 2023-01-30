package com.maveric.balanceservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.maveric.balanceservice.dto.BalanceDto;
import com.maveric.balanceservice.entity.Balance;
import com.maveric.balanceservice.enums.Currency;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
public
class BalanceServiceApplicationTests {

	public static final String APIV1 = "/api/v1/accounts/123/balances";

	@Test
	void contextLoads() {
		assertTrue(true);
	}

	public static Balance getBalance() {
		return Balance.builder()
				._id("1")
				.accountId("1234")
				.currency(Currency.DOLLAR)
				.amount(1200)
				.build();
	}

	public static BalanceDto getBalanceDto() {
		return BalanceDto.builder()
				._id("1")
				.accountId("1234")
				.amount(1234)
				.currency(Currency.DOLLAR)
				.build();

	}


}