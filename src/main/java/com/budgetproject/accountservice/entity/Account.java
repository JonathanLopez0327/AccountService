package com.budgetproject.accountservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long accountId;

    @Column(name = "ACCOUNT_NAME")
    private String name;

    @Column(name = "ACCOUNT_DESCRIPTION")
    private String description;

    @Column(name = "ACCOUNT_BANK_ID")
    private String bankId;

    @Column(name = "AMOUNT")
    private long amount;
}
