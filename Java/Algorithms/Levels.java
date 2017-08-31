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

class Levels {

	static List< List<Node> > getLevels(Node root) {
	
		List< List<Node> > levels = new LinkedList<>();
		if(root == null) return levels;

		List<Node> curr = new LinkedList<>();
		List<Node> next = new LinkedList<>();
		curr.add(root);

		while(!curr.isEmpty()) {

			for(int i = 0; i < curr.size(); i++) {
				if(curr.get(i).left != null)
					next.add(curr.get(i).left);
				if(curr.get(i).right != null)
					next.add(curr.get(i).right);
			}
			
			levels.add( new LinkedList<>( curr ) );
			curr = new LinkedList<>( next );
			next.clear();
		}

		return levels;
	}


	static void levelOrder(Node root) {
		if(root == null)
			return;

		Queue<Node> q = new LinkedList<>();
		q.add( root );

		while( !q.isEmpty() ) {

			Node curr = q.remove();
			System.out.print( curr.val + " " );

			if(curr.left != null)
				q.add(curr.left);
			if(curr.right != null)
				q.add(curr.right);
	
		}
		
		System.out.println();
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

		System.out.print("\nLevel order: ");
		levelOrder(root);

		List< List<Node> > levels = getLevels( root );
		int i = 0;
		for(List<Node> list : levels) {
			System.out.print("\nlevel " + i++ + ": ");
			for(Node n : list) {
				System.out.print(n.val + " ");
			}
		}
		System.out.println();

	}


}









