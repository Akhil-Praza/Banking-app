package com.example.banking_app.controller;

import com.example.banking_app.dto.AccountDto;
import com.example.banking_app.service.AccountService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
     }

     // add Account for rest api
    @PostMapping
    public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto){
        return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
    }

    // get account by id for rest api
     @GetMapping("/{id}")
     public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id){
        AccountDto accountDto = accountService.getAccountById(id);
         return ResponseEntity.ok(accountDto);
     }

     // deposit for rest api
     @PutMapping("/{id}/deposit")
     public ResponseEntity<AccountDto> deposit(@PathVariable Long id, @RequestBody Map<String,Double> request){
        Double amount = request.get("amount");
        AccountDto accountDto = accountService.deposit(id, amount);
        return ResponseEntity.ok(accountDto);
     }

     // withdraw for rest api
     @PutMapping("/{id}/withdraw")
     public ResponseEntity<AccountDto> withdraw(@PathVariable Long id, @RequestBody Map<String,Double> request) {
         Double amount = request.get("amount");
         AccountDto accountDto = accountService.withdraw(id, amount);
         return ResponseEntity.ok(accountDto);
     }
     // get all accounts for rest api
        @GetMapping
        public ResponseEntity<List<AccountDto>> getAllAccounts() {
            List<AccountDto> accounts = accountService.getAllAccounts();
            return ResponseEntity.ok(accounts);
        }
}
