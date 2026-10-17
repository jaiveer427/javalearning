package coding;

public class SlidingWindowProblem {
	// SLIDING WINDOW PROBLEM SET (WITH INPUT & OUTPUT)
	/*
	 * Maximum Sum Subarray of Size K
	 * 
	 * Problem: Given an array of integers and a number k, find the maximum sum of
	 * any contiguous subarray of size k.
	 * 
	 * Input
	 * 
	 * arr = [2, 1, 5, 1, 3, 2] k = 3
	 */

	public static int maxSumSubArray(int[] arr, int k) {

		if (arr == null || arr.length < k) {
			throw new IllegalArgumentException("Not a valid input");

		}

		int maxSum = Integer.MIN_VALUE;
		int windowSum = 0;
		for (int i = 0; i < arr.length; i++) {
			windowSum += arr[i];
			if (i >= k - 1) {
				maxSum = Math.max(maxSum, windowSum);
				windowSum -= arr[i - k + 1];
			}

		}
		return maxSum;

	}

	public static void main(String[] args) {
		int []numbers={-2, -1, -5, -1, -3, -2};
		
		System.out.println(maxSumSubArray(numbers, 3));

	}

}
