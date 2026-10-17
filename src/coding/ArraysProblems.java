package coding;

import java.util.Arrays;
import java.util.List;

public class ArraysProblems {
    /*
     * Target Index Search Given a sorted array of distinct integers and a target
     * value, return the index of the target or -1 if not found.
     */

    /*
     * public static int binarySearch(List<Integer> nums, int target) {
     * if(nums==null || nums.size()<=0) { return -1; }
     *
     * return nums.indexOf(target); }
     */


    public static  int maximumWealth(int[][] accounts) {
        if (accounts.length==0) return 0;
        int m=accounts.length;
        int n=accounts[0].length;
        int sum=Integer.MIN_VALUE;
        for(int i=0;i<m;i++){
            int s=0;
            for(int j=0;j<n;j++){
                s+=accounts[i][j];
            }
            sum=Integer.max(sum,s);
        }
        return sum;
    }
    public static  int[] runningSum(int[] nums) {
        if (nums.length == 0) return nums;
        for (int i = 1; i < nums.length; i++) {

            nums[i] = nums[i - 1] + nums[i];

        }
        return nums;
    }
    public static int binarySearch(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int low = 0;
        int high = nums.length - 1;

        while (low < high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return -1;
    }

    public static int recursiveSearch(int[] arr, int low, int high, int target) {
        // Base case: if range is invalid, target is not present
        if (low > high) {
            return -1;
        }

        // Calculate mid safely to avoid integer overflow
        int mid = low + (high - low) / 2;

        // Check if target is at mid
        if (arr[mid] == target) {
            return mid;
        }

        // If target is smaller, search the left subarray
        if (target < arr[mid]) {
            return recursiveSearch(arr, low, mid - 1, target);
        }

        // Otherwise, search the right subarray
        return recursiveSearch(arr, mid + 1, high, target);
    }

    public static int findSmallestMissingPositiveinList(List<Integer> list) {

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

    public static int findSmallestMissingPositiveinList(int[] arr) {


        return 0;

    }

    public static int hourglassSum(int[][] arr) {
        int max = Integer.MIN_VALUE;
        int row = arr.length;
        int col = arr[0].length;

        for (int i = 0; i <= row - 3; i++) {
            for (int j = 0; j <= col - 3; j++) {
                int sum = arr[i][j] + arr[i][j + 1] + arr[i][j + 2]
                        + arr[i + 1][j + 1] +
                        arr[i + 2][j] + arr[i + 2][j + 1] + arr[i + 2][j + 2];
                max = Integer.max(sum, max);

            }
        }
        return max;
    }

    //using reversal algorithm
    public static void rotateLeft(int d, int[] arr) {

        int n = arr.length - 1;
        d%=n;//handling id  d is greater than the length of the arr
        reverseArr(arr, 0, d-1);
        reverseArr(arr, d, n);
        reverseArr(arr, 0, n);
        //  return arr;

    }

    public static void reverseArr(int[] arr, int start, int end) {

        while (start < end) {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }
    public static  int removeDuplicates(int[] nums) {
        int slow=0;
       for(int i=1;i<nums.length;i++){
           if(nums[i]!=nums[slow]){
               slow++;
           nums[slow]=nums[i];
           }
       }
       System.out.println(Arrays.toString(nums));
       return slow+1;
    }

    public static int maxProfit(int[] prices) {
        return 0;
    }



    public static void main(String[] args) {
        System.out.println(binarySearch(new int[]{1, 2, 3, 4, 5}, 6));
        int[][] arr = {
                {1, 1, 1, 0, 0, 0},
                {0, 1, 0, 0, 0, 0},
                {1, 1, 1, 0, 0, 0},
                {0, 0, 2, 4, 4, 0},
                {0, 0, 0, 2, 0, 0},
                {0, 0, 1, 2, 4, 0}
        };

        // System.out.println("Max Hourglass Sum: " + hourglassSum(arr));
        int[] arr1 = new int[]{
                1, 2, 3, 4, 5
        };
        int d = 4;
        rotateLeft(d,arr1);
        System.out.println(Arrays.toString(arr1));
        int [][] accounts =new int [][]{{1,2,3},{3,2,1}};
        System.out.println(maximumWealth(accounts));

     System.out.println(removeDuplicates(new int[]{0,0,1,1,1,2,2,3,3,4}));

    }


}


