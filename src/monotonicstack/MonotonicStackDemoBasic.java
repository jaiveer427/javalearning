package monotonicstack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Stack;

public class MonotonicStackDemoBasic {

	public void monotonicStackTemplate(int[] nums) {
		Deque<Integer> stack = new ArrayDeque<>(); // Usually stores indices

		for (int i = 0; i < nums.length; i++) {
			// STEP 1: Pop elements that violate the monotonic property
			while (!stack.isEmpty() && nums[stack.peek()] < nums[i]) { // Change '<' to '>' for increasing
				int poppedIndex = stack.pop();
				// Process the popped element (e.g., update its 'Next Greater' value)
			}

			// STEP 2: Make decisions using the remaining element at the top of the stack
			if (!stack.isEmpty()) {
				int nearestElementIndex = stack.peek();
			}

			// STEP 3: Push current element/index onto the stack
			stack.push(i);
		}

	}

	public static Deque<Integer> increasingStack(int[] nums) {
		Deque<Integer> stack = new ArrayDeque<Integer>();
		for (int i = 0; i < nums.length; i++) {
			while (!stack.isEmpty() && stack.peek() > nums[i]) {
				stack.pop();
			}
			stack.push(nums[i]);
		}
		return stack;

	}

	public static <T> void printStack(Deque<T> stack) {
		System.out.print("[");
		Iterator<T> it = stack.descendingIterator();

		while (it.hasNext()) {
			System.out.print(it.next() + (it.hasNext() ? "," : ""));
		}

		System.out.println("]");

	}
	/*
	 * Given an array of integers temperatures represents the daily temperatures,
	 * return an array answer such that answer[i] is the number of days you have to
	 * wait after the ith day to get a warmer temperature. If there is no future day
	 * for which this is possible, keep answer[i] == 0 instead.
	 * 
	 */

	public static int[] dailyTemperatures(int[] temperatures) {
		int n = temperatures.length;
		int[] result = new int[n];
		Deque<Integer> stack = new ArrayDeque<>();

		for (int i = 0; i < n; i++) {
			while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
				int prevDayIndex = stack.pop();
				result[prevDayIndex] = i - prevDayIndex;
			}
			stack.push(i);

		}
		return result;

	}

	public static int[] dailyTemperaturesArr(int[] temperatures) {
		int n = temperatures.length;
		int[] result = new int[n];

		int[] stack = new int[n];
		int top = -1;
		for (int i = n - 1; i >= 0; i--) {
			while (top >= 0 && temperatures[stack[top]] <= temperatures[i]) {
				top--;
			}
			if (top >= 0) {
				result[i] = stack[top] - i;
			}
			stack[++top] = i;

		}
		return result;

	}
	/*
	 * The next greater element of some element x in an array is the first greater
	 * element that is to the right of x in the same array.
	 * 
	 * You are given two distinct 0-indexed integer arrays nums1 and nums2, where
	 * nums1 is a subset of nums2.
	 * 
	 * For each 0 <= i < nums1.length, find the index j such that nums1[i] ==
	 * nums2[j] and determine the next greater element of nums2[j] in nums2. If
	 * there is no next greater element, then the answer for this query is -1.
	 * 
	 * Return an array ans of length nums1.length such that ans[i] is the next
	 * greater element as described above.
	 */

	public static int[] nextGreaterElement(int[] nums1, int[] nums2) {
		Map<Integer, Integer> nextGreaterMap = new HashMap<>();
		Deque<Integer> stack = new ArrayDeque<>();
		int[] result = new int[nums1.length];
		for (int num : nums2) {
			while (!stack.isEmpty() && num > stack.peek()) {
				nextGreaterMap.put(stack.pop(), num);
			}
			stack.push(num);
		}
		for (int i = 0; i < nums1.length; i++) {
			result[i] = nextGreaterMap.getOrDefault(nums1[i], -1);

		}
		return result;

	}
	/*
	 * Given a circular integer array nums (i.e., the next element of
	 * nums[nums.length - 1] is nums[0]), return the next greater number for every
	 * element in nums.
	 * 
	 * The next greater number of a number x is the first greater number to its
	 * traversing-order next in the array, which means you could search circularly
	 * to find its next greater number. If it doesn't exist, return -1 for this
	 * number.
	 */

	public static int[] nextGreaterElementsII(int[] nums) {
		Deque<Integer> stack = new ArrayDeque<>();
		int[] result = new int[nums.length];
		for (int i = 0; i < 2 * nums.length; i++) {
			int circularIndex = i % nums.length;
			System.out.println(nums[circularIndex]);
			while (!stack.isEmpty() && nums[circularIndex] > nums[stack.peek()]) {
				int poppedIndex = stack.pop();
				result[poppedIndex] = nums[circularIndex];
			}
		}
		return result;

	}

	public static void main(String[] args) {

		// int[] nums = { 2, 1, 4, 3, 4 };
		// int[] temperatures = { 73, 74, 75, 71, 69, 72, 76, 73 };
		// System.out.println(Arrays.toString(dailyTemperatures(temperatures)));
		// System.out.println(Arrays.toString(dailyTemperaturesArr(temperatures)));

		// printStack(increasingStack(nums));
		// int[] nums1 = { 4, 1, 2 }, nums2 = { 1, 3, 4, 2 };
		// System.out.println(Arrays.toString(nextGreaterElement(nums1, nums2)));
		int[] nums = { 1, 2, 1 };
		System.out.println(Arrays.toString(nextGreaterElementsII(nums)));

	}

}
