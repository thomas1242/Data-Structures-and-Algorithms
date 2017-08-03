import java.util.*;

public class BinarySearchTree<T extends Comparable<T>> {

	private static class TreeNode<T> {
		T data; 
		TreeNode left;
		TreeNode right;
		public TreeNode(T data) {
			this.data = data;
		}
	}

	private TreeNode root;
	public BinarySearchTree() {}

	public void insert(T data) {
		root = insert(root, data);
	}

	public void remove(T data) {
		root = remove(root, data);
	}

	private TreeNode insert(TreeNode node, T data) {
		if(node == null)
			node = new TreeNode(data);
		else if (data.compareTo((T)node.data) > 0)
			node.right = insert(node.right, data);
		else
			node.left = insert(node.left, data);

		return node;
	}

	private TreeNode remove(TreeNode node, T data) {
		if(node == null)
			return node; 
		else if (data.compareTo((T)node.data) < 0)
			node.left = remove(node.left, data);
		else if (data.compareTo((T)node.data) > 0)
			node.right = remove(node.right, data);
		else {	

			if(node.left == null)
				return node.right;
			if(node.right == null)
				return node.left;

			T min = getMin(node.right);
			node.data = min;
			node.right = remove(node.right, min);
		}

		return node;	// return updated node
	}

	public T getMin(TreeNode node) {
		TreeNode curr = node;
		while(curr.left != null)
			curr = curr.left;
		return (T)curr.data;
	}

	public int size(TreeNode node) {
		if(node == null)
			return 0;
		else
			return 1 + size(node.left) + size(node.right);
	}

	public void inOrder() {		inOrder(root);		}
	public void preOrder() {	preOrder(root);		}
	public void postOrder() {	postOrder(root);	}
	public void levelOrder() {	levelOrder(root);	}

	private void inOrder(TreeNode node) {
		if(node == null)
			return;
		inOrder(node.left);
		System.out.print(node.data + " ");
		inOrder(node.right);
	}

	private void preOrder(TreeNode node) {
		if (node == null) 
			return;
		System.out.print(node.data + " ");
		preOrder(node.left);
		preOrder(node.right);
	}

	private void postOrder(TreeNode node) {
		if (node == null) 
			return;
		postOrder(node.left);
		postOrder(node.right);
		System.out.print(node.data + " ");
	}

	private void levelOrder(TreeNode node) {
		if (node == null)
			return;
		Queue<TreeNode> q = new LinkedList<>(); 
		q.add(node);
		TreeNode curr;
		while(!q.isEmpty()) {
			curr = q.poll();
			System.out.print(node.data + " ");
			if(curr.left != null)
				q.add(curr.left);
			if(curr.right != null)
				q.add(curr.right);
		}
	}

	public static void main(String[] args) {

		BinarySearchTree<String> strs = new BinarySearchTree<>();
		BinarySearchTree<Integer> ints = new BinarySearchTree<>();

		Random rand = new Random();
		for(int i = 0; i < 5; i++)
			ints.insert( rand.nextInt(100) );

		strs.insert("hello");
		strs.insert("world");
		strs.insert("one");
		strs.insert("and");;
		strs.insert("zero");

		ints.inOrder();
		System.out.println();
		strs.inOrder();
		System.out.println();

	}
}
















