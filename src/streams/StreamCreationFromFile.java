package streams;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

public class StreamCreationFromFile {

	public static void main(String[] args) {
		long uniqueWords = 0;

		try (Stream<String> lines = Files.lines(Paths.get("data.txt"), Charset.defaultCharset())) {

			uniqueWords = lines.flatMap(line -> Arrays.stream(line.split(""))).distinct().count();
			System.out.println("uniqueWords "+ uniqueWords);

		} catch (Exception ex) {
			System.out.println(ex);
		}

	}

}
