package coding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StrictAverageSum {

	public static int countResponseTimeRegressions(List<Integer> list) {
		if (list == null || list.size() <= 1) {
			return 0;
		}

		int count = 0;
		long sum = list.get(0);

		for (int i = 1; i < list.size(); i++) {
			double avg = (double) sum / i;

			if (list.get(i) > avg) {
				count++;
			}
			sum += list.get(i);
		}

		return count;

	}

	// orderNumbers = [3, 4, -1, 1]

	public static int findSmallestMissingPositive(List<Integer> list) {

		int n = list.size();
		for (int i = 0; i < n; i++) {

			while (list.get(i) > 0 && list.get(i) <= n && !list.get(list.get(i) - 1).equals(list.get(i))) {

				int correctIndex = list.get(i) - 1;
				int temp = list.get(i);
				list.set(i, list.get(correctIndex));
				list.set(correctIndex, temp);

			}

		}
		for (int i = 0; i < n; i++) {

			if (list.get(i) != i + 1) {
				return i + 1;
			}
		}

		return n + 1;

	}

	/*
	 * public static boolean isAlphabeticPalindrome(String code) { StringBuilder
	 * sb=new StringBuilder(); for(int i=0;i<code.length();i++) {
	 * if(Character.isLetter(code.charAt(i))) {
	 * sb.append(Character.toLowerCase(code.charAt(i))); } } String original =
	 * sb.toString(); String reversed = sb.reverse().toString();
	 * if(sb.equals(sb.reverse())) { return true; }
	 * 
	 * return false;
	 * 
	 * }
	 */

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

	public static void main(String[] args) {
		List<Integer> list = List.of(100, 200, 150, 300);

		// System.out.println(countResponseTimeRegressions(list));
		List<Integer> list1 = new ArrayList<>(Arrays.asList(3, 4, -1, 1));

		// System.out.println(findSmallestMissingPositive(list1));
		System.out.println(isAlphabeticPalindrome("A1b2B!a") ? 1 : 0);

	}

}
