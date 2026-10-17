package coding;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FreqProblems {

	public static String freqChars(String s) {
		/*
		 * Map<Character,Integer> freq=new HashMap<Character, Integer>();
		 * 
		 * StringBuilder result=new StringBuilder(); for(int i=0;i<s.length();i++) {
		 * char c= s.charAt(i); freq.put(c, freq.getOrDefault(c,0)+1);
		 * 
		 * }
		 * 
		 * System.out.println(freq); for(Entry<Character,Integer> entry:
		 * freq.entrySet()) { result.append(entry.getKey() +""+ entry.getValue());
		 * 
		 * }
		 */
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < s.length()-1; i++) {
			System.out.println(i);
			int count = 1;

			while (i < s.length()-1 && s.charAt(i) == s.charAt(i+1)) {
				i++;
				count++;
			}
			result.append(s.charAt(i) + "" + count);
		}
		return result.toString();

	}

	public static <T> T[] removeDuplicates(T[] arr) {
		int j = 0;
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] != arr[j]) {
				j++;
				arr[j] = arr[i];
			}
		}

		return arr;

	}

	// Longest substring without repeating characters

	public static void main(String[] args) {

		String input = "aaabbbbdddccc";

		 System.out.println(freqChars(input));
		// Input: [1,1,2,2,3,3,3]
		// Output: [1,2,3]

		//Integer[] input_1 = { 1, 1, 2, 2, 3, 3, 3 };
		// System.out.println(Arrays.toString(removeDuplicates(input_1)));
		// System.out.println(longestSubString("abcabcadecf"));

		//System.out.println(solution(1));

	}

	private static String longestSubString(String string) {

		return null;
	}

	public static long solution(long n) {
		String input = String.valueOf(n);
		int len = input.length();

		Set<Long> candidates = new HashSet<>();
		candidates.add((long) Math.pow(10, len - 1) - 1);
		candidates.add((long) Math.pow(10, len) + 1);

		System.out.println(candidates);
		long prefix = Long.parseLong(input.substring(0, (len + 1) / 2));
		System.out.println("Prefix: " + prefix);
		for (long i : new long[] { prefix, prefix + 1, prefix - 1 }) {

			candidates.add(createPalindrome(i, len % 2 == 0));

		}
		System.out.println(candidates);

		long result = Long.MAX_VALUE;
		for (long cand : candidates) {
			if (cand > n && cand < result) {
				result = cand;
			}
		}
		return result;

	}

	private static Long createPalindrome(long prefix, boolean even) {
		System.out.println(prefix);
		String pStr = String.valueOf(prefix);
		StringBuilder pStrb = new StringBuilder(pStr);

		StringBuilder suffix = new StringBuilder(pStr).reverse();
		if (!even) {
			suffix.deleteCharAt(0);
		}

		return Long.parseLong(pStrb.append(suffix).toString());
	}

	public static boolean isPalindrome(long n) {
		long temp = n;
		long reversedNo = 0;
		while (temp > 0) {
			int digit = (int) (temp % 10);
			reversedNo = reversedNo * 10 + digit;
			temp = temp / 10;
		}
		return n == reversedNo ? true : false;

	}
	
	

}
