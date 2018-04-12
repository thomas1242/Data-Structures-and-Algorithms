public class DoublyLinkedList<T> {

	private static class Node<T> {
		T data;
		Node<T> next;
		Node<T> prev;
		public Node(T data) {
			this.data = data;
		}
	}	

	private Node<T> head;

	public DoublyLinkedList() {
		head = null;
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

	public void push(T data) {
		Node<T> newNode = new Node<T>(data);

		if(head != null) {
			newNode.next = head;
			head.prev = newNode;
		}

		head = newNode;
	}

	public T pop() {
		if(head == null)
			return null;

		T data = head.data;

		head = head.next;
		if(head != null)
			head.prev = null;

		return data;
	}

	public void append(T data) {
		Node<T> newNode = new Node<T>(data);

		if(head == null)
			head = newNode;
		else {
			Node<T> curr = head;
			while(curr.next != null)
				curr = curr.next;
			curr.next = newNode;
			newNode.prev = curr;
		}
	}

	public void insert(T data, int index) {
		if(index < 0 || index > size())
			return;

		Node<T> newNode = new Node<T>(data);

		if(index == 0) {
			newNode.next = head;
			if(head != null)
				head.prev = newNode;
			head = newNode;
		}
		else {
			Node<T> curr = head;
			for(int i = 0; i < index - 1; i++ )
				curr = curr.next;

			newNode.next = curr.next;
			newNode.prev = curr;
			curr.next = newNode;
			if(newNode.next != null)
				newNode.next.prev = newNode;
		}
	}

	public void remove(T data) {
		if(head == null)
			return;			

		if(head.data == data) {
			head = head.next;
			if(head != null) 
				head.prev = null;
		}
		else { 
			Node<T> curr = head;
			while(curr != null && curr.data != data)
				curr = curr.next;

			if(curr == null)	// not found
				return;

			curr.prev.next = curr.next;
			if(curr.next != null)
				curr.next.prev = curr.prev;

			curr.prev = null;
			curr.next = null;
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		Node<T> curr = head;
		while(curr != null) {
			sb.append("[ " + curr.data + " ] ");
			curr = curr.next;
		}

		return sb.toString();
	}

	public static void main(String[] args) {
		
		DoublyLinkedList<String> strings = new DoublyLinkedList<>();
		DoublyLinkedList<Integer> ints = new DoublyLinkedList<>();

		strings.push("one");
		strings.append("hello");
		strings.insert("world", 2);
		strings.remove("one");

		ints.push(1);
		ints.push(2);
		ints.append(3);
		ints.pop();

		System.out.println(ints);
		System.out.println(strings);
	}
}
