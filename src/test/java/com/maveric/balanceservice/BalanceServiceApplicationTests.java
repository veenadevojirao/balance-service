package com.maveric.balanceservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.maveric.balanceservice.dto.AccountDto;
import com.maveric.balanceservice.dto.BalanceDto;
import com.maveric.balanceservice.dto.UserDto;
import com.maveric.balanceservice.entity.Balance;
import com.maveric.balanceservice.enums.Currency;

import com.maveric.balanceservice.enums.Type;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
public
class
BalanceServiceApplicationTests {

	public static final String apiV1 = "/api/v1/accounts/1234/balances";
	public static final String invalidApiV1 = "/api/v1/accounts/0000/balances/0000";

	@Test
	void contextLoads() {
		assertTrue(true);
	}
	public static BalanceDto getBalanceDto()
	{
		return  BalanceDto.builder()
				._id("1")
				.accountId("123")
				.amount(2000)
				.currency(Currency.INR)
				.build();
	}
	public static Balance getBalance()
	{
		return  Balance.builder()
				._id("1")
				.accountId("123")
				.amount(2000)
				.currency(Currency.INR)
				.build();
	}
	public static UserDto getUserDto()
	{
		return UserDto.builder()
				.email("maveric@gmail.com")
				.id("1234")
				.build();
	}
	public static AccountDto getAccountDto(){
//	AccountDto accountDto=new AccountDto();
		AccountDto accountDto = AccountDto.builder().build();
		accountDto.set_id("1234");
		accountDto.setCustomerId("1234");

		return accountDto;
	}
	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}




