package com.bank.service;


import com.bank.domain.Transaction;
import com.bank.domain.TransactionType;


import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Service responsible for formatting and printing bank account statements.
 */
public class StatementPrinter {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final String STATEMENT_HEADER = "DATE | OPERATION | AMOUNT | BALANCE";

    /**
     * Generates a formatted statement for the given account.
     * The statement includes a header and a list of all transactions,
     * with each transaction showing its date, type, amount, and resulting balance.
     *
     * @param transactions the transactions to generate the statement for
     * @return a formatted string containing the account statement
     */
    public String print(List<Transaction> transactions) {
            
        StringBuilder statement = new StringBuilder();
        statement.append(STATEMENT_HEADER).append("\n");
        
        for (Transaction transaction : transactions) {
            statement.append(formatTransaction(transaction)).append("\n");
        }
        
        return statement.toString();
    }

    /**
     * Formats a single transaction into a string representation.
     *
     * @param transaction the transaction to format
     * @return a formatted string representing the transaction
     */
    private String formatTransaction(Transaction transaction) {
        String formatted = String.format(
            "%s | %s | %s | %s",
            transaction.getTimestamp().format(DATE_FORMATTER),
            transaction.getType(),
            formatAmount(transaction.getAmount(), transaction.getType()),
            transaction.getBalance()
        );
            
        return formatted;
    }

    /**
     * Formats an amount into a string representation.
     * For withdrawals, the amount is prefixed with a minus sign.
     *
     * @param amount the amount to format
     * @param type the type of transaction (null for balance)
     * @return a formatted string representation of the amount
     */
    private String formatAmount(BigDecimal amount, TransactionType type) {
        if (type == TransactionType.WITHDRAWAL) {
            return "-" + amount.toString();
        }
        return amount.toString();
    }
} 