package financialprob;
/*
We are building a program to manage a bank's transaction processing system.

The bank imports transactions from multiple sources such as partner institutions,
customer statement uploads, ATM systems, payment gateways, and legacy migrations.

Each transaction belongs to an account and represents a financial event.

The system must:
- Store imported transactions.
- Maintain running balances for accounts.
- Maintain running balances per transaction source.
- Detect duplicate transactions.
- Generate account summaries.
- Generate transaction-type summaries.
- Generate source-based summaries.
- Identify accounts whose balance has become negative.
- Support transaction reversal.

Definitions:

* A "transaction" is an object representing a single financial event.
  It has:
    - transaction ID
    - account ID
    - amount
    - transaction type
    - timestamp
    - description
    - source

* An "import system" is a class used to manage all imported transactions.

* A "credit" is a transaction type that increases the account balance.
    DEPOSIT and INTEREST are credits.

* A "debit" is a transaction type that decreases the account balance.
    WITHDRAWAL, FEE, and TRANSFER are debits.

* A transaction amount is always positive.

* "netChange" represents the total credits minus total debits.

* A transaction ID must be unique.
  If a transaction with the same transaction ID has already been imported,
  the new transaction must NOT be added.

* A transaction can be reversed.
  Reversing a credit creates the equivalent debit effect.
  Reversing a debit creates the equivalent credit effect.

* A reversed transaction must not be reversed more than once.

------------------------------------------------------------
TASK 1
------------------------------------------------------------

Read through and understand the existing code.

The provided tests are failing because ImportSystem is incomplete.

Make the necessary changes to ImportSystem so that the existing tests pass.

Do not change the Transaction class or the tests.

------------------------------------------------------------
TASK 2
------------------------------------------------------------

The system currently stores transactions but does not properly maintain
account balances.

Add:

2-1) A balances attribute:

    Map<Integer, Double> balances

The key is accountId and the value is the current running balance.

The map must initially be empty.

2-2) Modify addTransaction(transaction):

    - Add the transaction to transactions.
    - Initialize a new account with balance 0.0.
    - Credits increase the balance.
    - Debits decrease the balance.

2-3) Duplicate transaction handling:

If a transaction with the same transactionId already exists:

    - Do not add the transaction.
    - Do not modify any balance.
    - Return false.

Otherwise:

    - Add the transaction.
    - Update the balance.
    - Return true.

Therefore change:

    void addTransaction(Transaction transaction)

to:

    boolean addTransaction(Transaction transaction)

------------------------------------------------------------
TASK 3
------------------------------------------------------------

Implement:

    Map<String, Map<String, Object>> getSourceSummary()

The result must group transactions by source.

Example:

{
    "SRC_A": {
        "count": 4,
        "total_amount": 1000.0
    },

    "SRC_B": {
        "count": 2,
        "total_amount": 300.0
    }
}

Rules:

- "count" = number of transactions from that source.
- "total_amount" = sum of transaction amounts.
- Aggregate across all accounts.
- Only include sources that actually contain transactions.

------------------------------------------------------------
TASK 4
------------------------------------------------------------

Implement:

    Map<Integer, Map<String, Object>> getAccountTypeSummary()

This method must group transactions first by account and then by
transaction type.

Example:

{
    101: {
        "DEPOSIT": {
            "count": 2,
            "total_amount": 800.0
        },
        "WITHDRAWAL": {
            "count": 1,
            "total_amount": 100.0
        }
    },

    202: {
        "DEPOSIT": {
            "count": 1,
            "total_amount": 500.0
        }
    }
}

------------------------------------------------------------
TASK 5
------------------------------------------------------------

Implement:

    List<Integer> getNegativeBalanceAccounts()

Return all account IDs whose current balance is below 0.

Example:

balances:

101 -> 500
202 -> -100
303 -> 0
404 -> -50

Result:

[202, 404]

Rules:

- Only accounts with balance < 0 are included.
- Balance exactly 0 must NOT be included.
- The returned account IDs must be sorted in ascending order.

------------------------------------------------------------
TASK 6
------------------------------------------------------------

Implement:

    Map<String, Object> getAccountSummary(int accountId)

The summary must contain:

    total_credits
    total_debits
    net_change
    transaction_count
    current_balance
    unique_sources

Example:

{
    "total_credits": 1000.0,
    "total_debits": 300.0,
    "net_change": 700.0,
    "transaction_count": 5,
    "current_balance": 700.0,
    "unique_sources": 2
}

Definitions:

total_credits:
    Sum of DEPOSIT and INTEREST.

total_debits:
    Sum of WITHDRAWAL, FEE and TRANSFER.

net_change:
    total_credits - total_debits.

transaction_count:
    Number of transactions belonging to the account.

current_balance:
    Running balance stored in balances.

unique_sources:
    Number of different sources that supplied transactions for this account.

If the account does not exist:

    current_balance = 0.0
    transaction_count = 0
    total_credits = 0.0
    total_debits = 0.0
    net_change = 0.0
    unique_sources = 0

------------------------------------------------------------
TASK 7
------------------------------------------------------------

Implement transaction reversal.

Add:

    boolean reverseTransaction(String transactionId)

Rules:

1. Find the transaction using transactionId.

2. If the transaction does not exist:
       return false

3. If the transaction has already been reversed:
       return false

4. When reversing:

   A DEPOSIT becomes a balance decrease.

   An INTEREST becomes a balance decrease.

   A WITHDRAWAL becomes a balance increase.

   A FEE becomes a balance increase.

   A TRANSFER becomes a balance increase.

5. The original transaction must remain inside transactions.

6. Do NOT create a second Transaction object.

7. Track reversed transaction IDs using:

       Set<String> reversedTransactionIds

8. Return true when the reversal succeeds.

Example:

Initial:

DEPOSIT 500

balance = 500

reverseTransaction("t001")

balance = 0

Calling:

reverseTransaction("t001")

again must return false.

------------------------------------------------------------
TASK 8
------------------------------------------------------------

Implement:

    List<Transaction> getTransactionsForAccount(
        int accountId,
        int startTimestamp,
        int endTimestamp
    )

Return all transactions satisfying:

    accountId matches
    AND
    timestamp >= startTimestamp
    AND
    timestamp <= endTimestamp

The result must be sorted by:

1. timestamp ascending
2. transactionId ascending if timestamps are equal

Do not modify the original transactions list.

------------------------------------------------------------
TASK 9
------------------------------------------------------------

Implement:

    Map<Integer, Double> getBalancesBySource()

This is intentionally different from getSourceSummary().

For every transaction, calculate its effect on the account balance
and aggregate the net effect by source.

Example:

SRC_A:
    DEPOSIT 500
    WITHDRAWAL 100

SRC_B:
    DEPOSIT 300
    FEE 50

Result:

{
    "SRC_A": 400.0,
    "SRC_B": 250.0
}

Important:

This is NOT the current account balance.

It represents the net financial effect of each source across all accounts.

------------------------------------------------------------
TASK 10
------------------------------------------------------------

Implement:

    String getLargestTransactionSource()

Return the source whose transactions have the largest total transaction
amount.

Example:

SRC_A -> 500
SRC_B -> 1200
SRC_C -> 800

Return:

"SRC_B"

If there are no transactions:

    return null

If two sources have the same total amount, return the lexicographically
smaller source name.

Example:

SRC_A -> 1000
SRC_B -> 1000

Return:

"SRC_A"

------------------------------------------------------------
TASK 11
------------------------------------------------------------

Implement:

    List<Integer> getMostActiveAccounts(int limit)

An account's activity score is defined as:

    activity score = number of transactions

Return account IDs ordered by:

1. Highest transaction count first.
2. If counts are equal, smaller accountId first.

Example:

101 -> 5 transactions
202 -> 8 transactions
303 -> 3 transactions
404 -> 8 transactions

getMostActiveAccounts(3)

returns:

[202, 404, 101]

If limit is greater than the number of accounts,
return all accounts.

If limit <= 0, return an empty list.

------------------------------------------------------------
TASK 12
------------------------------------------------------------

Implement:

    Map<Integer, Map<String, Object>> reconcileAccounts()

For every account that appears in the transaction list, calculate:

    calculated_balance
    stored_balance
    difference
    transaction_count

Example:

{
    101: {
        "calculated_balance": 500.0,
        "stored_balance": 500.0,
        "difference": 0.0,
        "transaction_count": 4
    }
}

The calculated balance must be derived by processing the transactions
again from scratch.

Do NOT simply read balances to calculate calculated_balance.

This method is used to verify that the running balances have not become
corrupted.

------------------------------------------------------------
IMPORTANT REQUIREMENTS
------------------------------------------------------------

1. Do not modify Transaction.

2. Do not modify TransactionType.

3. Do not modify the tests.

4. Use Java collections appropriately.

5. Avoid O(n^2) solutions where a HashMap or HashSet can solve the problem
   efficiently.

6. addTransaction() should detect duplicate transaction IDs efficiently.

7. Reversal must not create a new transaction.

8. getTransactionsForAccount() must not modify transactions.

9. Monetary calculations should use double for this exercise.

10. Enum.name() must be used when a transaction type needs to be represented
    as a String.

11. The solution should work for multiple accounts and multiple sources.

12. New accounts may start with a debit transaction, resulting in a
    negative balance.

------------------------------------------------------------
STARTER CODE
------------------------------------------------------------
*/

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class Solution {

	enum TransactionType {
		DEPOSIT, WITHDRAWAL, TRANSFER, FEE, INTEREST
	}

	static class Transaction {

		final String transactionId;
		final int accountId;
		final double amount;
		final TransactionType transactionType;
		final int timestamp;
		final String description;
		final String source;

		Transaction(String transactionId, int accountId, double amount, TransactionType transactionType, int timestamp,
				String description, String source) {

			this.transactionId = transactionId;
			this.accountId = accountId;
			this.amount = amount;
			this.transactionType = transactionType;
			this.timestamp = timestamp;
			this.description = description;
			this.source = source;
		}

		@Override
		public boolean equals(Object other) {

			if (!(other instanceof Transaction t)) {
				return false;
			}

			return transactionId.equals(t.transactionId) && accountId == t.accountId
					&& Double.compare(amount, t.amount) == 0 && transactionType == t.transactionType
					&& timestamp == t.timestamp && description.equals(t.description) && source.equals(t.source);
		}

		@Override
		public int hashCode() {

			return Objects.hash(transactionId, accountId, amount, transactionType, timestamp, description, source);
		}

		@Override
		public String toString() {
			return "Transaction [transactionId=" + transactionId + ", accountId=" + accountId + ", amount=" + amount
					+ ", transactionType=" + transactionType + ", timestamp=" + timestamp + ", description="
					+ description + ", source=" + source + "]" + "\n";
		}

	}

	static class ImportSystem {

		final ArrayList<Transaction> transactions = new ArrayList<>();

		// TODO:
		// Add required data structures.

		/**
		 * Adds a transaction.
		 *
		 * Return false if transactionId already exists. Return true when successfully
		 * added.
		 */
		boolean addTransaction(Transaction transaction) {
			Set<String> set = new HashSet<>();
			boolean result = false;
			if (set.add(transaction.transactionId)) {
				result = true;
				transactions.add(transaction);
			}

			return result;
		}

		/**
		 * Returns summary for a specific account.
		 */
		Map<String, Object> getAccountSummary(int accountId) {

			// TODO
			return null;
		}

		/**
		 * Returns transaction summary grouped by source.
		 */
		Map<String, Map<String, Object>> getSourceSummary() {

			// TODO
			return null;
		}

		/**
		 * Returns:
		 *
		 * accountId -> transactionType -> count -> total_amount
		 */
		Map<Integer, Map<String, Map<String, Object>>> getAccountTypeSummary() {

			// TODO
			return null;
		}

		/**
		 * Returns accounts whose current balance is negative.
		 */
		List<Integer> getNegativeBalanceAccounts() {
		//	return transactions

			// TODO
			return null;
		}

		/**
		 * Reverses a transaction.
		 */
		boolean reverseTransaction(String transactionId) {

			// TODO
			return false;
		}

		/**
		 * Returns transactions for an account inside an inclusive timestamp range.
		 */
		List<Transaction> getTransactionsForAccount(int accountId, int startTimestamp, int endTimestamp) {

			// TODO
			return null;
		}

		/**
		 * Returns the net financial effect grouped by source.
		 */
		Map<String, Double> getBalancesBySource() {

			// TODO
			return null;
		}

		/**
		 * Returns the source with the largest transaction amount.
		 */
		String getLargestTransactionSource() {

			// TODO
			return null;
		}

		/**
		 * Returns the most active accounts.
		 */
		List<Integer> getMostActiveAccounts(int limit) {

			// TODO
			return null;
		}

		/**
		 * Recalculates account balances from transactions and compares them with the
		 * stored running balances.
		 */
		Map<Integer, Map<String, Object>> reconcileAccounts() {

			// TODO
			return null;
		}
	}

	public static void main(String[] args) {

		ImportSystem system = new ImportSystem();

		/*
		 * ======================================================== ACCOUNT 101
		 * ========================================================
		 * 
		 * Expected normal balance:
		 * 
		 * DEPOSIT +1000 DEPOSIT +500 WITHDRAWAL -300 FEE -50 INTEREST +25 TRANSFER -200
		 * 
		 * Final balance = 975
		 */

		system.addTransaction(new Transaction("T001", 101, 1000.00, TransactionType.DEPOSIT, 1, "Salary", "SRC_A"));

		system.addTransaction(new Transaction("T002", 101, 500.00, TransactionType.DEPOSIT, 2, "Bonus", "SRC_B"));

		system.addTransaction(
				new Transaction("T003", 101, 300.00, TransactionType.WITHDRAWAL, 3, "ATM withdrawal", "SRC_A"));

		system.addTransaction(new Transaction("T004", 101, 50.00, TransactionType.FEE, 4, "Monthly fee", "SRC_C"));

		system.addTransaction(
				new Transaction("T005", 101, 25.00, TransactionType.INTEREST, 5, "Monthly interest", "SRC_C"));

		system.addTransaction(
				new Transaction("T006", 101, 200.00, TransactionType.TRANSFER, 6, "Transfer to savings", "SRC_B"));

		/*
		 * ======================================================== ACCOUNT 202
		 * ========================================================
		 * 
		 * DEPOSIT +2000 WITHDRAWAL -800 TRANSFER -400 INTEREST +50
		 * 
		 * Final balance = 850
		 */

		system.addTransaction(new Transaction("T007", 202, 2000.00, TransactionType.DEPOSIT, 7, "Salary", "SRC_A"));

		system.addTransaction(
				new Transaction("T008", 202, 800.00, TransactionType.WITHDRAWAL, 8, "ATM withdrawal", "SRC_A"));

		system.addTransaction(
				new Transaction("T009", 202, 400.00, TransactionType.TRANSFER, 9, "Rent payment", "SRC_D"));

		system.addTransaction(new Transaction("T010", 202, 50.00, TransactionType.INTEREST, 10, "Interest", "SRC_C"));

		/*
		 * ======================================================== ACCOUNT 303
		 * ========================================================
		 * 
		 * This account intentionally starts with a DEBIT.
		 * 
		 * FEE -100 WITHDRAWAL -250 DEPOSIT +100
		 * 
		 * Final balance = -250
		 * 
		 * This account should appear in: getNegativeBalanceAccounts()
		 */

		system.addTransaction(
				new Transaction("T011", 303, 100.00, TransactionType.FEE, 11, "Account setup fee", "SRC_C"));

		system.addTransaction(
				new Transaction("T012", 303, 250.00, TransactionType.WITHDRAWAL, 12, "Cash withdrawal", "SRC_B"));

		system.addTransaction(
				new Transaction("T013", 303, 100.00, TransactionType.DEPOSIT, 13, "Small deposit", "SRC_A"));

		/*
		 * ======================================================== ACCOUNT 404
		 * ========================================================
		 * 
		 * DEPOSIT +300 FEE -50 DEPOSIT +100 TRANSFER -350
		 * 
		 * Final balance = 0
		 * 
		 * This account should NOT appear in negative accounts.
		 */

		system.addTransaction(
				new Transaction("T014", 404, 300.00, TransactionType.DEPOSIT, 14, "Initial deposit", "SRC_D"));

		system.addTransaction(new Transaction("T015", 404, 50.00, TransactionType.FEE, 15, "Service fee", "SRC_C"));

		system.addTransaction(new Transaction("T016", 404, 100.00, TransactionType.DEPOSIT, 16, "Refund", "SRC_B"));

		system.addTransaction(
				new Transaction("T017", 404, 350.00, TransactionType.TRANSFER, 17, "Outgoing transfer", "SRC_D"));

		/*
		 * ======================================================== ACCOUNT 505
		 * ========================================================
		 * 
		 * Only deposits.
		 * 
		 * DEPOSIT +500 DEPOSIT +750
		 * 
		 * Final balance = 1250
		 */

		system.addTransaction(
				new Transaction("T018", 505, 500.00, TransactionType.DEPOSIT, 18, "Initial deposit", "SRC_E"));

		system.addTransaction(
				new Transaction("T019", 505, 750.00, TransactionType.DEPOSIT, 19, "Additional deposit", "SRC_E"));

		/*
		 * ======================================================== ACCOUNT 606
		 * ========================================================
		 * 
		 * Only debits.
		 * 
		 * WITHDRAWAL -100 FEE -25
		 * 
		 * Final balance = -125
		 */

		system.addTransaction(new Transaction("T020", 606, 100.00, TransactionType.WITHDRAWAL, 20, "ATM", "SRC_A"));

		system.addTransaction(new Transaction("T021", 606, 25.00, TransactionType.FEE, 21, "Fee", "SRC_C"));

	}
}