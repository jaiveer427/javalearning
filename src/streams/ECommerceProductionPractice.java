package streams;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class ECommerceProductionPractice {

	// --- Core Domain Entities ---

	public static class Customer {
		private String customerId;
		private String name;
		private String email;
		private String tier; // "PLATINUM", "GOLD", "SILVER", "REGULAR"
		private LocalDate registrationDate;

		public Customer(String customerId, String name, String email, String tier, LocalDate registrationDate) {
			this.customerId = customerId;
			this.name = name;
			this.email = email;
			this.tier = tier;
			this.registrationDate = registrationDate;
		}

		public String getCustomerId() {
			return customerId;
		}

		public String getName() {
			return name;
		}

		public String getTier() {
			return tier;
		}

		public LocalDate getRegistrationDate() {
			return registrationDate;
		}

		@Override
		public String toString() {
			return "Customer{" + name + ", Tier=" + tier + "}";
		}
	}

	public static class Product {
		private String productId;
		private String name;
		private String category;
		private double price;
		private int stockCount;

		public Product(String productId, String name, String category, double price, int stockCount) {
			this.productId = productId;
			this.name = name;
			this.category = category;
			this.price = price;
			this.stockCount = stockCount;
		}

		public String getProductId() {
			return productId;
		}

		public String getName() {
			return name;
		}

		public String getCategory() {
			return category;
		}

		public double getPrice() {
			return price;
		}

		public int getStockCount() {
			return stockCount;
		}

		@Override
		public String toString() {
			return "Product{" + name + ", Price=" + price + "}";
		}
	}

	public static class OrderItem {
		private String productId;
		private int quantity;

		public OrderItem(String productId, int quantity) {
			this.productId = productId;
			this.quantity = quantity;
		}

		public String getProductId() {
			return productId;
		}

		public int getQuantity() {
			return quantity;
		}
	}

	public static class Order {
		private String orderId;
		private String customerId;
		private List<OrderItem> items;
		private LocalDate orderDate;
		private String status; // "DELIVERED", "PROCESSING", "CANCELLED"

		public Order(String orderId, String customerId, List<OrderItem> items, LocalDate orderDate, String status) {
			this.orderId = orderId;
			this.customerId = customerId;
			this.items = items;
			this.orderDate = orderDate;
			this.status = status;
		}

		public String getOrderId() {
			return orderId;
		}

		public String getCustomerId() {
			return customerId;
		}

		public List<OrderItem> getItems() {
			return items;
		}

		public LocalDate getOrderDate() {
			return orderDate;
		}

		public String getStatus() {
			return status;
		}
	}

	// --- Mock Database Mocking Vectors ---

	public static List<Customer> getCustomers() {
		return Arrays.asList(
				new Customer("C001", "Alice Vance", "alice@vance.com", "PLATINUM", LocalDate.of(2023, 5, 12)),
				new Customer("C002", "Bob Miller", "bob@miller.com", "GOLD", LocalDate.of(2024, 1, 19)),
				new Customer("C003", "Charlie Knox", "charlie@knox.com", "SILVER", LocalDate.of(2025, 8, 30)),
				new Customer("C004", "Diana Prince", "diana@amazon.com", "PLATINUM", LocalDate.of(2022, 11, 05)),
				new Customer("C005", "Evan Wright", "evan@wright.com", "REGULAR", LocalDate.of(2026, 2, 14)));
	}

	public static Map<String, Product> getProductCatalog() {
		List<Product> products = Arrays.asList(new Product("P101", "Developer Laptop", "Electronics", 1200.00, 15),
				new Product("P102", "Ergonomic Keyboard", "Electronics", 150.00, 45),
				new Product("P103", "4K Ultra Monitor", "Electronics", 450.00, 8),
				new Product("P104", "Standing Desk", "Furniture", 650.00, 4),
				new Product("P105", "Office Task Chair", "Furniture", 280.00, 12),
				new Product("P106", "Java Performance Optimization", "Books", 55.00, 120),
				new Product("P107", "Cloud Architecture Guide", "Books", 70.00, 85),
				new Product("P108", "Noise Cancelling Headphones", "Electronics", 300.00, 25));
		return products.stream().collect(Collectors.toMap(Product::getProductId, p -> p));
	}

	public static List<Order> getOrders() {
		return Arrays.asList(
				new Order("O501", "C001", Arrays.asList(new OrderItem("P101", 1), new OrderItem("P102", 1)),
						LocalDate.of(2026, 8, 10), "DELIVERED"),
				new Order("O502", "C002", Arrays.asList(new OrderItem("P103", 2)), LocalDate.of(2026, 8, 15),
						"DELIVERED"),
				new Order("O503", "C001", Arrays.asList(new OrderItem("P106", 3), new OrderItem("P107", 1)),
						LocalDate.of(2026, 8, 22), "DELIVERED"),
				new Order("O504", "C003", Arrays.asList(new OrderItem("P104", 1), new OrderItem("P105", 1)),
						LocalDate.of(2026, 9, 01), "PROCESSING"),
				new Order("O505", "C002", Arrays.asList(new OrderItem("P102", 1), new OrderItem("P108", 1)),
						LocalDate.of(2026, 9, 05), "CANCELLED"),
				new Order("O506", "C004", Arrays.asList(new OrderItem("P101", 2), new OrderItem("P108", 1)),
						LocalDate.of(2026, 9, 12), "DELIVERED"),
				new Order("O507", "C005", Arrays.asList(new OrderItem("P106", 1)), LocalDate.of(2026, 9, 17),
						"PROCESSING"));
	}

	public static void main(String[] args) {
		List<Customer> customers = getCustomers();
		Map<String, Product> catalog = getProductCatalog();
		List<Order> orders = getOrders();
		//System.out.println(orders.get(0));

		Map<String, String> customerIdToTierMap = customers.stream()
				.collect(Collectors.toMap(C -> C.getCustomerId(), C -> C.getTier()));
	//	System.out.println(customerIdToTierMap);
		/*
		 * The Goal: Calculate the exact total revenue generated exclusively from
		 * DELIVERED orders, broken down by the customer's membership tier (PLATINUM,
		 * GOLD, etc.).Expected Type: Map<String, Double>
		 */
		Map<String, Double> revenueByTier = getOrders().stream().filter(o -> "DELIVERED".equals(o.getStatus()))
				.collect(Collectors.groupingBy(o -> customerIdToTierMap.getOrDefault(o.getCustomerId(), "UNKNOWN"),
						Collectors.summingDouble(o -> o.getItems().stream().mapToDouble(item -> {
							Product p = catalog.get(item.getProductId());
							return p != null ? p.getPrice() * item.getQuantity() : 0.0;
						}).sum())));
		
		//System.out.println(revenueByTier);
		
		//Cross-Referenced Filtering (Platinum Customer Book Lovers)
		
		//Map<String,Customer> topTierBookLover=

	}
}
