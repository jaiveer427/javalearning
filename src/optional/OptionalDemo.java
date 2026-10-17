package optional;

import java.util.Optional;

public class OptionalDemo {

	public static void main(String[] args) {
		
		Optional<String> user = Optional.ofNullable("Jaiveerkumar427@gmail.com");
		System.out.println(user.get());
		user
		    .map(email -> email.split("@")[1])
		    .ifPresent(System.out::println);

	}

}
