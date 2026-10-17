package streams;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

enum CaloricLevel {
	DIET, NORMAL, FAT
}

public class StreamCreationDemo {
	
	public static boolean isPrime(int candidate) {
		int candidateRoot = (int) Math.sqrt((double) candidate);
		return IntStream.rangeClosed(2, candidateRoot)
		.noneMatch(i -> candidate % i == 0);
		}
	
	
	public static Map<Boolean, List<Integer>> partitionPrimes(int n) {
		return IntStream.rangeClosed(2, n).boxed()
		.collect(
		Collectors.partitioningBy(candidate -> isPrime(candidate)));
		}

	public static void main(String[] args) {
		Map<String, List<String>> dishTags = new HashMap<>();
		dishTags.put("pork", Arrays.asList("greasy", "salty"));
		dishTags.put("beef", Arrays.asList("salty", "roasted"));
		dishTags.put("chicken", Arrays.asList("fried", "crisp"));
		dishTags.put("french fries", Arrays.asList("greasy", "fried"));
		dishTags.put("rice", Arrays.asList("light", "natural"));
		dishTags.put("season fruit", Arrays.asList("fresh", "natural"));
		dishTags.put("pizza", Arrays.asList("tasty", "salty"));
		dishTags.put("prawns", Arrays.asList("tasty", "roasted"));
		dishTags.put("salmon", Arrays.asList("delicious", "fresh"));

		List<Dish> menu = Arrays.asList(new Dish("pork", false, 800, Dish.Type.MEAT),
				new Dish("beef", false, 700, Dish.Type.MEAT), new Dish("chicken", false, 400, Dish.Type.MEAT),
				new Dish("french fries", true, 530, Dish.Type.OTHER), new Dish("rice", true, 350, Dish.Type.OTHER),
				new Dish("season fruit", true, 120, Dish.Type.OTHER), new Dish("pizza", true, 550, Dish.Type.OTHER),
				new Dish("prawns", false, 300, Dish.Type.FISH), new Dish("salmon", false, 450, Dish.Type.FISH));

		// System.out.println(menu);

		Optional<Dish> maxCalDish = menu.stream().sorted((a, b) -> b.getCalories() - a.getCalories()).findFirst();
		Optional<Dish> lowestCalDish = menu.stream().sorted((a, b) -> a.getCalories() - b.getCalories()).findFirst();

		List<String> filteredDish = menu.stream().filter((dish) -> {
			// System.out.println("filtering " + dish.getName());
			return dish.getCalories() > 300;
		}).map((dish) -> {
			// System.out.println("mapping " + dish.getName());
			return dish.getName();
		}).collect(Collectors.toList());

		Optional<Dish> vegetarianDishes = menu.stream().filter(Dish::isVegetarian).findAny();
		// System.out.println(vegetarianDishes.get());

		/*
		 * if(menu.stream().anyMatch(Dish::isVegetarian)) {
		 * System.out.println("The menu is (somewhat) vegetarian friendly!!"); }
		 */

		/*
		 * List<Integer> someNumbers = Arrays.asList(1, 2, 3, 4, 5,6); Optional<Integer>
		 * firstSquareDivisibleByThree = someNumbers.stream() .map(n -> n * n) .filter(n
		 * -> n % 3 == 0) .skip(1).findAny(); // 9
		 * System.out.println(firstSquareDivisibleByThree.get());
		 */
		/*
		 * List<Integer> numbers = Arrays.asList(-1,1, 2, 3, 4, 5,6);
		 * List<Integer>squaredNum=numbers.stream().map(a->(int)Math.pow(a,
		 * 2)).toList(); long max=squaredNum.stream().reduce(0,(a,b)->Math.max(a, b));
		 * long min=squaredNum.stream().reduce((a,b)->Math.min(a, b)).orElse(0); int
		 * sum=numbers.stream().reduce(-1, (a,b)->a+b); int
		 * product=numbers.stream().reduce(1,(a,b)->a*b); System.out.println("Sum: "+
		 * sum); System.out.println("Product: "+ product); System.out.println("Max :"+
		 * max); System.out.println("Min :"+ min);
		 * System.out.println("Squared numbers: "+ squaredNum);
		 */

		Map<Dish.Type, List<Dish>> caloricDishesByType = menu.stream().collect(Collectors.groupingBy(Dish::getType,
				Collectors.filtering(d -> d.getCalories() > 400, Collectors.toList())));

		Map<Dish.Type, Set<String>> dishNamesByType = menu.stream().collect(Collectors.groupingBy(Dish::getType,
				Collectors.flatMapping(d -> dishTags.get(d.getName()).stream(), Collectors.toSet())));

		Map<Dish.Type, Map<CaloricLevel, List<Dish>>> dishesByTypeCaloricLevel = menu.stream()
				.collect(Collectors.groupingBy(Dish::getType, Collectors.groupingBy(d -> {

					if (d.getCalories() <= 400)
						return CaloricLevel.DIET;
					else if (d.getCalories() <= 700)
						return CaloricLevel.NORMAL;
					else
						return CaloricLevel.FAT;

				})));
		// find the highest-calorie dish in the menu by type

		// Map<Dish.Type, Optional<Dish>> mostCaloricByType = menu.stream().collect(
		// Collectors.groupingBy(Dish::getType,
		// Collectors.maxBy(Comparator.comparingInt(Dish::getCalories))));

		Map<Dish.Type, Dish> mostCaloricByType = menu.stream().collect(Collectors.groupingBy(Dish::getType, Collectors
				.collectingAndThen(Collectors.maxBy(Comparator.comparingInt(Dish::getCalories)), Optional::get)));

		Map<Boolean, Map<Dish.Type, List<Dish>>> vegetarianDishesByType = menu.stream()
				.collect(Collectors.partitioningBy(Dish::isVegetarian, Collectors.groupingBy(Dish::getType)));

		Map<Boolean, Dish> mostCaloricPartitionedByVegetarian = menu.stream()
				.collect(Collectors.partitioningBy(Dish::isVegetarian, Collectors.collectingAndThen(
						Collectors.maxBy(Comparator.comparingInt(Dish::getCalories)), Optional::get)));

		System.out.println("....................................................................\n");
		// System.out.println("caloricDishesByType: " + caloricDishesByType);

		// System.out.println("dishNamesByType: " + dishNamesByType);
		// System.out.println(" dishesByTypeCaloricLevel: " + dishesByTypeCaloricLevel);
		// System.out.println(" mostCaloricByType: " + mostCaloricByType);

	//	System.out.println("vegetarianDishesByType :" + vegetarianDishesByType);
		//System.out.println("vegetarianDishesByType :" + vegetarianDishesByType.get(false));
		//System.out.println("vegetarianDishesByType :" + vegetarianDishesByType.get(true));
		
		System.out.println("mostCaloricPartitionedByVegetarian "+ mostCaloricPartitionedByVegetarian);

	}

}
