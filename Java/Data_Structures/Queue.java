public class Queue<T> {

	private static class Node<T> {
		T data;
		Node<T> next;
		
		public Node(T data) {
			this.data = data;
			next = null;
		}
	}

	private Node<T> head; 
	private Node<T> tail;
	private int size;

	public Queue() {
		size = 0;
	}

	public void add(T data) {
		Node<T> newNode = new Node<T>(data);

		if(head == null) 
			head = newNode;
		else 
			tail.next = newNode;

		tail = newNode;
	}

	public T peek() {
		if(head == null)
			return null;
		else 
			return (T) head.data;
	}

	public T remove() {
		if(head == null)
			return null;
		else {
			T data = (T) head.data;
			head = head.next;
			if(head == null)
				tail = null;
			return data;
		}
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public static void main(String[] args) {
		Queue<Integer> q0 = new Queue<>();
		Queue<String>  q1 = new Queue<>();
	}
}