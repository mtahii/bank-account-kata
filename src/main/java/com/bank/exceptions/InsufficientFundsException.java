package com.bank.exceptions;

/**
 * Exception thrown when an account has insufficient funds for a withdrawal operation.
 */
public class InsufficientFundsException extends RuntimeException {
    public InsufficientFundsException(String message) {
        super(message);
    }
} 