package com.bank.domain;

import com.bank.exceptions.InsufficientFundsException;
import com.bank.exceptions.InvalidTransactionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {
    private Account account;
    

    @BeforeEach
    void setUp() {
        account = new Account();
    }

    @Test
    void shouldCreateAccountWithZeroBalance() {
        assertEquals(BigDecimal.ZERO, account.getBalance());
        assertTrue(account.getTransactionHistory().isEmpty());
    }

    @Test
    void shouldStoreTransactionAndUpdateBalanceAfterDeposit() {
        BigDecimal amount = new BigDecimal("100.00");
        account.deposit(amount);
        
        assertEquals(amount, account.getBalance());
        assertEquals(1, account.getTransactionHistory().size());
        
        Transaction transaction = account.getTransactionHistory().get(0);
        assertEquals(TransactionType.DEPOSIT, transaction.getType());
        assertEquals(amount, transaction.getAmount());
        assertEquals(amount, transaction.getBalance());
    }

    @Test
    void shouldStoreWithdrawalTransactionAndUpdateBalanceCorrectly() {
        BigDecimal depositAmount = new BigDecimal("100.00");
        BigDecimal withdrawAmount = new BigDecimal("50.00");
        
        account.deposit(depositAmount);
        account.withdraw(withdrawAmount);
        
        assertEquals(new BigDecimal("50.00"), account.getBalance());
        assertEquals(2, account.getTransactionHistory().size());
        
        Transaction depositTransaction = account.getTransactionHistory().get(0);
        assertEquals(TransactionType.DEPOSIT, depositTransaction.getType());
        assertEquals(depositAmount, depositTransaction.getAmount());
        assertEquals(depositAmount, depositTransaction.getBalance());

        Transaction withdrawalTransaction = account.getTransactionHistory().get(1);
        assertEquals(TransactionType.WITHDRAWAL, withdrawalTransaction.getType());
        assertEquals(withdrawAmount, withdrawalTransaction.getAmount());
        assertEquals(new BigDecimal("50.00"), withdrawalTransaction.getBalance());
    }

    @Test
    void shouldNotAllowNegativeDeposit() {
        assertThrows(InvalidTransactionException.class, () -> 
            account.deposit(new BigDecimal("-100.00"))
        );
        assertTrue(account.getTransactionHistory().isEmpty());
    }

    @Test
    void shouldNotAllowNegativeWithdrawal() {
        assertThrows(InvalidTransactionException.class, () -> 
            account.withdraw(new BigDecimal("-100.00"))
        );
        assertTrue(account.getTransactionHistory().isEmpty());
    }

    @Test
    void shouldNotAllowWithdrawalWithInsufficientFunds() {
        assertThrows(InsufficientFundsException.class, () -> 
            account.withdraw(new BigDecimal("100.00"))
        );
        assertTrue(account.getTransactionHistory().isEmpty());
    }
} 