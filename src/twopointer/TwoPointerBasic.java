package twopointer;

import java.util.ArrayList;
import java.util.List;

public class TwoPointerBasic {

	public static int arraySign(int[] nums) {
		int negativeCount = 0;

		for (int num : nums) {
			if (num == 0) {
				return 0;
			}
			if (num < 0) {
				negativeCount++;
			}
		}
		return negativeCount % 2 == 0 ? 1 : -1;
	}

	public static int kthFactor(int n, int k) {
		for (int i = 1; i * i <= n; i++) {
			if (n % i == 0) {
				k--;

				if (k == 0) {
					return i;
				}
			}
		}
		for (int i = (int) Math.sqrt(n); i >= 1; i--) {

			if (n % i == 0 && i != n / i) {
				k--;

				if (k == 0) {
					return n / i;
				}
			}
		}

		return -1;

	}

	public static int longestCommonPrefix(String s, String t) {
		if (s == null || t == null) {
			return 0;
		}
		int i = 0;
		while (i < s.length() && i < t.length() && s.charAt(i) == t.charAt(i)) {
			i++;
		}
		if (i == s.length() || i == t.length()) {
			return i;
		}
		int j = 0;
		while (i + 1 + j < s.length() && i + j < t.length() && s.charAt(i + j + 1) == t.charAt(i + j)) {
			j++;
		}
		return i + j;

	}

	public static void main(String[] args) {
		String s = "madxa", t = "madam";// output =4
		// System.out.println(longestCommonPrefix(s, t));
		int n = 12, k = 3;
		// System.out.println(kthFactor(n, k));
		int[] nums = { 41, 65, 14, 80, 20, 10, 55, 58, 24, 56, 28, 86, 96, 10, 3, 84, 4, 41, 13, 32, 42, 43, 83, 78, 82,
				70, 15, -41 };
		System.out.println(arraySign(nums));

	}

}
