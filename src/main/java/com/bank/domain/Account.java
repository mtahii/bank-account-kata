package com.bank.domain;

import com.bank.exceptions.InsufficientFundsException;
import com.bank.exceptions.InvalidTransactionException;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a bank account with the ability to deposit and withdraw money.
 * This class maintains the account balance and transaction history.
 * All monetary values are handled using BigDecimal for precise decimal arithmetic.
 */
@Getter
public class Account {
    private final List<Transaction> transactions;
    private BigDecimal balance;

    /**
     * Creates a new account with zero balance and empty transaction history.
     */
    public Account() {
        this.transactions = new ArrayList<>();
        this.balance = BigDecimal.ZERO;
    }

    /**
     * Deposits the specified amount into the account.
     * The amount must be positive.
     *
     * @param amount the amount to deposit, must be positive
     * @throws InvalidTransactionException if the amount is zero or negative
     */
    public void deposit(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidTransactionException("Deposit amount must be positive");
        }
        
        balance = balance.add(amount);
        transactions.add(new Transaction(TransactionType.DEPOSIT, amount, balance));
    }

    /**
     * Withdraws the specified amount from the account.
     * The amount must be positive and not exceed the current balance.
     *
     * @param amount the amount to withdraw, must be positive
     * @throws InvalidTransactionException if the amount is zero or negative
     * @throws InsufficientFundsException if the account has insufficient funds
     */
    public void withdraw(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidTransactionException("Withdrawal amount must be positive");
        }
        if (amount.compareTo(balance) > 0) {
            throw new InsufficientFundsException("Insufficient funds");
        }

        balance = balance.subtract(amount);
        transactions.add(new Transaction(TransactionType.WITHDRAWAL, amount, balance));
    }

    /**
     * Returns an unmodifiable view of the transaction history.
     *
     * @return an unmodifiable list of transactions
     */
    public List<Transaction> getTransactionHistory() {
        return Collections.unmodifiableList(transactions);
    }
} 