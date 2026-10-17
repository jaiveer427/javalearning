package coding.arrays;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ArraySum {

	public static int[] twoSumUsingBruteForce(int[] arr, int target) {
		if (arr == null || arr.length < 2) {
			throw new IllegalArgumentException("Array must have two elements");
		}

		for (int i = 0; i < arr.length; i++) {
			for (int j = i + 1; j < arr.length - 1; j++) {
				int sum = arr[i] + arr[j];
				if (sum == target) {
					return new int[] { i, j };
				}
			}
		}

		throw new IllegalArgumentException("Not able to find the required pair");
	}

	// two sum using two pointer for sorted array

	public static int[] twoSumUsingTwoPointer(int[] arr, int target) {
		if (arr == null || arr.length < 2) {
			throw new IllegalArgumentException("Array must have two elements");
		}
		Arrays.sort(arr);
		int left = 0;
		int right = arr.length - 1;
		while (left < right) {
			int sum = arr[left] + arr[right];
			if (sum == target) {
				return new int[] { left, right };
			} else if (sum < target) {
				left++;
			} else {
				right--;
			}

		}

		throw new IllegalArgumentException("Not able to find the required pair");
	}

	// two sum using hashmap and complement

	public static int[] twoSumUsingHashMap(int[] arr, int target) {
		if (arr == null || arr.length < 2) {
			throw new IllegalArgumentException("Array must have two elements");
		}
		Map<Integer, Integer> pairs = new HashMap<Integer, Integer>();

		for (int i = 0; i < arr.length; i++) {
			int complement = target - arr[i];
			if (pairs.containsKey(complement)) {
				return new int[] { pairs.get(complement), i };
			}
			pairs.put(arr[i], i);
		}

		throw new IllegalArgumentException("Not able to find the required pair");
	}

	public static int containerWithMaxArea(int[] arr) {
		if (arr == null || arr.length < 1) {
			throw new IllegalArgumentException("Array must not be empty");
		}
		int left = 0;
		int right = arr.length - 1;
		int maxArea = 0;
		while (left < right) {
			int height = Math.min(arr[left], arr[right]);
			int width = right - left;
			int area = height * width;
			maxArea = Math.max(area, maxArea);

			if (arr[left] < arr[right]) {
				left++;
			} else {
				right--;
			}

		}

		return maxArea;
	}

	public static int maxProfit(int[] prices) {
		if (prices == null || prices.length == 0) {
			return 0;
		}

		int maxProfit = 0;
		int minPrice = Integer.MAX_VALUE;
		for (int currentPrice : prices) {
			minPrice = Math.min(currentPrice, minPrice);
			maxProfit = Math.max(maxProfit, currentPrice - minPrice);

			/*
			 * if (currentPrice < minPrice) { minPrice = currentPrice;
			 * 
			 * } else if (currentPrice - minPrice > maxProfit) { maxProfit = currentPrice -
			 * minPrice;
			 * 
			 * }
			 */
		}

		return maxProfit;
	}

	// 3Sum

	// count element frequency in array

	public static Map<Integer, Integer> elementFreqInArr(int[] arr) {
		Map<Integer, Integer> freq = new HashMap<Integer, Integer>();

		if (arr == null || arr.length < 1) {
			return freq;
		}

		for (int i = 0; i < arr.length; i++) {

			if (!freq.containsKey(arr[i])) {
				int count = 1;
				for (int j = i + 1; j < arr.length; j++) {
					if (arr[i] == arr[j]) {
						count++;
					}
				}
				freq.put(arr[i], count);
			}

		}

		return freq;
	}

	// Optimized

	public static Map<Integer, Integer> elementFreqInArrOptimized(int[] arr) {
		Map<Integer, Integer> freq = new HashMap<Integer, Integer>();

		if (arr == null || arr.length < 1) {
			return freq;
		}

		for (int num : arr) {
			freq.put(num, freq.getOrDefault(num, 0) + 1);

		}

		return freq;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Instant start = Instant.now();
		// System.out.println(Arrays.toString(twoSumUsingBruteForce(new
		// int[]{2,7,11,15}, 9)));
		// System.out.println(Arrays.toString(twoSumUsingHashMap(new int[] { 3, 3 },
		// 6)));
		// Instant end = Instant.now();
		// Duration duration = Duration.between(start, end);
		// System.out.println(duration.toMillis());

		// System.out.println(containerWithMaxArea(new int[] { 1, 8, 6, 2, 5, 4, 8, 3, 7
		// }));
		// System.out.println(containerWithMaxArea(new int[] { 1, 2, 3, 4, 5 }));

		// System.out.println(maxProfit(new int[] { 7, 6, 4, 3, 1 }));
		System.out.println(elementFreqInArrOptimized(new int[] { 1, 1, 1, 2, 2 }));

	}

}
