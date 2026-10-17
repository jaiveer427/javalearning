package coding;

import java.util.List;

public class ListProblems {



	public static List<Integer> rotateLeft(int d, List<Integer> arr) {
		int n=arr.size();
		d%=n;
		reverseArr(arr, 0,d-1);
		reverseArr(arr, d, n-1);
		reverseArr(arr, 0, n-1);
		return arr;

	}

	public static void reverseArr(List<Integer> arr, int start, int end) {

		while (start < end) {
			int temp = arr.get(start);
			arr.set(start, arr.get(end));
			arr.set(end, temp);
			start++;
			end--;
		}
	}




/*
	 * Maximum Number of Non-Overlapping Intervals Given an array of intervals where
	 * each interval has a start and end time, return the maximum number of
	 * non-overlapping intervals.
	 */

	public static void main(String[] args) {


	}


}