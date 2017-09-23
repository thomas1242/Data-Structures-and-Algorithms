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

class Levels {

	static List< List<Node> > getLevels(Node root) {
	
		List< List<Node> > levels = new LinkedList<>();
		if(root == null) return levels;

		List<Node> curr = new LinkedList<>();
		List<Node> next = new LinkedList<>();
		curr.add(root);

		while(!curr.isEmpty()) {

			for(int i = 0; i < curr.size(); i++) {
				Node node = curr.get(i);
				if(node.left != null)
					next.add(node.left);
				if(node.right != null)
					next.add(node.right);
			}
			
			levels.add( new LinkedList<>( curr ) );
			curr = new LinkedList<>( next );
			next.clear();
		}

		return levels;
	}

	static List< List<Integer> > zigzagLevelOrder(Node root) {
		List< List<Integer> > levels = new LinkedList<>();
		if(root == null) return levels;

		List<Node> curr = new LinkedList<>();
		List<Node> next = new LinkedList<>();
		curr.add(root);

		boolean reverse = false;
		while(!curr.isEmpty()) {

			List<Integer> level = new LinkedList<>();
			for(int i = 0; i < curr.size(); i++) {
				Node node = curr.get(i);

				if(reverse)	level.add(0, node.val);  
				else		level.add(node.val);		

				if(node.left != null)  next.add(node.left);
				if(node.right != null) next.add(node.right);
			}
			
			levels.add(level);
			curr = new LinkedList<>( next );
			next.clear();
			reverse ^= true;
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

		//	   	   7  
		//      3     10
		//    1   5       15
        //          6  13    17 

		Node root = new Node(7);
		root.left = new Node(3);
		root.left.left = new Node(1);
		root.left.right = new Node(5);
		root.left.right.right = new Node(6);
		root.right = new Node(10);
		root.right.right = new Node(15);
		root.right.right.left = new Node(13);
		root.right.right.right = new Node(17);

		System.out.print("\nLevel order: ");
		levelOrder(root);

		System.out.print("\nLevel order: ");
		List< List<Node> > levels = getLevels( root );
		int i = 0;
		for(List<Node> list : levels) {
			System.out.print("\nlevel " + i++ + ": ");
			for(Node n : list) 
				System.out.print(n.val + " ");
		}
		System.out.println();

		System.out.print("\nZigzag level order: ");
		List< List<Integer> > zigzag = zigzagLevelOrder( root );
		i = 0;
		for(List<Integer> list : zigzag) {
			System.out.print("\nlevel " + i++ + ": ");
			for(Integer n : list) 
				System.out.print(n + " ");
		}
		System.out.println();
	}
}