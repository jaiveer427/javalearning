package streams.problems;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {

	// PYTHAGOREAN TRIPLE
	public static List<int[]> generatePythagoreanTriplets(int from, int to, int limit) {

		Stream<int[]> tripletStream = IntStream.rangeClosed(from, to).boxed()
				.flatMap(a -> IntStream.rangeClosed(a, to).filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
						.mapToObj(b -> new int[] { a, b, (int) Math.sqrt(a * a + b * b) }));

		/*
		 * Stream<double[]> tripletStream = IntStream.rangeClosed(from, to).boxed()
		 * .flatMap(a -> IntStream.rangeClosed(a, to) .mapToObj( b -> new int[]{a, b,
		 * Math.sqrt(a*a + b*b)}) .filter(t -> t[2] % 1.0 == 0));
		 */

		return tripletStream.limit(limit).toList();

	}

	public static void main(String[] args) {
		Trader raoul = new Trader("Raoul", "Cambridge");
		Trader mario = new Trader("Mario", "Milan");
		Trader alan = new Trader("Alan", "Cambridge");
		Trader brian = new Trader("Brian", "Cambridge");
		List<Transaction> transactions = Arrays.asList(new Transaction(brian, 2011, 300),
				new Transaction(raoul, 2012, 1000), new Transaction(raoul, 2011, 400),
				new Transaction(mario, 2012, 710), new Transaction(mario, 2012, 700), new Transaction(alan, 2012, 950));
		// System.out.println("transactions: " + transactions);

		/*
		 * 1 Find all transactions in the year 2011 and sort them by value (small to
		 * high). 2 What are all the unique cities where the traders work? 3 Find all
		 * traders from Cambridge and sort them by name. 4 Return a string of all
		 * traders’ names sorted alphabetically. 5 Are any traders based in Milan? 6
		 * Print the values of all transactions from the traders living in Cambridge. 7
		 * What’s the highest value of all the transactions? 8 Find the transaction with
		 * the smallest value.
		 */

		List<Transaction> sortedByYear = transactions.stream().filter(t -> t.getYear() == 2011)
				.sorted((a, b) -> a.getValue() - b.getValue()).toList();

		List<String> uniqueCities = transactions.stream().map(t -> t.getTrader().getCity()).distinct().toList();

		List<Trader> cambridgeTraderSortedByName = transactions.stream().map(t -> t.getTrader())
				.filter(t -> t.getCity().equals("Cambridge")).sorted((a, b) -> a.getName().compareTo(b.getName()))
				.toList();
//less Efficient due to overhead of string creation
		/*
		 * String tradersNameSortedByName = transactions.stream().map(t ->
		 * t.getTrader().getName()).distinct() .sorted((a, b) ->
		 * a.compareTo(b)).reduce("", (s1, s2) -> s1 + s2);
		 */

		String tradersNameSortedByName = transactions.stream().map(t -> t.getTrader().getName()).distinct()
				.sorted((a, b) -> a.compareTo(b)).collect(Collectors.joining());

		Boolean anyTraderFromMilan = transactions.stream().anyMatch(t -> t.getTrader().getCity().equals("Milan"));

		List<Integer> allTranValuesFromCambridge = transactions.stream()
				.filter(t -> t.getTrader().getCity() == "Cambridge").map(t -> t.getValue()).toList();

		OptionalInt maxValue = transactions.stream().mapToInt(Transaction::getValue).max();

		// Integer minValue = transactions.stream().map(t ->
		// t.getValue()).reduce(Integer::min).get();
		// Integer minValue =
		// transactions.stream().mapToInt(Transaction::getValue).min().orElse(-1);

		Transaction minValueTranaction = transactions.stream().min(Comparator.comparing(Transaction::getValue))
				.orElse(null);

		System.out.println("transactions sortedByYear: " + sortedByYear);
		System.out.println("Unique cities : " + uniqueCities);
		System.out.println("cambridgeTraderSortedByName : " + cambridgeTraderSortedByName);
		System.out.println("tradersNameSortedByName : " + tradersNameSortedByName);
		System.out.println("anyTraderFromMilan : " + anyTraderFromMilan);
		System.out.println(" allTranValuesFromCambridge : " + allTranValuesFromCambridge);
		System.out.println("Max value : " + maxValue.getAsInt());
		System.out.println("min Value Tranaction : " + minValueTranaction);

		// generatePythagoreanTriplets(100, 500, 10).forEach(t ->
		// System.out.println(t[0] + "," + t[1] + "," + t[2]));

		/*
		 * Stream.iterate(new int[] { 0, 1 }, t -> new int[] { t[1], t[0] + t[1]
		 * }).limit(20) .forEach(t -> System.out.println("(" + t[0] + "," + t[1] +
		 * ")"));
		 */
		Map<Integer, List<Transaction>> transactionsByCurrencies =
				transactions.stream().collect(Collectors.groupingBy(Transaction::getValue));
		System.out.println( transactionsByCurrencies);

	}

}
