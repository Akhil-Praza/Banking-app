package com.example.banking_app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//public class AccountDto {
//    private Long id;
//    private String accountHolderName;
//    private Double balance;
//
//
//}

// using here record to create immutable class and avoid boilerplate code
public record AccountDto(Long id, String accountHolderName, Double balance) {
}