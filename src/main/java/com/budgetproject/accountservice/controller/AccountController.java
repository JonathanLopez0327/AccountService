package com.budgetproject.accountservice.controller;

import com.budgetproject.accountservice.model.AccountRequest;
import com.budgetproject.accountservice.model.AccountResponse;
import com.budgetproject.accountservice.service.AccountService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountService accountService;


    @RolesAllowed({"B_Administrator","B_Regular_Customer"})
    @PostMapping
    public ResponseEntity<Long> recordAccount(@RequestBody @Valid AccountRequest accountRequest) {
        long accountId = accountService.recordAccount(accountRequest);
        return new ResponseEntity<>(accountId, HttpStatus.CREATED);
    }

    @RolesAllowed({"B_Administrator","B_Regular_Customer", "B_Auditor"})
    @GetMapping
    public ResponseEntity<List<AccountResponse>> listAllAccounts() {
        List<AccountResponse> accountResponses = accountService.listAllAccounts();
        return new ResponseEntity<>(accountResponses, HttpStatus.OK);
    }

    @RolesAllowed({"B_Administrator","B_Regular_Customer", "B_Auditor"})
    @GetMapping("/{id}")
    public ResponseEntity<AccountResponse> listAccountById(@PathVariable("id") long accountId) {
        AccountResponse accountResponse = accountService.listAccountById(accountId);
        return new ResponseEntity<>(accountResponse, HttpStatus.OK);
    }

    @RolesAllowed({"B_Administrator","B_Regular_Customer", "SCOPE_internal"})
    @PutMapping("/debitAmount/{id}")
    public ResponseEntity<Void> debitAmount(
            @PathVariable("id") long accountId,
            @RequestParam long amount
    ) {
        accountService.debitAmount(accountId, amount);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RolesAllowed({"B_Administrator","B_Regular_Customer", "SCOPE_internal"})
    @PutMapping("/creditAmount/{id}")
    public ResponseEntity<Void> creditAmount(
            @PathVariable("id") long accountId,
            @RequestParam long amount
    ) {
        accountService.creditAmount(accountId, amount);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
