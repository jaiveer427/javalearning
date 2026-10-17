package lists;

public class SinglyList {
	public static int length(ListNode head) {
		int count = 0;
		if (head == null) {
			return count;
		}
		ListNode current = head;
		while (current != null) {
			count++;
			current = current.next;
		}

		return count;

	}

	public static void traverse(ListNode head) {
		if (head == null) {
			System.out.println("List is empty");
			return;
		}
		ListNode current = head;
		while (current != null) {
			System.out.print(current.val + " ");
			current = current.next;
		}

	}

	public static ListNode insertHead(ListNode head, int val) {
		ListNode newNode = new ListNode(val);
		if (head == null) {
			return newNode;
		}
		newNode.next = head;
		head = newNode;
		return head;

	}

	public static ListNode insertMiddle(ListNode head, int val, int index) {
		if (index < 0 || index >= length(head)) {
			throw new IllegalArgumentException("Invalid index");
		}
		if (index == 0) {
			return insertHead(head, val);
		}

		if (index == 0) {
			return insertHead(head, val);
		}
		ListNode newNode = new ListNode(val);
		ListNode current = head;
		for (int i = 0; i < index - 1; i++) {
			current = current.next;

		}
		ListNode next = current.next;
		current.next = newNode;
		newNode.next = next;
		return head;

	}

	public static ListNode insertTail(ListNode head, int val) {

		ListNode newNode = new ListNode(val);
		if (head == null) {
			return newNode;
		}
		ListNode current = head;
		while (current.next != null) {
			current = current.next;
		}
		current.next = newNode;

		return head;

	}

	public static ListNode deleteLast(ListNode head) {
		ListNode current = head;
		while (current.next.next != null) {
			current = current.next;
		}
		current.next = null;
		return head;

	}

	public static ListNode deletFirst(ListNode head) {
		ListNode curr = head;
		head = curr.next;
		return head;

	}

	public static ListNode delete(ListNode head, int value) {
		if (head == null) {
			System.out.println("List is empty");

		}
		if (head.val == value) {
			head = head.next;

		}

		ListNode curr = head;
		while (curr.next != null && curr.next.val != value) {
			curr = curr.next;

		}

		return head;

	}

	public static ListNode deleteMiddle(ListNode head, int val, int index) {
		ListNode newNode = new ListNode(val);
		if (head == null) {
			return newNode;
		}
		ListNode slow = head;
		ListNode fast = head;
		int i = 0;
		while (fast != null && fast.next != null && i < index - 1) {
			fast = fast.next;

		}

		return newNode;

	}

	public static void main(String[] args) {
		ListNode head = new ListNode(10);
		head.next = new ListNode(20);
		head.next.next = new ListNode(30);

		traverse(head);
		System.out.println("\nlength : " + length(head));
		head = insertHead(head, 5);
		traverse(head);
		System.out.println("\nlength : " + length(head));
		// head = insertMiddle(head, 40, 2);
		head = deletFirst(head);
		traverse(head);
		System.out.println("\nlength : " + length(head));

	}

}
