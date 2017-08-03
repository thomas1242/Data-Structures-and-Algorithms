public class Stack<T> {

	private static class Node<T> {
		T data;
		Node next;
		public Node(T data) {
			this.data = data;
		}
	}

	private Node top; 
	private int size;

	public Stack() {
		top = null;
		size = 0;
	}

	public void push(T data) {
		if(top == null) 
			top = new Node<T>(data);
		else {
			Node<T> newNode = new Node<T>(data);
			newNode.next = top;
			top = newNode;
		}
		size++;
	}

	public void pop() {
		if(top == null)
			return;
		else 
			top = top.next;
		size--;
	}

	public Object peek() {
		if(top == null)
			return null;
		else
			return top.data;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public static void main(String[] args) {
		Stack<Integer> s1 = new Stack<>();
		Stack<String>  s2 = new Stack<>();
		
		s1.push(1);
		s1.push(2);
		s1.push(3);
		while( !s1.isEmpty() ) {
			System.out.println(s1.peek());
			s1.pop();
		}
	
		s2.push("one");
		s2.push("two");
		s2.push("three");
		while( !s2.isEmpty() ) {
			System.out.println(s2.peek());
			s2.pop();
		}
	}
}