package com.bank.domain;

/**
 * Represents the possible types of bank transactions.
 * This enum defines the two fundamental operations that can be performed on a bank account:
 * deposits and withdrawals.
 */
public enum TransactionType {
    /**
     * Represents a deposit operation, where money is added to the account.
     */
    DEPOSIT,
    
    /**
     * Represents a withdrawal operation, where money is removed from the account.
     */
    WITHDRAWAL
} 