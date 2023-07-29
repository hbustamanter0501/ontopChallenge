# Wallet Transaction Service

This is the README file for the wallet transaction service. The service is designed with a Hexagonal Architecture.

### Prerequisites

Gradle must be installed on your machine. You can download Gradle from the official website and follow the installation instructions specific to your operating system.

### DDBB Access

host: database-1.chrfrhvwcmcq.us-east-2.rds.amazonaws.com

port: 5432

database: postgres

Username and password are in the application.properties file. Is Postgres DB, recommend to use PGAdmin.

### Build Steps

1. Clone this repository to your local machine.
   ```
   git clone https://github.com/hbustamanter0501/ontopChallenge.git
   ```
2. Run WalletTransactionApplication on IDE

### Flow

1. First use the Account REST API endpoint to create a new Account with a valid userId.
2. Use that userId to generate a wallet transaction with the WalletTransaction REST API endpoint.

Hint: There are some Accounts created in DDBB that you can also use for testing purpouse.