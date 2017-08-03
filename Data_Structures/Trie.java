public class Trie {

	static class Node {
		char c;
		Node[] children;
		boolean isWord;

		public Node(char c) {
			this.c = c;
			children = new Node[26];
		}
	}

	private Node root;
	public Trie() {}

	private void printAllWords(Node root, String prefix) {
		if(root == null)
			return;

		if(root.isWord)
			System.out.println(prefix);

		for(int i = 0; i < root.children.length; i++) 
			if(root.children[i] != null) 
				printAllWords( root.children[i], prefix + root.children[i].c );
	}

	private Node insertWord(Node root, String word) {
		if(root == null)
			root = new Node(' ');

		Node curr = root;		
		for(int i = 0; i < word.length(); i++) {
			Character c = word.charAt(i);
			int index = c - 'a';

			if(c.compareTo('A') >= 0 && c.compareTo('Z') <= 0)	// case in-sensitive
				index += 'a' - 'A';

			if(curr.children[index] == null)
				curr.children[index] = new Node(c);

			curr = curr.children[index];
		}
		curr.isWord = true;
		return root;
	}

	private static boolean containsWord(String word, Node root) {
		if(root == null)
			return false;

		Node curr = root;
		for(int i = 0; i < word.length(); i++) {
			int index = word.charAt(i) - 'a';
			if(curr.children[index] == null)
				return false;
			curr = curr.children[index];
		}

		if(curr.isWord == true)
			return true;
		else
			return false;
	}

	public void printAllWords() {
		printAllWords(root, "");
	}

	public void insertWord(String word) {
		root = insertWord(root, word);
	}

	public boolean containsWord(String word) {
		return containsWord(word, root);
	}

	public static void main(String[] args) {
		Trie trie = new Trie();

		trie.insertWord("news");
		trie.insertWord("paper");
		trie.insertWord("hello");
		trie.insertWord("herron");

		trie.printAllWords();
	}
}









