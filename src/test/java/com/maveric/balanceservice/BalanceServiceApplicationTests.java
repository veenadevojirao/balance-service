package com.maveric.balanceservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.maveric.balanceservice.dto.BalanceDto;
import com.maveric.balanceservice.entity.Balance;
import com.maveric.balanceservice.enums.Currency;
import org.joda.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public
class BalanceServiceApplicationTests {
	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	@Test
	void testDoSomething() {  // Noncompliant
		assertTrue(true);

	}


	public static Balance getBalance ()
	{
		return Balance.builder()
				.accountId("1234")
				.currency(Currency.DOLLAR)
				.amount(1200)
				.build();
	}
	public static BalanceDto getBalanceDto ()
	{
		return BalanceDto.builder()
				.accountId("1234")
				.amount(1234)
				.currency(Currency.DOLLAR)
				.build();
	}



}

