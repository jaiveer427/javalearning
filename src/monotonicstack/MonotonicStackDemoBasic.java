package monotonicstack;

import java.util.ArrayDeque;
import java.util.Deque;
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

	public static void main(String[] args) {
		Deque<Integer> stack = new ArrayDeque<>(10);

		for (int i = 1; i <= 10; i++) {
			stack.push(i);
		}

		stack.offer(12);
		System.out.println(stack);
		System.out.println(stack.pop());
		System.out.println(stack);
		System.out.println(stack.peek());
		System.out.println("Size: "+stack.size()+ " Empty or not: "+ stack.isEmpty() );
		stack.offer(13);
		System.out.println(stack);
		System.out.println(stack.size());
		

	}

}
