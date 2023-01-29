package com.maveric.balanceservice.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


//public class AccountIdMismatchException extends RuntimeException{
//    public AccountIdMismatchException(String accountId){
//        super("User not belongs to account id "+ accountId);
//    }
//
//    public AccountIdMismatchException(String accountId,String message) {
//        super(accountId + " :" + message);
//
//    }
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class AccountIdMismatchException extends Exception {
    public AccountIdMismatchException(String message) {
        super(message);

    }
}
