package com.maveric.balanceservice.exception;

public class BalanceIDNotFoundException extends RuntimeException{
    public BalanceIDNotFoundException(String message) {
        super(message);
    }
}
