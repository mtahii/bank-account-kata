package com.bank.service;

import com.bank.domain.Account;
import com.bank.exceptions.InsufficientFundsException;
import com.bank.exceptions.InvalidTransactionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class AccountServiceTest {
    private AccountService accountService;

    @BeforeEach
    void setUp() {
        accountService = new AccountService(new Account(), new StatementPrinter());
    }

    @Test
    void shouldIncludeDepositInPrintedStatement() {
        BigDecimal amount = new BigDecimal("100.00");
        accountService.deposit(amount);
        
        String statement = accountService.printStatement();
        assertTrue(statement.contains("100.00"));
        assertTrue(statement.contains("DEPOSIT"));
    }

    @Test
    void shouldIncludeWithdrawalInPrintedStatement() {
        accountService.deposit(new BigDecimal("100.00"));
        accountService.withdraw(new BigDecimal("50.00"));
        
        String statement = accountService.printStatement();
        assertTrue(statement.contains("50.00"));
        assertTrue(statement.contains("WITHDRAWAL"));
    }

    @Test
    void shouldNotAllowNegativeDeposit() {
        assertThrows(InvalidTransactionException.class, () -> 
            accountService.deposit(new BigDecimal("-100.00"))
        );
    }

    @Test
    void shouldNotAllowNegativeWithdrawal() {
        assertThrows(InvalidTransactionException.class, () -> 
            accountService.withdraw(new BigDecimal("-100.00"))
        );
        assertFalse(accountService.printStatement().contains("WITHDRAWAL"));
    }

    @Test
    void shouldNotAllowWithdrawalWithInsufficientFunds() {
        assertThrows(InsufficientFundsException.class, () -> 
            accountService.withdraw(new BigDecimal("100.00"))
        );
        assertFalse(accountService.printStatement().contains("WITHDRAWAL"));
    }
} 