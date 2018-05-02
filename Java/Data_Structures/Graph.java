import java.util.List;
import java.util.Queue;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.Stack;

public class Graph<T> {	// simple unweighted directed graph

	private static class Node<T> {
		T id;
		Node parent;
		List<Node<T>> adjNodes;
		boolean visited;

		public Node(T id) {
			this.id = id;
			adjNodes = new LinkedList<>();
		}
	}

	private HashMap<T, Node<T>> graph;

	public Graph() {
		graph = new HashMap<>();
	}

	public void addEdge(T v1, T v2) {	// add directed edge from v1 to v2
		if(graph.get(v1) == null)  graph.put(v1, new Node<>(v1));
		if(graph.get(v2) == null)  graph.put(v2, new Node<>(v2));

		graph.get(v1).adjNodes.add( graph.get(v2) );
	}

	public void DFS(T v1) {
		DFS(graph.get(v1));
	}

	private void DFS(Node<T> node) {
		node.visited = true;

		for(Node<T> adjNode : node.adjNodes) 
			if(!adjNode.visited) 
				DFS(adjNode);
	}

	public void BFS(T v1) {
		BFS(graph.get(v1));
	}

	private void BFS(Node<T> node) {
		Queue<Node<T>> q = new LinkedList<>();
		
		Node<T> startNode = node;
		q.add(startNode);
		startNode.visited = true;

		while( !q.isEmpty() ) {
			Node<T> curr = q.poll();
			for(Node<T> adjNode : curr.adjNodes) { 
				if(!adjNode.visited) {
					q.add(adjNode);
					adjNode.visited = true;
				}
			}
		}
	}

	public void printMinPath(T start, T end) {
		Queue<Node<T>> q = new LinkedList<>();

		Node<T> startNode = graph.get(start);
		Node<T> endNode   = graph.get(end);

		q.add( startNode );
		startNode.visited = true;

		while( !q.isEmpty() ) {
			Node<T> curr = q.poll();
			for(Node<T> adjNode : curr.adjNodes) { 
				if( !adjNode.visited ) {
					if(adjNode == endNode) {
						endNode.parent = curr;
						printList( reverseList(endNode) );
					}
					else {
						q.add(adjNode);
						adjNode.visited = true;
						adjNode.parent = curr;
					}
				}
			}
		}
	}

	private Node<T> reverseList(Node<T> head) {
        Node<T> curr = head;
        Node<T> prev = null;
        Node<T> next = null;
        
        while(curr != null) {
            next = curr.parent;
            curr.parent = prev;
            prev = curr;
            curr = next;
        }
        
        return prev;
    }

	public void clearVisited() {
		for(Node<T> node : graph.values()) 
			node.visited = false;
	}

	public void printList(Node<T> node) {
		while(node != null) {
			System.out.print(node.id + " ");
			node = node.parent;
		}
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
		g.printMinPath(5, 20);

		Graph<String> g1 = new Graph<>();
		g1.addEdge("one", "five");
		g1.addEdge("one", "three");
		g1.addEdge("one", "nine");
		g1.addEdge("five", "nine");
		g1.addEdge("nine", "five");
		g1.addEdge("nine", "fifteen");
		g1.addEdge("fifteen", "twenty");
		System.out.print("\nShortest path from " + "five" + " to " + "twenty" + " : ");
		g1.printMinPath("five", "twenty");
	}
}