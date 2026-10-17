package monotonicstack;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class LockFreeStack {

	// A simple Node class for our linked structure
	private static class Node {
		final int val;
		Node next;

		Node(int val) {
			this.val = val;
		}
	}

	// AtomicReference acts as our CAS control for the top of the stack
	private final AtomicReference<Node> head = new AtomicReference<>(null);
	private final AtomicInteger len = new AtomicInteger(0);
	private final int capacity;

	public LockFreeStack(int capacity) {
		this.capacity = capacity;
	}

	public void push(int el) throws Exception {
		while (true) {
			int currentSize = len.get();
			if (currentSize >= capacity) {
				throw new Exception("Stack is full");
			}

			Node oldHead = head.get();
			Node newHead = new Node(el);
			newHead.next = oldHead;

			if (head.compareAndSet(oldHead, newHead)) {
				len.incrementAndGet();
				return;
			}

		}
	}

	public int pop() {

		while (true) {
			Node oldHead = head.get();
			if (oldHead == null) {
				throw new IllegalArgumentException("Stack is empty");
			}

			Node newHead = oldHead.next;

			if (head.compareAndSet(oldHead, newHead)) {
				len.decrementAndGet();
				return oldHead.val;
			}
		}

	}

	public int peek() {
		Node currentHead = head.get();
		if (currentHead == null) {
			throw new IllegalArgumentException("Stack is empty");
		}
		return currentHead.val;

	}

	public int size() {
		return len.get();
	}

	public void traverse() {
		Node current = head.get();
		System.out.print("[");
		while (current != null) {
			System.out.print(current.val);
			System.out.print(current.val + (current.next != null ? ", " : ""));

		}
		System.out.print("]");
	}

}
