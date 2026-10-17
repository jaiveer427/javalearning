package searchingDemo;

public class ArraySearchDemo {

	public static int linearSearchNum(int[] arr, int target) {

		int turn = 0;
		for (int i = 0; i < arr.length; i++) {
			turn++;
			if (arr[i] == target) {
				System.out.println("Total no of turn to find the element : " + turn);
				return i + 1;
			}

		}

		return -1;

	}

	public static int binarySearchNum(int[] nums, int target) {
		int left = 0;
		int right = nums.length - 1;

		int turn = 0;

		while (left < right) {
			int mid = left + (right - left) / 2;
			turn++;
			if (nums[mid] == target) {
				System.out.println("Total no of turn to find the element using binary : " + turn);
				return mid + 1;
			} else if (nums[mid] < target) {
				left = mid + 1;

			} else {
				right = mid - 1;
			}

		}

		return -1;

	}

	public static int binarySearchRecursive(int[] nums, int target, int low, int high) {
		if (low > high) {
			return -1;
		}
		int mid = low + (high - low) / 2;
		if (nums[mid] == target) {
			return mid;
		}
		if (nums[mid] < target) {
			return binarySearchRecursive(nums, target, mid + 1, high);
		}
		return binarySearchRecursive(nums, target, low, mid - 1);

	}
	
	
	

	public static void main(String[] args) {
		
		int[] nums = { 1, 2, 3, 4, 5, 6, 10, 11, 12 };
		// System.out.println(linearSearchNum(nums, 10));
		// System.out.println(binarySearchNum(nums, 10));
		System.out.println(binarySearchRecursive(nums, 10, 0, nums.length - 1));

	}

}
