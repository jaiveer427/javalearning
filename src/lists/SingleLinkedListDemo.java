package lists;



public class SingleLinkedListDemo {
	private ListNode head = null;

	public int getLength() {

		int len = 0;
		ListNode current = head;
		while (current != null) {

			len++;
			current = current.next;
		}
		return len;

	}

	public void traverse() {
		ListNode current = head;
		while (current != null) {
			System.out.print(current.val + " ");
			current = current.next;
		}

	}

	public void insertFirst(int val) {
		ListNode newNode = new ListNode(val);

		newNode.next = head;

		head = newNode;

	}

	public void insertMiddle(int index, int val) {

		if (index < 0 || index > getLength()) {
			System.out.println("Invalid position!");
			return;
		}

		if (index == 0) {
			insertFirst(val);
			return;
		}
		ListNode newNode = new ListNode(val);
		ListNode current = head;

		for (int i = 0; i < index - 1; i++) {
			current = current.next;
		}
		newNode.next = current.next;
		current.next = newNode;
	}

	public void insertEnd(int val) {
		ListNode newNode = new ListNode(val);
		if (head == null) {
			head = newNode;

		}
		ListNode current = head;
		while (current.next != null) {
			current = current.next;
		}
		current.next = newNode;

	}

	public void deleteByVal(int val) {
		if (head == null) {
			System.out.println("List is empty..");
			return;
		}
		ListNode current = head;
		if (head.val == val) {
			head = head.next;
			return;
		}
		while (current.next != null && current.next.val != val) {
			current = current.next;
		}
		if (current.next == null) {
			System.out.println("Value " + val + " not found in the list.");
			return;
		}
		current.next = current.next.next;

	}

	public void deleteByIndex(int index) {
		if (head == null) {
			System.out.println("List is empty..");
			return;
		}
		int length = getLength();
		if (index < 0 || index >= length) {
			System.out.println("Invalid position!");
			return;
		}

		if (index == 0) {
			head = head.next;
			return;
		}

		ListNode current = head;
		for (int i = 0; i < index - 1; i++) {
			current = current.next;
		}

		current.next = current.next.next;

	}

	public void deleteByMiddle() {
		int length = getLength();
		int mid = 0;
		if (length % 2 != 0) {
			mid = length + 1 / 2;
		}
		mid = length / 2;
		ListNode current = head;
		for (int i = 0; i < mid - 1; i++) {
			current = current.next;
		}

		current.next = current.next.next;

	}
	
public ListNode removeNthFromEnd(ListNode head, int n) {
        return null;
    }

	public static void main(String[] args) {
		SingleLinkedListDemo list = new SingleLinkedListDemo();
		list.insertFirst(10);
		list.insertFirst(5);
		list.insertEnd(15);

		list.insertMiddle(3, 20);
		list.insertEnd(25);
		// list.deleteByVal(5);
	//	list.deleteByIndex(0);
		list.deleteByMiddle();
		list.traverse();

	}

}
