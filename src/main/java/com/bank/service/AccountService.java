package com.bank.service;

import com.bank.domain.Account;

import lombok.Getter;

import java.math.BigDecimal;

/**
 * Service class that acts as a façade for Account operations.
 * This class delegates all operations to an internal Account instance
 * and does not contain any business logic.
 */

 @Getter
public class AccountService {
    private final Account account;
    private final StatementPrinter printer;

    public AccountService(Account account, StatementPrinter printer) {
        this.account = account;
        this.printer = printer;
    }

    /**
     * Deposits the specified amount into the account.
     *
     * @param amount the amount to deposit
     */
    public void deposit(BigDecimal amount) {
        account.deposit(amount);
    }

    /**
     * Withdraws the specified amount from the account.
     *
     * @param amount the amount to withdraw
     */
    public void withdraw(BigDecimal amount) {
        account.withdraw(amount);
    }

    /**
     * Prints the account statement.
     *
     * @return the formatted statement
     */
    public String printStatement() {
        return printer.print(account.getTransactionHistory());
    }
} 