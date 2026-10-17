package coding.slidingWindow;

import java.util.Arrays;

public class SlidingWindowProblem {

	/*
	 * You are entering a competition, and are given two positive integers
	 * initialEnergy and initialExperience denoting your initial energy and initial
	 * experience respectively.
	 * 
	 * You are also given two 0-indexed integer arrays energy and experience, both
	 * of length n.
	 * 
	 * You will face n opponents in order. The energy and experience of the ith
	 * opponent is denoted by energy[i] and experience[i] respectively. When you
	 * face an opponent, you need to have both strictly greater experience and
	 * energy to defeat them and move to the next opponent if available.
	 * 
	 * Defeating the ith opponent increases your experience by experience[i], but
	 * decreases your energy by energy[i].
	 * 
	 * Before starting the competition, you can train for some number of hours.
	 * After each hour of training, you can either choose to increase your initial
	 * experience by one, or increase your initial energy by one.
	 * 
	 * Return the minimum number of training hours required to defeat all n
	 * opponents.
	 * 
	 */

	public static int minNumberOfHours(int initialEnergy, int initialExperience, int[] energy, int[] experience) {
		int trainingHour = 0;
		int totalEnergy = 0;
		for (int en : energy) {
			totalEnergy += en;
		}

		trainingHour = totalEnergy - initialEnergy + 1;
		int currentExp = initialExperience;
		for (int i = 0; i < experience.length; i++) {

			if (currentExp <= experience[i]) {
				int need = experience[i] + 1 - currentExp;

				trainingHour += need;

				currentExp += need;
			}
			currentExp += experience[i];
		}
		return trainingHour;

	}

	/*
	 * private static int sum(int[] arr) { return Arrays.stream(arr).sum(); }
	 */

	public static int getWinner(int[] arr, int k) {
		int champ = arr[0];
		int wins = 0;
		k = Math.min(arr.length, k);

		for (int i = 1; i < arr.length - 1; i++) {

			if (champ > arr[i]) {
				wins++;
			} else {
				champ = arr[i];
				wins = 1;
			}

			if (wins == k) {
				return champ;
			}

		}

		return champ;

	}

	public static int[] searchRange(int[] nums, int target) {
		int first = findFirst(nums, target);
		int last = findLast(nums, target);

		return new int[] { first, last };

	}

	private static int findFirst(int[] nums, int target) {

		int ans = -1;
		int left = 0;
		int right = nums.length - 1;

		while (left <= right) {
			int mid = left + (right - left) / 2;
			System.out.println(nums[mid]);
			if (nums[mid] == target) {
				ans = mid;
				right = mid - 1;
			} else if (nums[mid] < target) {
				left = mid + 1;

			} else {
				right = mid - 1;
			}
			System.out.println(ans);
		}

		return ans;

	}

	private static int findLast(int[] nums, int target) {
		int ans = -1;
		int left = 0;
		int right = nums.length - 1;
		while (left <= right) {
			int mid = left + (right - left) / 2;
			if (nums[mid] == target) {
				ans = mid;
				left = mid + 1;
			} else if (nums[mid] < target) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		return ans;
	}

	public static char nextGreatestLetter(char[] letters, char target) {

		char result = letters[0];

		int left = 0;
		int right = letters.length - 1;
		while (left <= right) {
			int mid = left + (right - left) / 2;
			if (letters[mid] > target) {
				result = letters[mid];
				right = mid - 1;
			} else {
				left = mid + 1;
			}

		}

		return result;

	}

	public static void main(String[] args) {

		int initialEnergy = 5;
		int initialExperience = 3;
		int[] energy = { 1, 4, 3, 2 };
		int[] experience = { 2, 6, 3, 1 };

		// Output: 8
		// System.out.println(minNumberOfHours(initialEnergy, initialExperience, energy,
		// experience));
		// int[] arr = {1,11,22,33,44,55,66,77,88,99 };
		// int k =1000000000;
		// Output: 99
//System.out.println(getWinner(arr, k));

		int[] nums = { 5, 7, 7, 8, 8, 10 };
		int target = 8;
		// Output: [3,4]
		// System.out.println(Arrays.toString(searchRange(nums, target)));
		System.out.println(nextGreatestLetter(new char[] { 'c', 'f', 'j' }, 'a'));
	}

}
