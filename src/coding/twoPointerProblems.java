package coding;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class twoPointerProblems {

	public static int[] targetSum(int[] arr, int target) {

		if (arr == null || arr.length == 0) {
			return new int[] {};
		}
		int left = 0;
		int right = arr.length - 1;
		while (left < right) {
			int sum = arr[left] + arr[right];
			if (sum == target) {
				return new int[] { left, right };
			}
			if (sum < target) {
				left++;
			} else {
				right--;
			}
		}

		return new int[] {};
	}
	// target sum using hashmap

	public static int[] targetSumUsigMap(int[] arr, int target) {

		if (arr == null || arr.length == 0) {
			return new int[] {};
		}
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();

		for (int i = 0; i < arr.length; i++) {
			int diff = target - arr[i];
			if (map.containsKey(diff)) {
				return new int[] { map.get(diff), i };

			}
			map.put(arr[i], i);

		}
		return new int[] {};
	}

	public static boolean isAlphabeticPalindrome(String code) {
		if (code == null || code.length() <= 0) {
			return false;
		}
		int left = 0;
		int right = code.length() - 1;
		while (left < right) {

			while (!Character.isLetter(code.charAt(left))) {
				left++;

			}
			while (!Character.isLetter(code.charAt(right))) {
				right--;

			}
			if (Character.toLowerCase(code.charAt(left)) != Character.toLowerCase(code.charAt(right))) {
				return false;
			}
			left++;
			right--;

		}

		return true;

	}

	public static int[] removeDuplicatesArr(int[] arr) {
		if (arr == null || arr.length == 0) {
			return arr;
		}
		int slow = 0;
		for (int fast = 1; fast < arr.length; fast++) {
			if (arr[fast] != arr[slow]) {
				slow++;
				arr[slow] = arr[fast];
			}
		}

		int[] result = new int[slow + 1];
		for (int i = 0; i < result.length; i++) {
			result[i] = arr[i];
		}
		return result;
	}

	public static String reverseString(String s) {
		if (s == null || s.length() == 0) {
			return s;

		}
		char[] chars = s.toCharArray();
		int left = 0;
		int right = chars.length - 1;
		while (left < right) {
			char temp = chars[left];
			chars[left] = chars[right];
			chars[right] = temp;
			left++;
			right--;

		}

		return new String(chars);

	}

	public static void main(String[] args) {
		System.out.println(Arrays.toString(targetSum(new int[] { 2, 7, 11, 15 }, 9)));
		System.out.println(Arrays.toString(targetSumUsigMap(new int[] { 2, 7, 11, 15 }, 9)));
		System.out.println(Arrays.toString(removeDuplicatesArr(new int[] { 1, 1, 2 })));

	}

}
