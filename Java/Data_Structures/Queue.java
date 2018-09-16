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

	public Queue() {}

	public void add(T data) {
		Node<T> newNode = new Node<>(data);

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
			return head.data;
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

	public int size() {
		return size(head);
	}

	private int size(Node<T> node) {
		if(node == null)
			return 0;
		else
			return 1 + size(node.next);
	}

	public boolean isEmpty() {
		return head == null;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		Node<T> curr = head;
		while(curr != null) {
			sb.append(curr.data + " ");
			curr = curr.next;
		}

		return sb.toString();
	}

	public static void main(String[] args) {
		Queue<Integer> q = new Queue<>(); 
	}
}
