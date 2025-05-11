package com.bank.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Represents a bank transaction as an immutable value object.
 * Each transaction records the type of operation, amount, resulting balance,
 * and the timestamp when it occurred.
 */
@Getter
@RequiredArgsConstructor
public final class Transaction {
    private final TransactionType type;
    private final BigDecimal amount;
    private final BigDecimal balance;
    private final LocalDateTime timestamp = LocalDateTime.now();

} 