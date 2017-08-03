import java.util.*;

public class BinarySearchTree<T extends Comparable<T>> {

	private static class Node<T> {
		T data; 
		Node left;
		Node right;
		public Node(T data) {
			this.data = data;
		}
	}

	private Node root;
	public BinarySearchTree() {}

	public void insert(T data) {
		root = insert(root, data);
	}

	public boolean contains(T data) {
		return contains(root, data);
	}

	public void remove(T data) {
		root = remove(root, data);
	}

	private Node insert(Node node, T data) {
		if(node == null)
			node = new Node(data);
		else if (data.compareTo((T)node.data) > 0)
			node.right = insert(node.right, data);
		else
			node.left = insert(node.left, data);

		return node;
	}

	private boolean contains(Node node, T data) {
		if(node == null)
			return false;
		else if (data.compareTo((T)node.data) > 0)
			return contains(node.right, data);
		else if (data.compareTo((T)node.data) < 0)
			return contains(node.left, data);
		else
			return true;
	}

	private Node remove(Node node, T data) {
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

	public T getMin(Node node) {
		Node curr = node;
		while(curr.left != null)
			curr = curr.left;
		return (T)curr.data;
	}

	public int size(Node node) {
		if(node == null)
			return 0;
		else
			return 1 + size(node.left) + size(node.right);
	}

	public void inOrder() {		inOrder(root);		}
	public void preOrder() {	preOrder(root);		}
	public void postOrder() {	postOrder(root);	}
	public void levelOrder() {	levelOrder(root);	}

	private void inOrder(Node node) {
		if(node == null)
			return;
		inOrder(node.left);
		System.out.print(node.data + " ");
		inOrder(node.right);
	}

	private void preOrder(Node node) {
		if (node == null) 
			return;
		System.out.print(node.data + " ");
		preOrder(node.left);
		preOrder(node.right);
	}

	private void postOrder(Node node) {
		if (node == null) 
			return;
		postOrder(node.left);
		postOrder(node.right);
		System.out.print(node.data + " ");
	}

	private void levelOrder(Node node) {
		if (node == null)
			return;
		Queue<Node> q = new LinkedList<>(); 
		q.add(node);
		Node curr;
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

		ints.insert( 7 );
		ints.insert( 3 );
		ints.insert( 15 );
		ints.insert( 30 );
		ints.insert( 25 );

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