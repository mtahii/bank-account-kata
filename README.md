# Bank Account Kata

A simple implementation of a bank account system that allows deposits, withdrawals, and statement printing.

## Features

- Deposit money into an account
- Withdraw money from an account
- View account statement with transaction history
- Exception handling for invalid operations

## Project Structure

```
src/
├── main/java/com/bank/
│   ├── domain/
│   │   ├── Account.java
│   │   ├── Transaction.java
│   │   └── TransactionType.java
│   ├── exceptions/
│   │   ├── InsufficientFundsException.java
│   │   └── InvalidTransactionException.java
│   └── service/
│       ├── AccountService.java
│       └── StatementPrinter.java
└── test/java/com/bank/
    ├── domain/
    │   └── AccountTest.java
    └── service/
        ├── AccountServiceTest.java
        └── StatementPrinterTest.java
```

## Setup

1. Ensure you have Java installed
2. Clone the repository
3. Build the project:
   ```bash
   mvn clean install
   ```

## Running Tests

To run the tests:
```bash
mvn test
```

## Requirements

- Java 17 or higher
- Maven 3.6 or higher 