package leetcodehard;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SubArray {

	private static final long MOD = 1_000_000_007L;

	public static int sumCounts(int[] nums) {
		long sum = 0;
		for (int i = 0; i < nums.length; i++) {
			Set<Integer> set = new HashSet<Integer>();

			for (int j = i; j < nums.length; j++) {
				set.add(nums[j]);
				int size = set.size();
				sum += size * size % MOD;

			}
		}
		return (int) sum;

	}

	public int sumCountsO(int[] nums) {
		int n = nums.length;
		SegmentTree tree = new SegmentTree(n);
		Map<Integer, Integer> lastSeen = new HashMap<>();

		long answer = 0;

		for (int i = 0; i < n; i++) {
			int previous = lastSeen.getOrDefault(nums[i], -1);

			// Increase distinct counts for valid starting indices.
			tree.add(previous + 1, i, 1);

			// All subarrays ending at i contribute to the answer.
			answer = (answer + tree.getSquareSum()) % MOD;

			lastSeen.put(nums[i], i);
		}

		return (int) answer;
	}

	private static class SegmentTree {
		private final int n;
		private final long[] sum;
		private final long[] squareSum;
		private final long[] lazy;

		SegmentTree(int n) {
			this.n = n;
			sum = new long[4 * n];
			squareSum = new long[4 * n];
			lazy = new long[4 * n];
		}

		long getSquareSum() {
			return squareSum[1];
		}

		void add(int left, int right, long delta) {
			update(1, 0, n - 1, left, right, delta);
		}

		private void apply(int node, int left, int right, long delta) {
			long length = right - left + 1L;

			squareSum[node] = (squareSum[node] + 2L * delta * sum[node] + delta * delta * length) % MOD;

			sum[node] = (sum[node] + delta * length) % MOD;
			lazy[node] += delta;
		}

		private void push(int node, int left, int right) {
			if (lazy[node] == 0 || left == right) {
				return;
			}

			int mid = left + (right - left) / 2;

			apply(node * 2, left, mid, lazy[node]);
			apply(node * 2 + 1, mid + 1, right, lazy[node]);

			lazy[node] = 0;
		}

		private void update(int node, int left, int right, int ql, int qr, long delta) {

			if (ql <= left && right <= qr) {
				apply(node, left, right, delta);
				return;
			}

			push(node, left, right);

			int mid = left + (right - left) / 2;

			if (ql <= mid) {
				update(node * 2, left, mid, ql, qr, delta);
			}

			if (qr > mid) {
				update(node * 2 + 1, mid + 1, right, ql, qr, delta);
			}

			sum[node] = (sum[node * 2] + sum[node * 2 + 1]) % MOD;

			squareSum[node] = (squareSum[node * 2] + squareSum[node * 2 + 1]) % MOD;
		}
	}

	public static void main(String[] args) {
		int[] nums = { 1, 2, 1 };
		System.out.println(sumCounts(nums));

	}

}
