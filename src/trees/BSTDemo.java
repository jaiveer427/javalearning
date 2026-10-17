package trees;

public class BSTDemo {

	public static TreeNode insertIntoBST(TreeNode root, int val) {
		if (root == null) {
			return new TreeNode(val);
		}
		if (val < root.value) {
			root.left = insertIntoBST(root.left, val);
		} else if (root.value < val) {
			root.right = insertIntoBST(root.right, val);
		}
		return root;
	}

	public static void preOrder(TreeNode parent) {
		if (parent == null) {
			return;
		}
		System.out.print(parent.value + " ");
		preOrder(parent.left);
		preOrder(parent.right);
	}

	public static TreeNode searchBST(TreeNode root, int val) {
		if (root == null || root.value == val) {
			return root;
		}
		if (val < root.value) {
			return searchBST(root.left, val);
		}

		return searchBST(root.right, val);

	}

	public static void main(String[] args) {
		TreeNode root = new TreeNode(40);
		root.left = new TreeNode(20);
		root.right = new TreeNode(60);

		root.left.left = new TreeNode(10);
		root.left.right = new TreeNode(30);

		root.right.left = new TreeNode(50);
		root.right.right = new TreeNode(70);

		// preOrder(searchBST(root, 60));
		preOrder(root);
		System.out.println();
		root = insertIntoBST(root, 45);
		preOrder(root);

	}

}
