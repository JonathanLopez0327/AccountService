package com.budgetproject.accountservice.exception;

import com.budgetproject.accountservice.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityCustomExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(AccountServiceCustomException.class)
    public ResponseEntity<ErrorResponse> handlerAccountServiceException(AccountServiceCustomException exception) {
        return new ResponseEntity<>(ErrorResponse.builder()
                .errorMessage(exception.getMessage())
                .errorCode(exception.getErrorCode())
                .build(), HttpStatus.valueOf(exception.getStatus()));
    }
}
