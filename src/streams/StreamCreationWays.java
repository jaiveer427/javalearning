package streams;

import java.util.List;
import java.util.stream.Stream;

public class StreamCreationWays {

	public static void main(String[] args) {
	List<Integer> numbers=	Stream.iterate(1, n -> n + 1).limit(100).toList();
	System.out.println(numbers);
		//Stream.generate(Math::random);


	}

}
