import java.util.List;
import java.util.Queue;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.Stack;

public class Graph<T> {

	private static class Node<T> {
		T id;
		Node parent;
		List<Node> adj;
		boolean visited;

		public Node(T id) {
			this.id = id;
			adj = new LinkedList<>();
		}
	}

	private HashMap<T, Node> graph;

	public Graph() {
		graph = new HashMap<>();
	}

	public void addEdge(T v1, T v2) {
		if(graph.get(v1) == null) 
			graph.put( v1, new Node(v1) );
		if(graph.get(v2) == null) 
			graph.put( v2, new Node(v2) );

		graph.get(v1).adj.add( graph.get(v2) );
	}

	public boolean DFS(T v1, T v2) {
		if(v1 == v2)
			return true;

		graph.get(v1).visited = true;

		for(Node n : (List<Node>)graph.get(v1).adj) 
			if( !n.visited ) 
				return DFS((T)n.id, v2);

		return false;
	}

	public boolean BFS(T v1, T v2) {

		Queue<Node> q = new LinkedList<Node>();

		Node start = graph.get(v1);
		Node end   = graph.get(v2);

		q.add( start );
		start.visited = true;

		while( !q.isEmpty() ) {
			Node curr = q.poll();
			for(Node n : (List<Node>)curr.adj) { 
				if( !n.visited ) {
					if(n == end) 
						return true;
					else {
						q.add(n);
						n.visited = true;
						n.parent = curr;
					}
				}
			}
		}

		return false;
	}

	public List<T> getMinPath(T start, T end) {

		Queue<Node> q = new LinkedList<Node>();

		Node startNode = graph.get(start);
		Node endNode   = graph.get(end);

		q.add( startNode );
		startNode.visited = true;

		while( !q.isEmpty() ) {
			Node curr = q.poll();
			for(Node n : (List<Node>)curr.adj) { 
				if( !n.visited ) {
					if(n == endNode) {
						endNode.parent = curr;
						List<T> startToEndPath = new LinkedList<>();
						Node reverse = reverseList(endNode);
						while(reverse != null) {
							startToEndPath.add((T)reverse.id);
							reverse = reverse.parent;
						}
						return startToEndPath;
					}
					else {
						q.add(n);
						n.visited = true;
						n.parent = curr;
					}
				}
			}
		}

		return null;
	}

	private static Node reverseList(Node head) {
        
        Node curr = head;
        Node prev = null;
        Node next = null;
        
        while(curr != null) {
            next = curr.parent;
            curr.parent = prev;
            prev = curr;
            curr = next;
        }
        
        return prev;
    }

	public void clearVisited() {
		for(Node n : graph.values()) n.visited = false;
	}

	public static void main(String[] args) {

		Graph<Integer> g = new Graph<>();

		g.addEdge(1, 5);
		g.addEdge(1, 3);
		g.addEdge(1, 9);
		g.addEdge(5, 9);
		g.addEdge(9, 5);
		g.addEdge(9, 15);
		g.addEdge(15, 20);

		System.out.print("Shortest path from " + 5 + " to " + 20 + " : ");
		System.out.println(  g.getMinPath(5, 20)  );

		Graph<String> g1 = new Graph<>();

		g1.addEdge("one", "five");
		g1.addEdge("one", "three");
		g1.addEdge("one", "nine");
		g1.addEdge("five", "nine");
		g1.addEdge("nine", "five");
		g1.addEdge("nine", "fifteen");
		g1.addEdge("fifteen", "twenty");

		System.out.print("Shortest path from " + "five" + " to " + "twenty" + " : ");
		System.out.println(  g1.getMinPath("five", "twenty")  );
	}

}