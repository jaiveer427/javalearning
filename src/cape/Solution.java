package cape;
/*
We are building a program to manage a bank's imported transactions. The system tracks transactions from multiple sources (partner institutions, customer statement uploads, legacy migrations), each belonging to an account. The program allows staff to import transactions, query per-account summaries, and reconcile activity across sources.

Definitions:
* A "transaction" is an object that represents a single financial event. It has properties for the transaction ID, account ID, amount (always positive), transaction type, timestamp (day number since a reference point), description, and source.
* An "import system" is a class used for managing all imported transactions for the bank.
* A "credit" is a transaction type that increases the account balance. DEPOSIT and INTEREST are credits.
* A "debit" is a transaction type that decreases the account balance. WITHDRAWAL, FEE, and TRANSFER are debits.
* netChange represents the net movement in an account's balance across all its transactions. It is positive if credits exceed debits, and negative if debits exceed credits.

To begin with, we present you with two tasks:
1-1) Read through and understand the code below. Feel free to run it.
1-2) The test for ImportSystem is not passing due to a bug in the code. Make the necessary changes to ImportSystem to fix the bug.
*/

/*
As transactions are imported, the system needs to record each one and update the account's running balance based on transaction direction (credits add, debits subtract). The system also needs to report activity summaries broken down by transaction type.

Definitions (in addition to those from Q1):
- balances is a mapping on ImportSystem from each accountId to its current running balance. New accounts start at $0 before their first transaction.

Add three things to the system:

2-1) Extend the ImportSystem class so it maintains a balances attribute — a mapping from accountId to running balance, initially empty.

2-2) Change addTransaction(transaction) so that, in addition to appending the given Transaction, it also updates the account's balance based on the transaction's direction. Credits (DEPOSIT, INTEREST) add the amount; debits (WITHDRAWAL, FEE, TRANSFER) subtract it. If the account is new (not yet in balances), initialize it to $0 before applying the change.

2-3) Implement getTypeSummary() that returns a mapping from transaction type name (a string, uppercase, matching the enum name exactly — for example "DEPOSIT") to an inner mapping with keys "count" (integer) and "total_amount" (double). Aggregate across all accounts.
Only include types that have at least one transaction; omit types that have none.

The testAddTransaction and testGetTypeSummary tests are provided.
*/

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * TransactionType represents the category of a financial event. DEPOSIT and
 * INTEREST are credits (they increase account balance). WITHDRAWAL, FEE, and
 * TRANSFER are debits (they decrease account balance).
 */
enum TransactionType {
	DEPOSIT, WITHDRAWAL, TRANSFER, FEE, INTEREST,
}

/** Data about a single financial transaction. */
class Transaction {
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
		if (!(other instanceof Transaction t))
			return false;
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
		return "Transaction ID: " + transactionId + ", Account: " + accountId + ", Amount: " + amount + ", Type: "
				+ transactionType + ", Timestamp: " + timestamp + ", Description: " + description + ", Source: "
				+ source + "\n";
	}
}

/**
 * Data for managing imported bank transactions, and methods which staff can use
 * to perform any queries or updates.
 */
class ImportSystem {
	Map<Integer, Double> balances = new HashMap<>();

	final ArrayList<Transaction> transactions = new ArrayList<>();

	/** Adds a transaction to the system */
	public void addTransaction(Transaction t) {

		balances.putIfAbsent(t.accountId, 0.0);

		double balance = balances.get(t.accountId);

		if (t.transactionType == TransactionType.DEPOSIT || t.transactionType == TransactionType.INTEREST) {
			balance += t.amount;

		} else if (t.transactionType == TransactionType.WITHDRAWAL || t.transactionType == TransactionType.FEE
				|| t.transactionType == TransactionType.TRANSFER) {
			balance -= t.amount;

		}

		balances.put(t.accountId, balance);

		transactions.add(t);

	}

	/** Calculates and returns summary statistics for a given account */
	Map<String, Object> getAccountSummary(int accountId) {
		double totalCredits = 0;
		double totalDebits = 0;
		int transactionCount = 0;
		for (Transaction t : transactions) {

			if (t.accountId != accountId)
				continue;
			transactionCount++;
			if (t.transactionType == TransactionType.DEPOSIT || t.transactionType == TransactionType.INTEREST) {

				totalCredits += t.amount;
			} else if (t.transactionType == TransactionType.WITHDRAWAL || t.transactionType == TransactionType.FEE
					|| t.transactionType == TransactionType.TRANSFER) {
				totalDebits += t.amount;
			}
		}

		double netChange = totalCredits - totalDebits;
		System.out.println("netChange " + netChange);

		Map<String, Object> result = new HashMap<>();
		result.put("total_credits", totalCredits);
		result.put("total_debits", totalDebits);
		result.put("net_change", netChange);
		result.put("transaction_count", transactionCount);
		return result;
	}

	public Map<String, Map<String, Object>> getTypeSummary() {

		Map<String, Map<String, Object>> typeSummary = new HashMap<>();

		for (Transaction t : transactions) {
			String type = t.transactionType.name();
			if (!(typeSummary.containsKey(type))) {
				Map<String, Object> summary = new HashMap<>();
				summary.put("count", 0);
				summary.put("total_amount", 0.0);

				typeSummary.put(type, summary);

			}

			Map<String, Object> summary = typeSummary.get(type);
			int count = (int) summary.get("count");
			double amount = (double) summary.get("total_amount");

			summary.put("count", count + 1);
			summary.put("total_amount", amount + t.amount);

		}

		return typeSummary;

	}
}

public class Solution {

	public static void main(String[] args) {
		ImportSystem system = new ImportSystem();

		// Account 101: 6 transactions covering all 5 types
		system.addTransaction(new Transaction("t001", 101, 500.00, TransactionType.DEPOSIT, 1, "Salary", "SRC_A"));
		system.addTransaction(new Transaction("t002", 101, 300.00, TransactionType.DEPOSIT, 2, "Refund", "SRC_A"));
		system.addTransaction(new Transaction("t003", 101, 150.00, TransactionType.WITHDRAWAL, 3, "ATM", "SRC_A"));
		system.addTransaction(new Transaction("t004", 101, 50.00, TransactionType.FEE, 4, "Service fee", "SRC_A"));
		system.addTransaction(new Transaction("t005", 101, 200.00, TransactionType.TRANSFER, 5, "Wire out", "SRC_A"));
		system.addTransaction(
				new Transaction("t006", 101, 20.00, TransactionType.INTEREST, 6, "Interest paid", "SRC_A"));

		// Account 202: 2 transactions to verify per-account filtering
		system.addTransaction(new Transaction("t007", 202, 100.00, TransactionType.DEPOSIT, 7, "Deposit", "SRC_A"));
		system.addTransaction(
				new Transaction("t008", 202, 30.00, TransactionType.WITHDRAWAL, 8, "Withdrawal", "SRC_A"));

		System.out.println(system.transactions);
		System.out.println(system.balances);
		System.out.println(system.getAccountSummary(101));
		System.out.println(system.getAccountSummary(202));
		System.out.println(system.getTypeSummary());

	}
}
