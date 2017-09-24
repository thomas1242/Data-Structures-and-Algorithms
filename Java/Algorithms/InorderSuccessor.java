import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Node {
	int val;
	Node left, right;
	public Node(int val) {
		this.val = val;
	}
}

class InorderSuccessor {

	static Node getInorderSuccessor(Node root, Node node) {
		if(root == null || node == null) return null;

		if(node.right != null) 
			return getMin(node.right);

		Node successor = null;
		while(root != null) {				// inorder successor must be greater than its predecessor
			if(node.val < root.val) {
				successor = root;
				root = root.left;
			}
			else if(node.val > root.val) 
				root = root.right;
			else
				break;
		}	
		return successor;	
	}

	static Node getInorderPredecessor(Node root, Node node) {
		if(root == null || node == null) return null;

		if(node.left != null)
			return getMax(node.left);

		Node pre = null;
		while(root != null) {				// inorder predecessor must be less than its successor
			if(node.val < root.val) 
				root = root.left;
			else if(node.val > root.val) {
				pre = root;
				root = root.right;
			}
			else
				break;
		}

		return pre;	
	}

	static Node getMin(Node root) {
		while(root.left != null) 
			root = root.left;
		return root;
	}

	static Node getMax(Node root) {
		while(root.right != null) 
			root = root.right;
		return root;
	}
	
	static void inorder(Node root) {
		if(root == null) return;
		inorder(root.left);
		System.out.print(root.val + " ");
		inorder(root.right);
	}

	public static void main(String[] args) {
		int[] arr = new int[]{3, 5, -1, 4, 7, -5, 10, 2, 3, -7, -5, 11, 3, 2};
		System.out.println(maximumContiguousSubarray(arr));

		//	   7  
		//      3     10
		//    1   5      15
                //          6

		Node root = new Node(7);
		root.left = new Node(3);
		root.left.left = new Node(1);
		root.left.right = new Node(2);
		root.left.right.right = new Node(6);
		root.right = new Node(10);
		root.right.right = new Node(15);
		
		inorder(root);
		System.out.println("\nSuccessor of 6 is " + getInorderSuccessor( root, root.left.right.right ).val);
		System.out.println("Successor of 3 is " + getInorderSuccessor( root, root.left ).val);
		System.out.println("Successor of 10 is " + getInorderSuccessor( root, root.right ).val);

		System.out.println("\nPrecessor of 6 is " + getInorderPredecessor( root, root.left.right.right ).val);
		System.out.println("Precessor of 3 is " + getInorderPredecessor( root, root.left ).val);
		System.out.println("Precessor of 10 is " + getInorderPredecessor( root, root.right ).val);
	}
}
