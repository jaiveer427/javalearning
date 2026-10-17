package streams;

import java.util.Arrays;
import java.util.List;

public class StreamBeginner {

	public static void main(String[] args) {
		// Remove null values from list
		List<String> list = Arrays.asList("a", null, "b");

		List<String> nonNulllist = list.stream().filter(str -> str != null).toList();
		List<Integer> nums = List.of(5, 6, 7);
		System.out.println(nonNulllist);
		//long count=nums.stream().count();
		List<String> l = Arrays.asList("A", "B", "C", "D");
	   //  long count = l.stream().peek(System.out::println).count();
	//	Sort numbers ascending
		List<Integer> sortedList=List.of(5,2,9).stream().sorted((a,b)->a-b).toList();
	System.out.println(sortedList);

	}

}
