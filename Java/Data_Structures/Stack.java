public class Stack<T> {

	private static class Node<T> {
		T data;
		Node<T> next;
		public Node(T data) {
			this.data = data;
		}
	}

	private Node<T> top;

	public Stack() {}

	public void push(T data) {
		Node<T> newNode = new Node<T>(data);
		newNode.next = top;
		top = newNode;
	}

	public void pop() {
		if(top == null)
			return;
		else 
			top = top.next;
	}

	public T peek() {
		if(top == null)
			return null;
		else
			return (T) top.data;
	}

	public boolean isEmpty() {
		return top == null;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		Node<T> curr = top;
		sb.append("|   |\n");
		while(curr != null) {
			sb.append("| " + curr.data + " |\n");
			curr = curr.next;
		}
		sb.append("|___|\n");

		return sb.toString();
	}

	public int size() {
		return size(top);
	}

	private int size(Node<T> node) {
		if(node == null)
			return 0;
		else
			return 1 + size(node.next);
	}

	public static void main(String[] args) {
		Stack<Integer> s1 = new Stack<>();		
		s1.push(1);
		s1.push(2);
		s1.push(3);
		System.out.println(s1);
		while( !s1.isEmpty() )  
			s1.pop();
	}
}