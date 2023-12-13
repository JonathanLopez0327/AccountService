package com.budgetproject.accountservice.exception;

import lombok.Data;

@Data
public class AccountServiceCustomException extends RuntimeException {
    private String errorCode;

    public AccountServiceCustomException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
