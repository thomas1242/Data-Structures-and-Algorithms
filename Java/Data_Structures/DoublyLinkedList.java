public class DoublyLinkedList<T> {

	private static class Node<T> {
		T data;
		Node<T> next, prev;
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
		return node == null ? 0 : 1 + size(node.next);
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
			throw new java.util.NoSuchElementException("oops");

		T data = head.data;

		head = head.next;
		if(head != null) head.prev = null;

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

	public void insert(int index, T data) {
		if(index < 0 || index > size())
			throw new java.lang.IndexOutOfBoundsException("oops");

		Node<T> newNode = new Node<T>(data);

		if(index == 0) {
			newNode.next = head;
			if(head != null) head.prev = newNode;
			head = newNode;
		}
		else {
			Node<T> curr = head;
			for(int i = 0; i < index - 1; i++ )
				curr = curr.next;

			newNode.next = curr.next;
			newNode.prev = curr;
			curr.next = newNode;
			if(newNode.next != null) newNode.next.prev = newNode;
		}
	}

	public boolean remove(T data) {
		if(head == null)
			return false;			

		if(head.data == data) {
			head = head.next;
			if(head != null) head.prev = null;
		}
		else { 
			Node<T> curr = head;
			while(curr != null && curr.data != data)
				curr = curr.next;

			if(curr == null)	// not found
				return false;

			curr.prev.next = curr.next;
			if(curr.next != null) curr.next.prev = curr.prev;
		}
		return true;
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
	}
}
