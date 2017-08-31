import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


class Node {
	int val;
	Node left;
	Node right;
	public Node(int val) {
		this.val = val;
	}
}

class LowestCommonAncestor {

	static List<Node> getPath(Node root, int val, List<Node> path) {
		if(root == null) return null;

		path.add(root);

		if(val < root.val)
			getPath(root.left, val, path);
		else if(val > root.val)
			getPath(root.right, val, path);

		return path;
	}

	static Node getAncestor(int a, int b, Node root) {
		if(root == null) return null;

		List<Node> aPath = getPath(root, a, new LinkedList<>());
		List<Node> bPath = getPath(root, b, new LinkedList<>());
	
		Node ancestor = null;

		int i = 0;
		while( i < aPath.size() && i < bPath.size() && aPath.get(i) == bPath.get(i) ) 
			ancestor = aPath.get(i++);
		
		return ancestor;
	}


	public static void main(String[] args) {

		//	   7  
		//      3     10
		//    1   5      15
                //          6

		Node root = new Node(7);
		root.left = new Node(3);
		root.left.left = new Node(1);
		root.left.right = new Node(5);
		root.left.right.right = new Node(6);
		root.right = new Node(10);
		root.right.right = new Node(15);
		
		System.out.print("Lowest common ancestor: ");
		System.out.print( getAncestor(1, 5, root).val );

	}


}









