import java.util.*;

public class BinarySearchTree<T extends Comparable<T>> {

	private class TreeNode<T> {
		T data; 
		TreeNode left;
		TreeNode right;

		public TreeNode(T data) {
			this.data = data;
		}
	}

	 private int compare(T x, T y)
   {
      return x.compareTo(y);
   }


	public TreeNode remove(TreeNode root, T data) {
		if(root == null)
			return root; // node not found, return root unchanged
		else if (data.compareTo((T)root.data) < 0)
			root.left = remove(root.left, data);
		else if (data.compareTo((T)root.data) > 0)
			root.right = remove(root.right, data);
		else {	// found node to remove

			if(root.left == null)
				return root.right;
			if(root.right == null)
				return root.left;

			T min = getMin(root.right);
			root.data = min;
			root.right = remove(root.right, min);
		}

		return root;	// return the modified root
	}

	public T getMin(TreeNode root) {
		TreeNode curr = root;
		while(curr.left != null)
			curr = curr.left;
		return (T)curr.data;
	}

	public TreeNode insert(TreeNode node, T data) {
		if(node == null)
			node = new TreeNode(data);
		else if (data.compareTo((T)node.data) > 0)
			node.right = insert(node.right, data);
		else
			node.left = insert(node.left, data);

		return node;
	}

	public int treeSize(TreeNode root) {
		if(root == null)
			return 0;
		else
			return 1 + treeSize(root.left) + treeSize(root.right);
	}


	public void inOrderTraversal(TreeNode root) {
		if(root == null)
			return;
		inOrderTraversal(root.left);
		System.out.print(" " + root.data);
		inOrderTraversal(root.right);
	}

	public void preOrder(TreeNode root) {
		if (root == null) 
			return;
		System.out.print(" " + root.data);
		preOrder(root.left);
		preOrder(root.right);
	}

	public void postOrder(TreeNode root) {
		if (root == null) 
			return;
		postOrder(root.left);
		postOrder(root.right);
		System.out.print(" " + root.data);
	}

	public void levelOrder(TreeNode root) {
		if (root == null)
			return;
		Queue<TreeNode> q = new LinkedList<>(); 
		q.add(root);
		TreeNode curr;
		while(!q.isEmpty()) {
			curr = q.poll();
			System.out.print(" " + curr.data);
			if(curr.left != null)
				q.add(curr.left);
			if(curr.right != null)
				q.add(curr.right);
		}
	}

	public TreeNode delete(TreeNode root, T data) {
		if(root == null)
			return root;
		else if (data.compareTo((T)root.data) < 0)
			root.left = delete(root.left, data);
		else if (data.compareTo((T)root.data) > 0)
			root.right = delete(root.right, data);
		else
		{
			if(root.left == null)
				return root.right;
			if(root.right == null)
				return root.left;

			T min = getMin(root.right);
			root.data = min;
			root.right = delete(root.right, min);
		}
		return root;
	}	

	public static void main(String[] args) {



	}
}
















