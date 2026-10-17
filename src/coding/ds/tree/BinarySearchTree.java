package coding.ds.tree;

public class BinarySearchTree {
	Node root;

	public BinarySearchTree() {
		this.root = null;
	}

	public Node insertNode(Node root, int data) {
		if (root == null) {
			return new Node(data);
		}

		if (data < root.val) {
			root.left = insertNode(root.left, data);
		} else if (data > root.val) {
			root.right = insertNode(root.right, data);
		}
		return root;
	}
	
	

}
