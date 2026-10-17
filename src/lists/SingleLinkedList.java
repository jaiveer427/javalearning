package lists;


public class SingleLinkedList {

	public static ListNode insertHead(ListNode head, int value) {
		ListNode node = new ListNode(value);
		node.next = head;
		head = node;

		return head;

	}

	public static ListNode insertTail(ListNode head, int value) {
		ListNode newNode = new ListNode(value);
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

	public static ListNode insertMiddle(ListNode head, int val, int index) {

		if (index < 0 || index >= length(head)) {
			throw new IllegalArgumentException("Invalid index");
		}
		if (index == 0) {
			return insertHead(head, val);
		}

		ListNode current = head;
		ListNode newNode = new ListNode(val);
		for (int i = 0; i < index - 1; i++) {
			current = current.next;
		}
		newNode.next = current.next;
		current.next = newNode;

		return head;
	}

	public static ListNode delete(ListNode head, int value) {
		if (head == null) {
			System.out.println("List is empty");

		}
		if (head.val == value) {
			head = head.next;

		}

		ListNode current = head;
		while (current.next != null && current.next.val != value) {
			current = current.next;

		}
		if (current.next == null) {
			System.out.println("Value " + value + " not found in the list.");

		}
		current.next = current.next.next;

		return head;

	}

	public static ListNode deleteFirst(ListNode head) {
		ListNode curr = head;
		head = curr.next;
		return head;

	}

	public static ListNode deleteLast1(ListNode head) {
		if (head == null) {
			return null;
		}
		if (head.next == null) {
			return null;
		}

		ListNode curr = head;
		while (curr.next.next != null) {

			curr = curr.next;

		}
		curr.next = null;

		return head;

	}

	public static ListNode deleteLast(ListNode head) {

		if (head == null) {
			return null;
		}
		if (head.next == null) {
			return null;
		}
		ListNode prev = null;
		ListNode curr = head;
		while (curr.next != null) {
			prev = curr;
			curr = curr.next;

		}
		prev.next = null;

		return head;

	}

	public static ListNode deleteMiddle(ListNode head) {
		if (head == null) {
			return null;
		}
		ListNode prev = null;
		ListNode slow = head;
		ListNode fast = head;

		while (fast != null && fast.next != null) {
			fast = fast.next.next;
			prev = slow;
			slow = slow.next; // slow one is at the middle node ,so we have to skip the middle node
		}
		prev.next = slow.next;
		return head;

	}

	public static ListNode deleteNthNode(ListNode head, int n) {
		if (head == null) {
			return null;
		}
		int len = length(head);
		if (n < 0 || n >= len) {
			System.out.println("Invalid position");

			return null;
		}
		int targetIndex = len - 1 - n;
		if (targetIndex == 0) {
			return head.next;
		}
		ListNode curr = head;
		for (int i = 0; i < targetIndex - 1; i++) {
			curr = curr.next;
		}
		curr.next = curr.next.next;

		return head;

	}

	public static ListNode deleteNthNodeOnePass(ListNode head, int n) {
		if (head == null || n <= 0) {
			return head;
		}

		ListNode dummy = new ListNode(0);
		dummy.next = head;

		ListNode slow = dummy;
		ListNode fast = dummy;
		for (int i = 0; i < n; i++) {
			fast = fast.next;
			if (fast == null) {
				System.out.println("Invalid position: n is greater than list length");
				return head;
			}
		}
		while (fast.next != null) {
			fast = fast.next;
			slow = slow.next;
		}
		slow.next = slow.next.next;

		return dummy.next;

	}

	

	public static ListNode reverse(ListNode head) {
		ListNode prev = null;
		ListNode curr = head;
		while (curr != null) {
			ListNode next = curr.next;
			curr.next = prev;
			prev = curr;
			curr = next;
		}
		return prev;
	}

	public static void traverse(ListNode head) {
		ListNode current = head;
		while (current != null) {
			System.out.print(current.val + " ");
			current = current.next;
		}
	}

	public static int length(ListNode head) {
		int count = 0;
		ListNode current = head;
		while (current != null) {
			count++;
			current = current.next;

		}
		return count;
	}

	public static void main(String[] args) {

		ListNode head = new ListNode(10);
		traverse(head);
		System.out.println();
		System.out.println(length(head));

		head = insertHead(head, 20);
		traverse(head);
		System.out.println();
		System.out.println(length(head));

		head = insertTail(head, 30);
		traverse(head);
		System.out.println();
		System.out.println(length(head));
		head = insertMiddle(head, 25, 2);
		traverse(head);
		System.out.println();
		// System.out.println(length(head));
		// head = deleteFirst(head);
		// head = deleteFirst(head);
		// head = deleteMiddle(head);
		// head = deleteLast(head);
		head = deleteNthNodeOnePass(head, 1);
		traverse(head);
		System.out.println();
		System.out.println(length(head));
		// head = reverse(head);
		// traverse(head);
		// System.out.println();

	}

}
