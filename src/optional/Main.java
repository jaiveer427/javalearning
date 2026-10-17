package optional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

	public static void main(String[] args) {
Integer[] array = { 4, 2, 7, 1, 5, 3, 6 };

		// System.out.println("Array: " + List.of(array));
		// Find the third smallest value in the array using Optional
		List<Integer> list = Arrays.stream(array).sorted((a, b) -> b - a).collect(Collectors.toList());
		System.out.println(list);

		Optional<Integer> thirdSmallest = Arrays.stream(array).sorted((a, b) -> b - a).skip(2).findFirst();

		System.out.println("Third smallest: " + thirdSmallest.orElse(null));

		Optional<Integer> max = Arrays.stream(array).max(Integer::compareTo);
		
		max.ifPresent(t->{
			System.out.println(t);
		});

	}
}
