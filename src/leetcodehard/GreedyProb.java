package leetcodehard;

public class GreedyProb {

	public static boolean increasingTripletBrute(int[] nums) {
		for (int i = 0; i < nums.length - 2; i++) {
			for (int j = i + 1; j < nums.length - 1; j++) {
				for (int k = j + 1; k < nums.length; k++) {
					if (i < j && j < k && (nums[i] < nums[j] && nums[j] < nums[k])) {
						return true;
					}
				}
			}
		}

		return false;

	}

	public static boolean increasingTriplet(int[] nums) {

		int first = Integer.MAX_VALUE;
		int second = Integer.MAX_VALUE;
		for (int num : nums) {

			if (num <= first) {
				first = num;
			}
			else if (num <= second) {
				second = num;
			} else {
				return true;
			}

		}

		return false;

	}

	public static void main(String[] args) {
		int[] nums = { 1, 2, 3, 4, 5 };
		System.out.println(increasingTriplet(nums));

	}

}
