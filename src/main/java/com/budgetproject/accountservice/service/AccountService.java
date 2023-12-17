package com.budgetproject.accountservice.service;

import com.budgetproject.accountservice.model.AccountRequest;
import com.budgetproject.accountservice.model.AccountResponse;

import java.util.List;

public interface AccountService {
    long recordAccount(AccountRequest accountRequest);

    List<AccountResponse> listAllAccounts();

    AccountResponse listAccountById(long accountId);

    void debitAmount(long accountId, long amount);

    void creditAmount(long accountId, long amount);
}
