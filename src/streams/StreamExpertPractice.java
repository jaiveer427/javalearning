package streams;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class StreamExpertPractice {

	public static class Order {
		private String orderId;
		private String customerId;
		private String category;
		private double totalAmount;
		private LocalDate orderDate;
		private List<String> itemNames;
		private String status; // "DELIVERED", "PENDING", "CANCELLED"

		public Order(String orderId, String customerId, String category, double totalAmount, LocalDate orderDate,
				List<String> itemNames, String status) {
			this.orderId = orderId;
			this.customerId = customerId;
			this.category = category;
			this.totalAmount = totalAmount;
			this.orderDate = orderDate;
			this.itemNames = itemNames;
			this.status = status;
		}

		public String getOrderId() {
			return orderId;
		}

		public String getCustomerId() {
			return customerId;
		}

		public String getCategory() {
			return category;
		}

		public double getTotalAmount() {
			return totalAmount;
		}

		public LocalDate getOrderDate() {
			return orderDate;
		}

		public List<String> getItemNames() {
			return itemNames;
		}

		public String getStatus() {
			return status;
		}

		@Override
		public String toString() {
			return "Order{" + orderId + ", Amt=" + totalAmount + ", Status='" + status + "'}" + "\n";
		}
	}

	public static List<Order> getOrders() {
		return Arrays.asList(
				new Order("O101", "C1", "Electronics", 1200.00, LocalDate.of(2026, 1, 10),
						Arrays.asList("Laptop", "Mouse"), "DELIVERED"),
				new Order("O102", "C2", "Electronics", 350.00, LocalDate.of(2026, 1, 15), Arrays.asList("Monitor"),
						"DELIVERED"),
				new Order("O103", "C1", "Home", 75.50, LocalDate.of(2026, 2, 01), Arrays.asList("Lamp", "Cushion"),
						"PENDING"),
				new Order("O104", "C3", "Books", 45.00, LocalDate.of(2026, 2, 05),
						Arrays.asList("Java Guide", "SQL Basics"), "DELIVERED"),
				new Order("O105", "C2", "Electronics", 80.00, LocalDate.of(2026, 2, 12), Arrays.asList("Keyboard"),
						"CANCELLED"),
				new Order("O106", "C4", "Clothing", 120.00, LocalDate.of(2026, 3, 01), Arrays.asList("Jacket"),
						"DELIVERED"),
				new Order("O107", "C3", "Books", 110.00, LocalDate.of(2026, 3, 10),
						Arrays.asList("Spring in Action", "Docker Toolkit"), "DELIVERED"),
				new Order("O108", "C1", "Electronics", 600.00, LocalDate.of(2026, 3, 15), Arrays.asList("Smartphone"),
						"DELIVERED"),
				new Order("O109", "C5", "Home", 450.00, LocalDate.of(2026, 3, 18), Arrays.asList("Sofa"), "PENDING"),
				new Order("O110", "C2", "Clothing", 65.00, LocalDate.of(2026, 3, 20), Arrays.asList("Shoes"),
						"DELIVERED"));
	}

	public static void main(String[] args) {
		List<Order> orders = getOrders();
		// System.out.println(orders);
		List<String> allCategories = orders.stream().map(o -> o.getCategory()).distinct().toList();
		// System.out.println("Categories: " + allCategories);
		// System.out.println();
		// find the highest value order within each category,
		Map<String, Order> highestValueOrder = orders.stream()
				.collect(Collectors.groupingBy(o -> o.getCategory(), Collectors.collectingAndThen(
						Collectors.maxBy(Comparator.comparingDouble(Order::getTotalAmount)), op -> op.orElse(null))));
		// System.out.println(highestValueOrder);

		// The Goal: Generate a multi-level breakdown. Group the orders first by
		// Category, and then group them inside by their Status. The final value should
		// be the sum of total amounts.Expected Type: Map<String, Map<String, Double>>

		Map<String, Map<String, Double>> orderGroup = orders.stream()
				.collect(Collectors.groupingBy(o -> o.getCategory(),
						Collectors.groupingBy(o -> o.getStatus(), Collectors.summingDouble(o -> o.getTotalAmount()))));

		// System.out.println(orderGroup);

		/*
		 * Extract a unique list of all purchased item names across all DELIVERED
		 * orders, filter out any item name that has less than 5 characters, convert
		 * them to uppercase, and join them together into a single comma-separated
		 * String.Expected Type: String (e.g., "LAPTOP, MONITOR, JACKET, SMARTPHONE...")
		 */
		List<Order> deliveredOrder = orders.stream().filter(o -> o.getStatus().equals("DELIVERED")).toList();
		String items = deliveredOrder.stream().map(i -> i.getItemNames()).flatMap(List::stream).distinct()
				.filter(s -> s.length() >= 5).map(s -> s.toUpperCase()).collect(Collectors.joining(","));
		// System.out.println(items);

		/*
		 * High-Value Customer Retention (Filtering on Collections)The Goal: Find the
		 * IDs of all customers (customerId) whose total combined spending across all
		 * their DELIVERED orders is strictly greater than $500.
		 */
		List<String> highValuecustomerIds = orders.stream().filter(o -> o.getStatus().equals("DELIVERED"))
				.collect(Collectors.groupingBy(o -> o.getCustomerId(),
						Collectors.summingDouble(o -> o.getTotalAmount())))
				.entrySet().stream().filter(e -> e.getValue() > 500).map(e -> e.getKey()).toList();

		/*
		 * Custom Volume ReductionThe Goal: Calculate the financial impact of
		 * cancellations. Find the sum total of totalAmount of all orders that have a
		 * status of "CANCELLED". Do not use .mapToDouble(). Instead, use the general
		 * .reduce() method with an identity and an accumulator lambda.Expected Type:
		 * Double
		 */

		/*
		 * Double cancellationEffect = orders.stream().filter(o ->
		 * o.getStatus().equals("CANCELLED")) .collect(Collectors.summingDouble(o ->
		 * o.getTotalAmount()));
		 */

		Double cancellationEffect = orders.stream().filter(o -> "CANCELLED".equals(o.getStatus())).reduce(0.0,
				(runningSum, order) -> runningSum + order.getTotalAmount(), (sum1, sum2) -> sum1 + sum2);

		System.out.println("Total Cancelled Impact: $" + cancellationEffect);

	}
}
