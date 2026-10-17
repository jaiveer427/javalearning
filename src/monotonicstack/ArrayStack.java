package monotonicstack;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class FixedSizeArrayStack {
	private int capacity;
	private static final int CAPACITY = 10;
	private int[] arr;
	private volatile int top = -1;
	private volatile int len = 0;

	public FixedSizeArrayStack() {
		this.capacity = CAPACITY;
		this.arr = new int[CAPACITY];
	}

	public FixedSizeArrayStack(int capacity) {
		this.capacity = capacity;
		this.arr = new int[capacity];
	}

	public synchronized void push(int el) {
		if (isFull()) {
			throw new IllegalArgumentException("Stack is full");

		}

		arr[++this.top] = el;
		this.len++;
	}

	public synchronized int size() {
		return this.len;
	}

	public synchronized int pop() {
		if (isEmpty()) {
			throw new IllegalArgumentException("Stack is empty");
		}
		int temp = arr[top];
		top--;
		len--;
		return temp;
	}

	public synchronized int peek() {
		if (isEmpty()) {
			throw new IllegalArgumentException("Stack is empty");
		}
		return arr[top];
	}

	public synchronized void traverse() {
		System.out.print("[");
		for (int i = 0; i < this.size(); i++) {
			System.out.print(arr[i] + " ");
			System.out.print(i >= 0 && i < top ? "," : "");

		}
		System.out.print("]\n");
	}

	public boolean isEmpty() {
		return top < 0;
	}

	public boolean isFull() {
		return top + 1 >= capacity;

	}

}

public class ArrayStack {

	public static void main(String[] args) throws Exception {
		// Create a stack with room for exactly 10 items
		// FixedSizeArrayStack stack = new FixedSizeArrayStack(10);
		LockFreeStack stack = new LockFreeStack(10);

		// We will use 2 threads to push items simultaneously
		ExecutorService executor = Executors.newFixedThreadPool(2);

		// Thread 1 pushes numbers 1 to 5
		executor.submit(() -> {
			for (int i = 1; i <= 5; i++) {
				try {
					stack.push(i);
					Thread.sleep(10); // Artificial delay to increase the chance of collision
				} catch (Exception e) {
					System.out.println("Thread 1 Error: " + e.getMessage());
				}
			}
		});

		// Thread 2 pushes numbers 6 to 10
		executor.submit(() -> {
			for (int i = 6; i <= 10; i++) {
				try {
					stack.push(i);
					Thread.sleep(10); // Artificial delay to increase the chance of collision
				} catch (Exception e) {
					System.out.println("Thread 2 Error: " + e.getMessage());
				}
			}
		});

		// Wait for both threads to finish completely
		executor.shutdown();
		executor.awaitTermination(1, TimeUnit.SECONDS);

		// Print results
		System.out.println("--- EXPERIMENT RESULTS ---");
		System.out.print("Stack Contents: ");
		stack.traverse();
		System.out.println("Expected Size: 10");
		System.out.println("Actual Size reported: " + stack.size());

	}

}
