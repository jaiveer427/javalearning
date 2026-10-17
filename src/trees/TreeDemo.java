package trees;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

public class TreeDemo {

	public int goodNodes(TreeNode root) {
		if (root == null) {
			return 0;
		}
		return dfs(root, root.value);
	}

	private int dfs(TreeNode root, int max) {
		if (root == null) {
			return 0;
		}
		int count = 0;
		if (root.value >= max) {
			count++;
		}
		max = Math.max(max, root.value);
		count += dfs(root.left, max);
		count += dfs(root.right, max);

		return count;

	}

	public static int maxDepth(TreeNode root) {
		if (root == null) {
			return 0;
		}
		int leftDepth = maxDepth(root.left);
		int rightDepth = maxDepth(root.right);
		return 1 + Math.max(leftDepth, rightDepth);

	}

	public boolean leafSimilar(TreeNode root1, TreeNode root2) {

		List<Integer> leaves1 = new ArrayList<Integer>();
		List<Integer> leaves2 = new ArrayList<Integer>();

		collectLeaves(root1, leaves1);
		collectLeaves(root2, leaves2);
		return leaves1.equals(leaves2);

	}

	public static void collectLeaves(TreeNode root, List<Integer> leaves) {
		if (root == null) {
			return;
		}
		if (root.left == null && root.right == null) {

			leaves.add(root.value);
		}
		collectLeaves(root.left, leaves);
		collectLeaves(root.right, leaves);

	}

	public static void preOrder(TreeNode parent) {
		if (parent == null) {
			return;
		}
		System.out.print(parent.value + " ");
		preOrder(parent.left);
		preOrder(parent.right);
	}

	public static void inOrder(TreeNode parent) {
		if (parent == null) {
			return;
		}
		inOrder(parent.left);
		System.out.print(parent.value + " ");

		inOrder(parent.right);
	}

	public static void postOrder(TreeNode parent) {
		if (parent == null) {
			return;
		}
		postOrder(parent.left);
		postOrder(parent.right);
		System.out.print(parent.value + " ");
	}

	// DFS
	public static void bfs(TreeNode root) {
		if (root == null) {
			return;
		}
		Queue<TreeNode> queue = new ArrayDeque<TreeNode>();
		List<Integer> visited = new ArrayList<Integer>();
		queue.offer(root);
		while (!queue.isEmpty()) {
			TreeNode node = queue.poll();
			visited.add(node.value);

			if (node.left != null) {
				queue.offer(node.left);
			}
			if (node.right != null) {
				queue.offer(node.right);
			}

		}
		System.out.println("bfs: "+visited);

	}
	// Level-order Queue BFS

	public static List<List<Integer>> levelOrderBFS(TreeNode root) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		if (root == null) {
			return result;
		}

		Queue<TreeNode> queue = new ArrayDeque<TreeNode>();
		int level = 0;
		queue.offer(root);

		while (!queue.isEmpty()) {

			int size = queue.size();
			List<Integer> list = new ArrayList<Integer>();
			System.out.println("Level: " + level);
			System.out.println("Size: " + size);

			for (int i = 0; i < size; i++) {
				TreeNode node = queue.poll();
				list.add(node.value);

				if (node.left != null) {
					queue.offer(node.left);
				}
				if (node.right != null) {
					queue.offer(node.right);
				}

			}

			level++;
			result.add(list);
		}
		return result;

	}

	// Sum of each level
	public static List<Integer> levelSum(TreeNode root) {
		List<Integer> result = new ArrayList<Integer>();
		if (root == null) {
			return result;
		}
		Queue<TreeNode> queue = new ArrayDeque<TreeNode>();
		queue.offer(root);

		while (!queue.isEmpty()) {

			int size = queue.size();
			int sum = 0;
			for (int i = 0; i < size; i++) {
				TreeNode node = queue.poll();
				sum += node.value;

				if (node.left != null) {
					queue.offer(node.left);
				}
				if (node.right != null) {
					queue.offer(node.right);
				}
			}
			result.add(sum);

		}
		return result;
	}

	public static List<Integer> maxValueByLevel(TreeNode root) {
		List<Integer> result = new ArrayList<Integer>();
		if (root == null) {
			return result;
		}
		Queue<TreeNode> queue = new ArrayDeque<TreeNode>();
		queue.offer(root);
		while (!queue.isEmpty()) {

			int size = queue.size();
			int max = -1;

			for (int i = 0; i < size; i++) {
				TreeNode node = queue.poll();
				if (node.value > max) {
					max = node.value;
				}

				if (node.left != null) {
					queue.offer(node.left);
				}
				if (node.right != null) {
					queue.offer(node.right);
				}
			}
			result.add(max);

		}

		return result;

	}

	// Count nodes at each level
	public static List<Integer> countNodeBylevel(TreeNode root) {
		List<Integer> result = new ArrayList<Integer>();
		if (root == null) {
			return result;
		}

		Queue<TreeNode> queue = new ArrayDeque<TreeNode>();

		queue.offer(root);

		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				TreeNode node = queue.poll();

				if (node.left != null) {
					queue.offer(node.left);
				}
				if (node.right != null) {
					queue.offer(node.right);
				}
			}
			result.add(size);

		}

		return result;

	}

	// Average of each level

	public static List<Double> averageBylevel(TreeNode root) {
		List<Double> result = new ArrayList<Double>();
		if (root == null) {
			return result;
		}
		Queue<TreeNode> queue = new ArrayDeque<TreeNode>();

		queue.offer(root);
		while (!queue.isEmpty()) {
			int size = queue.size();
			int sum = 0;
			for (int i = 0; i < size; i++) {
				TreeNode node = queue.poll();
				sum += node.value;

				if (node.left != null) {
					queue.offer(node.left);
				}
				if (node.right != null) {
					queue.offer(node.right);
				}
			}
			Double avg = (double) sum / size;
			result.add(avg);

		}

		return result;

	}
	// Print only the last node of each level → Right Side View

	public static List<Integer> lastNodeRight(TreeNode root) {
		List<Integer> result = new ArrayList<Integer>();

		if (root == null) {
			return result;
		}
		Queue<TreeNode> queue = new ArrayDeque<TreeNode>();

		queue.offer(root);
		while (!queue.isEmpty()) {
			int size = queue.size();

			for (int i = 0; i < size; i++) {
				TreeNode node = queue.poll();
				if (i == size - 1) {
					result.add(node.value);
				}

				if (node.left != null) {
					queue.offer(node.left);
				}
				if (node.right != null) {
					queue.offer(node.right);
				}
			}
		}
		return result;

	}

	// Left Side View → [5, 4, 11, 7]
	public static List<Integer> leftSideView(TreeNode root) {
		List<Integer> result = new ArrayList<Integer>();

		if (root == null) {
			return result;
		}
		Queue<TreeNode> queue = new ArrayDeque<TreeNode>();

		queue.offer(root);
		while (!queue.isEmpty()) {
			int size = queue.size();

			for (int i = 0; i < size; i++) {
				TreeNode node = queue.poll();

				// we want the first element f every level
				if (i == 0) {
					result.add(node.value);
				}

				if (node.left != null) {
					queue.offer(node.left);
				}
				if (node.right != null) {
					queue.offer(node.right);
				}
			}
		}
		return result;

	}

	// Find the first node with value 13 and return its level

	public static int findLevel(TreeNode root, int value) {
		if (root == null) {
			return -1;
		}
		Queue<TreeNode> queue = new ArrayDeque<TreeNode>();

		queue.offer(root);
		int level = 0;
		while (!queue.isEmpty()) {
			int size = queue.size();

			for (int i = 0; i < size; i++) {
				TreeNode node = queue.poll();

				// we want the first element f every level
				if (node.value == value) {
					return level;
				}

				if (node.left != null) {
					queue.offer(node.left);
				}
				if (node.right != null) {
					queue.offer(node.right);
				}

			}
			level++;
		}
		return -1;
	}

	public static int minDepth(TreeNode root) {
		if (root == null) {
			return -1;
		}
		Queue<TreeNode> queue = new ArrayDeque<TreeNode>();

		queue.offer(root);
		int depth = 1;
		while (!queue.isEmpty()) {
			int size = queue.size();

			for (int i = 0; i < size; i++) {
				TreeNode node = queue.poll();

				// we want the first element f every level
				if (node.left == null && node.right == null) {

					return depth;

				}

				if (node.left != null) {
					queue.offer(node.left);
				}
				if (node.right != null) {
					queue.offer(node.right);
				}

			}
			depth++;
		}
		return -1;
	}

	public List<Integer> rightSideView(TreeNode root) {
		List<Integer> result = new ArrayList<>();

		dfs(root, 0, result);
		return result;
	}

	private void dfs(TreeNode node, int depth, List<Integer> result) {
		if (node == null) {
			return;
		}
		if (depth == result.size()) {
			result.add(node.value);
		}
		dfs(node.right, depth + 1, result);
		dfs(node.left, depth + 1, result);
	}

	public static int pathSum(TreeNode root, int targetSum) {

		return 0;
	}

	public static int maxLevelSum(TreeNode root) {
		if (root == null) {
			return -1;
		}
		int maxSum = Integer.MIN_VALUE;
		int level = 1;
		int result = 0;
		Queue<TreeNode> queue = new ArrayDeque<TreeNode>();
		queue.offer(root);
		while (!queue.isEmpty()) {
			int size = queue.size();

			int sum = 0;
			for (int i = 0; i < size; i++) {
				TreeNode node = queue.poll();
				sum += node.value;

				if (node.left != null) {
					queue.offer(node.left);
				}
				if (node.right != null) {
					queue.offer(node.right);
				}
			}
			if (sum > maxSum) {
				maxSum = sum;
				result = level;
			}
			level++;

		}

		return result;
	}

	public static TreeNode searchBST(TreeNode root, int val) {
		if (root == null || root.value == val) {
	        return root;
	    }
		if(val<root.value) {
			return searchBST(root.left, val);
		}
	
		
			return searchBST(root.right, val);
		
	}
	

	public static void main(String[] args) {

		TreeNode root = new TreeNode(5);

		root.left = new TreeNode(4);
		root.right = new TreeNode(8);

		root.left.left = new TreeNode(11);

		root.right.left = new TreeNode(13);
		root.right.right = new TreeNode(4);

		root.left.left.left = new TreeNode(7);
		root.left.left.right = new TreeNode(2);

		root.right.right.left = new TreeNode(5);
		root.right.right.right = new TreeNode(1);
		preOrder(root);
		System.out.println();
		inOrder(root);
		System.out.println();
		postOrder(root);

		System.out.println();
		// System.out.println("max depth of tree is : " + maxDepth(root));
		// bfs(root);
		System.out.println(levelOrderBFS(root));
		System.out.println(levelSum(root));
		// System.out.println(maxValueByLevel(root));
		// System.out.println(countNodeBylevel(root));
		// System.out.println(averageBylevel(root));

		// System.out.println(leftSideView(root));

		// System.out.println(minDepth(root));
	//	System.out.println(maxLevelSum(root));
		inOrder(searchBST(root, 4));
		
	}

}
