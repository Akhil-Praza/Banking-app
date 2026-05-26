package com.example.banking_app.service.impl;

import com.example.banking_app.dto.AccountDto;
import com.example.banking_app.entity.Account;
import com.example.banking_app.mapper.AccountMappar;
import com.example.banking_app.repository.AccountRepository;
import com.example.banking_app.service.AccountService;
import jakarta.persistence.Entity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = AccountMappar.mapToAccount(accountDto);
        Account savedAccount = accountRepository.save(account);
        return AccountMappar.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto getAccountById(Long id) {
       Account account= accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account not found with id or not exits: " + id));
        return AccountMappar.mapToAccountDto(account);
    }

    @Override
    public AccountDto deposit(Long id, Double amount) {
        Account account= accountRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found with id or not exits: " + id));

        account.setBalance(account.getBalance() + amount);
        Account updatedAccount = accountRepository.save(account);
        return AccountMappar.mapToAccountDto(updatedAccount);
    }

    @Override
    public AccountDto withdraw(Long id, Double amount) {
        Account account= accountRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found with id or not exits: " + id));
        if (account.getBalance() < amount) {
            throw new RuntimeException("Insufficient balance in the account with id: " + id);
        }
        account.setBalance(account.getBalance() - amount);
        Account updatedAccount = accountRepository.save(account);
        return AccountMappar.mapToAccountDto(updatedAccount);

    }

    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream()
                .map((account) -> AccountMappar.mapToAccountDto(account))
                .collect(Collectors.toList());


    }
}
