package slidingwindow;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SlidingWinodwFixed {

	public static int longestContinuousSubstring(String s) {
		int currentlength = 1;
		int maxLen = 1;
		for (int i = 0; i < s.length() - 1; i++) {

			if (s.charAt(i) + 1 == s.charAt(i + 1)) {
				currentlength++;
			} else {

				currentlength = 1;
			}
			maxLen = Math.max(maxLen, currentlength);
		}
		return maxLen;

	}

	public static int[] longestContinuousSubstringIndex(String s) {
		int currentlength = 1;
		int maxLen = 1;
		int startIndex = 0;
		int maxStart = 0;
		for (int i = 0; i < s.length() - 1; i++) {
			if (s.charAt(i) + 1 == s.charAt(i + 1)) {

				currentlength++;
			} else {
				startIndex = i + 1;
				currentlength = 1;
			}
			if (maxLen < currentlength) {
				maxStart = startIndex;

				maxLen = currentlength;
			}

		}

		return new int[] { maxStart, maxStart + maxLen - 1 };
	}

	/*
	 * Given a binary array nums and an integer k, return the maximum number of
	 * consecutive 1's in the array if you can flip at most k 0's.
	 */
	public int longestOnes(int[] nums, int k) {
		int left = 0;
		int zeros = 0;
		int maxLen = 0;

		for (int right = 0; right < nums.length; right++) {
			if (nums[right] == 0) {
				zeros++;
			}

			while (zeros > k) {
				if (nums[left] == 0) {
					zeros--;
				}
				left++;
			}
			maxLen = Math.max(maxLen, right - left + 1);

		}

		return maxLen;

	}

	/*
	 * You are given a binary array nums, you should delete one element from it.
	 * 
	 * Return the size of the longest non-empty subarray containing only 1's in the
	 * resulting array. Return 0 if there is no such subarray.
	 * 
	 */
	public int longestSubarray(int[] nums) {
		int maxLen = 0;
		int left = 0;
		int zeros = 0;
		for (int right = 0; right < nums.length; right++) {

			if (nums[right] == 0) {
				zeros++;
			}
			while (zeros > 1) {
				if (nums[left] == 0) {
					zeros--;
				}
			}
			maxLen = Math.max(maxLen, right - left);

		}
		return maxLen;

	}

	public static int longestContinuousSubstringC(String s) {
		char[] chars = s.toCharArray();
		int start = 0;
		int currentlength = 1;
		int maxLen = 1;
		while (currentlength < s.length()) {
			while (currentlength < s.length() && (chars[currentlength] - chars[currentlength - 1] == 1)) {
				currentlength++;

			}
			maxLen = Math.max(maxLen, currentlength - start);
			start = currentlength;
			currentlength = start + 1;

		}
		return maxLen;

	}

	public static int maxVowels(String s, int k) {
		if (s == null || k <= 0) {
			return 0;
		}

		int currentCount = 0;

		for (int i = 0; i < k; i++) {
			char c = s.charAt(i);
			if (isVowel(c)) {
				currentCount++;
			}
		}
		int maxCount = currentCount;

		for (int i = k; i < s.length(); i++) {
			char c = s.charAt(i);
			if (isVowel(c)) {
				currentCount++;
			}
			if (isVowel(s.charAt(i - k))) {
				currentCount--;
			}
			maxCount = Math.max(maxCount, currentCount);
		}
		return maxCount;

	}

	private static boolean isVowel(char c) {
		return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
	}

	public static int countVowels(String s) {
		int count = 0;
		for (int i = 0; i < s.length(); i++) {
			if (isVowel(s.charAt(i))) {
				count++;
			}
		}
		return count;

	}

	public static int findMaxSum(int[] nums, int k) {
		int windowSum = 0;

		for (int i = 0; i < k; i++) {
			windowSum += nums[i];
		}
		int maxSum = windowSum;
		for (int j = k; j < nums.length; j++) {
			windowSum += nums[j] - nums[j - k];
			maxSum = Math.max(maxSum, windowSum);
		}
		return maxSum;

	}

	public static double findMaxAverage(int[] nums, int k) {

		int sum = 0;
		for (int i = 0; i < k; i++) {
			sum += nums[i];
		}

		int maxSum = sum;
		for (int i = k; i < nums.length; i++) {

			sum += nums[i] - nums[i - k];

			maxSum = Math.max(maxSum, sum);

		}
		return (double) maxSum / k;

	}

	public static int findMaxProduct(int[] nums, int k) {
		int maxProd = 1;
		int windowProd = 1;

		for (int i = 0; i < k; i++) {

			windowProd *= nums[i];
		}
		for (int i = k; i < nums.length; i++) {

			windowProd = windowProd * nums[i];

			windowProd /= nums[i - k];
			if (windowProd > maxProd) {
				maxProd = windowProd;
			}

		}
		return maxProd;
	}

	public static double findMaxAverageBrute(int[] nums, int k) {
		int maxSum = Integer.MIN_VALUE;
		for (int i = 0; i <= nums.length - k; i++) {
			int sum = 0;
			for (int j = i; j < i + k; j++) {
				sum += nums[j];
			}
			maxSum = Math.max(maxSum, sum);

		}

		return (double) maxSum / k;

	}

	public static void reverse(int[] nums) {
		int left = 0;
		int right = nums.length - 1;
		while (left < right) {
			int temp = nums[left];
			nums[left] = nums[right];
			nums[right] = temp;
			left++;
			right--;
		}

	}

	public static long maximumSubarraySum(int[] nums, int k) {
		int left = 0;
		long maxSum = 0;
		Set<Integer> set = new HashSet<Integer>();
		long sum = 0;
		for (int right = 0; right < nums.length; right++) {

			while (set.contains(nums[right])) {

				set.remove(nums[left]);
				sum -= nums[left];
				left++;
			}
			set.add(nums[right]);
			sum += nums[right];
			if (right - left + 1 > k) {
				set.remove(nums[left]);
				sum -= nums[left];
				left++;

			}
			if (right - left + 1 == k) {
				maxSum = Math.max(maxSum, sum);
			}
		}
		return maxSum;

	}

	public static void main(String[] args) {

		int[] nums = { 1, 12, -5, -6, 50, 3 };
		int k = 3;
		int[] nums1 = { 1, 5, 4, 2, 9, 9, 9 };

		// System.out.println(findMaxAverageBrute(nums, k));
		// System.out.println(findMaxSum(nums, k));
		String s = "abcdiiidefg";
		// int k = 3;
		// System.out.println(maxVowels(s, k));
		// System.out.println(Arrays.toString(longestContinuousSubstringIndex(s)));

		// System.out.println(findMaxProduct(nums, k));
		reverse(nums);
		// System.out.println(Arrays.toString(nums));
		System.out.println(maximumSubarraySum(nums1, k));

	}

}
