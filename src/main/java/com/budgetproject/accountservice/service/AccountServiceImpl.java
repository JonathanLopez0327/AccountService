package com.budgetproject.accountservice.service;

import com.budgetproject.accountservice.entity.Account;
import com.budgetproject.accountservice.exception.AccountServiceCustomException;
import com.budgetproject.accountservice.model.AccountRequest;
import com.budgetproject.accountservice.model.AccountResponse;
import com.budgetproject.accountservice.repository.AccountRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import static org.springframework.beans.BeanUtils.copyProperties;

@Service
@Log4j2
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;


    @Override
    public long recordAccount(AccountRequest accountRequest) {
        log.info("Recording new account");
        Account account = Account.builder()
                .name(accountRequest.getName())
                .description(accountRequest.getDescription())
                .bankId(accountRequest.getBankId())
                .amount(accountRequest.getAmount())
                .build();
        accountRepository.save(account);
        log.info("Account recorder");
        return account.getAccountId();
    }

    @Override
    public List<AccountResponse> listAllAccounts() {
        log.info("Getting all accounts");
        List<Account> accountList = accountRepository.findAll();
        return accountList
                .stream()
                .map(account -> {
                    AccountResponse accountResponse = new AccountResponse();
                    copyProperties(account, accountResponse);
                    return accountResponse;
                }).toList();
    }

    @Override
    public AccountResponse listAccountById(long accountId) {
        log.info("Getting accoutn by id {}", accountId);
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountServiceCustomException("Account with given id not found", "ACCOUNT_NOT_FOUND"));
        AccountResponse accountResponse = new AccountResponse();
        copyProperties(account, accountResponse);
        return accountResponse;
    }
}
