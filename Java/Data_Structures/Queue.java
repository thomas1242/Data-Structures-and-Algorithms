public class Queue<T> {

	private static class Node<T> {
		T data;
		Node<T> next;
		public Node(T data) {
			this.data = data;
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

		if (head == null) 
			head = newNode;
		else 
			tail.next = newNode;

		tail = newNode;
	}

	public T peek() {
		if (head == null)
			return null;
		else 
			return (T) head.data;
	}

	// returns the head of this queue, or throws an exception if queue is empty
	public T remove() {		
		if (head == null)
			throw new java.util.NoSuchElementException("oops");
		else {
			T data = (T) head.data;
			if(head == tail)
				head = tail = null;
			else
				head = head.next;
			return data;
		}
	}

	// returns the head of this queue, or null if this queue is empty
	public T poll() {		
		if (head == null)
			return null;
		else {
			T data = (T) head.data;
			if(head == tail)
				head = tail = null;
			else
				head = head.next;
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