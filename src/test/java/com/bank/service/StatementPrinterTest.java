package com.bank.service;

import com.bank.domain.Transaction;
import com.bank.domain.TransactionType;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class StatementPrinterTest {
    private StatementPrinter printer;

    @BeforeEach
    void setUp() {
        printer = new StatementPrinter();
    }

    @Test
    void shouldPrintEmptyStatement() {
        String statement = printer.print(List.of());
        assertTrue(statement.trim().equals("DATE | OPERATION | AMOUNT | BALANCE"));
    }

    @Test
    void shouldPrintWithTransactions() {
        List<Transaction> transactions = List.of(
            new Transaction(TransactionType.DEPOSIT, new BigDecimal("100.00"), new BigDecimal("100.00")),
            new Transaction(TransactionType.WITHDRAWAL, new BigDecimal("50.00"), new BigDecimal("50.00"))
        );

        String statement = printer.print(transactions);

        assertTrue(statement.startsWith("DATE | OPERATION | AMOUNT | BALANCE"));
        assertTrue(statement.contains("DEPOSIT"));
        assertTrue(statement.contains("WITHDRAWAL"));
        assertTrue(statement.contains("100.00"));
        assertTrue(statement.contains("-50.00"));
        assertTrue(statement.contains("50.00"));
    }
} 