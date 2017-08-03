public class Queue<T> {

	private static class Node<T> {
		T data;
		Node next;
		public Node(T data) {
			this.data = data;
			next = null;
		}
	}

	private Node front; 
	private Node back;
	private int size;

	public Queue() {
		size = 0;
	}

	public void add(T data) {
		if(front == null) {
			front = new Node(data);
			back = front;
		}
		else {
			back.next = new Node(data);
			back = back.next;
		}
	}

	public T remove() {
		if(front == null)
			return null;
		else {
			T data = (T)front.data;
			front = front.next;
			if(front == null)
				back = null;
			return data;
		}
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public static void main(String[] args) {

		Queue<Integer> q0 = new Queue<>();
		Queue<String>  q1 = new Queue<>();
		Queue<Character>  q2 = new Queue<>();

	}
}