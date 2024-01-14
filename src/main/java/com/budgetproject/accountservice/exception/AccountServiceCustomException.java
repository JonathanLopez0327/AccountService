package com.budgetproject.accountservice.exception;

import lombok.Data;

@Data
public class AccountServiceCustomException extends RuntimeException {
    private String errorCode;
    private int status;

    public AccountServiceCustomException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public AccountServiceCustomException(String message, String errorCode, int status) {
        super(message);
        this.errorCode = errorCode;
        this.status = status;
    }
}
