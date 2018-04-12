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

	public Queue() {}

	public void add(T data) {
		Node<T> newNode = new Node<>(data);

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
			return head.data;
	}

	public T remove() {
		if(head == null)
			return null;
		else {
			T data = head.data;
			head = head.next;
			if(head == null)
				tail = null;
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

		for (int i = 0; i < 10; i++) 
			q.add(i);

		System.out.println(q);
	}
}
