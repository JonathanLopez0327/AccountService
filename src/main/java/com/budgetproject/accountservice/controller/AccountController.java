package com.budgetproject.accountservice.controller;

import com.budgetproject.accountservice.model.AccountRequest;
import com.budgetproject.accountservice.model.AccountResponse;
import com.budgetproject.accountservice.service.AccountService;
import jakarta.validation.Valid;
import jakarta.ws.rs.POST;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping
    public ResponseEntity<Long> recordAccount(@RequestBody @Valid AccountRequest accountRequest) {
        long accountId = accountService.recordAccount(accountRequest);
        return new ResponseEntity<>(accountId, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AccountResponse>> listAllAccounts() {
        List<AccountResponse> accountResponses = accountService.listAllAccounts();
        return new ResponseEntity<>(accountResponses, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountResponse> listAccountById(@PathVariable("id") long accountId) {
        AccountResponse accountResponse = accountService.listAccountById(accountId);
        return new ResponseEntity<>(accountResponse, HttpStatus.OK);
    }
}
