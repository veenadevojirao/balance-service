package com.maveric.balanceservice.exception;

import com.maveric.balanceservice.dto.ErrorDto;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExceptionControllerAdvisorTest {
    private  ExceptionControllerAdvisor controllerAdvisor = new ExceptionControllerAdvisor();
    @Test
    void shouldReturnErrorWhenAccountMismatch() {
        AccountIdMismatchException exception = new AccountIdMismatchException("Account id is not belongs to authenticate user");
        ResponseEntity<ErrorDto> error = ExceptionControllerAdvisor.accountUserMismatch(exception);
        assertEquals(String.valueOf(HttpStatus.NOT_FOUND),error.getBody().getCode());
    }
    @Test
    void CustomerIDNotFoundExistsException() {
        CustomerIDNotFoundExistsException exception = new CustomerIDNotFoundExistsException("Customer ID Not found");
        ErrorDto error = controllerAdvisor.handleCustomerIDNotFoundExistsException(exception);
        assertEquals("404",error.getCode());
    }
    @Test
    void handleBalanceIdNotFoundException() {
        BalanceIdNotFoundException exception = new BalanceIdNotFoundException("BalanceId Not found");
        ErrorDto error = controllerAdvisor.handleBalanceIdNotFoundException(exception);
        assertEquals("404",error.getCode());
    }
    @Test
    void handleBalanceNotFoundException() {
        BalanceNotFoundException exception = new BalanceNotFoundException("Balance Not found");
        ErrorDto error = controllerAdvisor.handleBalanceNotFoundException(exception);
        assertEquals("404",error.getCode());
    }
    @Test
    void handleBalanceAlreadyExistException() {
        BalanceAlreadyExistException exception = new BalanceAlreadyExistException("BalanceAlready Exists Not found");
        ErrorDto error = controllerAdvisor.handleBalanceAlreadyExistException(exception);
        assertEquals("400",error.getCode());
    }

}
