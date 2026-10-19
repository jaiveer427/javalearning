package prefix;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Prefixbasic {

	// Input: strs = ["flower","flow","flight"]
	// Output: "fl"
	public static String longestCommonPrefix(String[] strs) {
		if (strs.length == 0 || strs == null) {
			return "";

		}
		String prefix = strs[0];
		if (prefix == null) {
			return "";
		}
		for (int i = 0; i < strs.length; i++) {
			if (strs[i].isBlank() || strs[i].isEmpty()) {
				return "";
			}
			while (strs[i].indexOf(prefix) != 0) {
				prefix = prefix.substring(0, prefix.length() - 1);

				if (prefix == "") {
					return "";
				}
			}

		}
		return prefix;

	}

	public static String longestCommonPrefixVerticalScanning(String[] strs) {
		if (strs == null || strs.length == 0) {
			return "";
		}
		for (String s : strs) {
			if (s == null) {
				return "";
			}
		}
		String prefix = strs[0];
		for (int i = 0; i < prefix.length(); i++) {
			char c = prefix.charAt(i);

			for (int j = 1; j < strs.length; j++) {

				if (i > strs[j].length() || c != strs[j].charAt(i)) {
					return prefix.substring(0, i);
				}
			}

		}

		return strs[0];

	}

	public static int findTheLongestSubstringBrute(String s) {

		if (s == null || s.isEmpty() || s.isBlank()) {
			return 0;
		}
		int n = s.length();
		int maxLen = 0;
		for (int i = 0; i < n; i++) {
			int a = 0, e = 0, idx = 0, o = 0, u = 0;
			for (int j = i; j < n; j++) {
				char ch = s.charAt(j);
				if (ch == 'a')
					a++;
				else if (ch == 'e')
					e++;
				else if (ch == 'i')
					idx++;
				else if (ch == 'o')
					o++;
				else if (ch == 'u')
					u++;

				if (a % 2 == 0 && e % 2 == 0 && idx % 2 == 0 && o % 2 == 0 && u % 2 == 0) {
					maxLen = Math.max(maxLen, j - i + 1);

				}

			}
		}

		return maxLen;

	}

	// eleetminicoworoep //13
	public static int findTheLongestSubstring(String s) {

		if (s == null || s.isEmpty() || s.isBlank()) {
			return 0;
		}
		int n = s.length();
		int mask = 0;
		int maxLen = 0;
		int[] seen = new int[32];
		Arrays.fill(seen, -2);
		seen[0] = -1;

		for (int i = 0; i < n; i++) {
			int a = 0, e = 0, idx = 0, o = 0, u = 0;

			char ch = s.charAt(i);
			if (ch == 'a')
				mask ^= (1 << 0);
			else if (ch == 'e')
				e++;
			else if (ch == 'i')
				idx++;
			else if (ch == 'o')
				o++;
			else if (ch == 'u')
				u++;
			
			System.out.println(mask);

		}
	

	return maxLen;

	}

	public static void main(String[] args) {
		// String[] strs = { "flower", "flow", "flight" };
		// System.out.println(longestCommonPrefix(strs));
		// System.out.println(longestCommonPrefixVerticalScanning(strs));
		String s = "eleetminicoworoep";

		System.out.println(findTheLongestSubstringBrute(s));
		System.out.println(1 << 1);

	}

}
