package java8CollectionApi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CollectionFactories {

	public static void main(String[] args) {
		//Traditional style collection use
		
		/*
		 * List<String> friends=new ArrayList<String>(); friends.add("Raphael");
		 * friends.add("Olivia"); friends.add("Thibaut");
		 */
		//List<String> friends=Arrays.asList("Raphael", "Olivia", "Thibaut");
	//	friends.set(0, "jai");
		List<String> friends = List.of("Raphael", "Olivia", "Thibaut");
		//friends.set(0, "jai");
		
		
		
		System.out.println(friends);
		

	}

}
