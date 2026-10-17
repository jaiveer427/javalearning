package trie;

import trees.TreeNode;

class TrieNode {

	TrieNode[] children = new TrieNode[26];

	boolean isEndOfWord;

}

class Trie {

	private final TrieNode root;

	public Trie() {
		root = new TrieNode();

	}

	public void insert(String word) {
		TrieNode current = root;

		for (char ch : word.toCharArray()) {
			int index = ch - 'a';

			if (current.children[index] == null) {
				current.children[index] = new TrieNode();
			}
			current = current.children[index];

		}
		current.isEndOfWord = true;

	}

	public boolean search(String word) {
		TrieNode current = root;

		for (char ch : word.toCharArray()) {

			int index = ch - 'a';

			if (current.children[index] == null) {
				return false;
			}
			current = current.children[index];

		}
		return current.isEndOfWord;

	}

	public boolean startsWith(String prefix) {
		TrieNode current = root;

		for (char ch : prefix.toCharArray()) {

			int index = ch - 'a';

			if (current.children[index] == null) {
				return false;
			}
			current = current.children[index];

		}

		return true;

	}
}

/**
 * Your Trie object will be instantiated and called as such: Trie obj = new
 * Trie(); obj.insert(word); boolean param_2 = obj.search(word); boolean param_3
 * = obj.startsWith(prefix);
 */

public class TrieBasicDemo {

	public static void main(String[] args) {

		Trie trie = new Trie();

		trie.insert("apple");
		trie.insert("app");
		trie.insert("application");
		trie.insert("car");
		trie.insert("cam");

		System.out.println(trie.search("apple"));
		System.out.println(trie.search("app"));
		System.out.println(trie.search("ap"));
		System.out.println(trie.search("ca"));

	//	System.out.println(trie.startsWith("app"));
	}

}
