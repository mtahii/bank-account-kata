package com.bank.exceptions;

/**
 * Exception thrown when an invalid amount is provided for a banking operation.
 * This includes zero or negative amounts for deposits and withdrawals.
 */
public class InvalidTransactionException extends RuntimeException {
    public InvalidTransactionException(String message) {
        super(message);
    }
} 