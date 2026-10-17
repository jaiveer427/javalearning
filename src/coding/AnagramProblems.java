package coding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.List;

public class AnagramProblems {

	/*
	 * Input: s = "listen", t = "silent" Output: true
	 */
	public static boolean areAnagramsUsingMap(String s1, String s2) {
		if (s1 == null || s2 == null) {
			throw new IllegalArgumentException("Strings must not be empty");
		}

		if (s1.length() != s2.length()) {
			return false;
		}

		Map<Character, Integer> freqMap = new LinkedHashMap<Character, Integer>();
		for (char c : s1.toCharArray()) {
			freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
		}

		// System.out.println(freqMap);

		for (char c : s2.toCharArray()) {
			if (!freqMap.containsKey(c))
				return false;

			freqMap.put(c, freqMap.get(c) - 1);

			if (freqMap.get(c) == 0) {
				freqMap.remove(c);
			}
		}

		// System.out.println(freqMap);

		return freqMap.isEmpty();

	}

	// Given an array of strings, group the anagrams together.

	/*
	 * Input: ["eat","tea","tan","ate","nat","bat"] Output:
	 * [["eat","tea","ate"],["tan","nat"],["bat"]]
	 */

	public static List<List<String>> groupAnagram(List<String> list) {
		if (list == null || list.size() == 0) {
			throw new IllegalArgumentException("List is empty");
		}

		Map<String, List<String>> result = new LinkedHashMap<String, List<String>>();

		for (String word : list) {
			char[] chars = word.toCharArray();
			Arrays.sort(chars);
			String key = new String(chars);

			result.computeIfAbsent(key, (k) -> new ArrayList<String>()).add(word);

		}

		return new ArrayList<List<String>>(result.values());
	}

	public static void main(String[] args) {
		// System.out.println(areAnagramsUsingMap("listen", "silent"));
		System.out.println(groupAnagram(Arrays.asList("eat", "tea", "tan", "ate", "nat", "bat")));

	}

}
