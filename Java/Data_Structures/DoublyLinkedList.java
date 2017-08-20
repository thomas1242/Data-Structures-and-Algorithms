public class DoublyLinkedList<T> {

	private static class Node<T> {
		T data;
		Node next;
		Node prev;
		public Node(T data) {
			this.data = data;
		}
	}	

	private Node head;
	private int size;

	public DoublyLinkedList() {
		head = null;
		size = 0;
	}

	public void push(T data) {
		Node newNode = new Node(data);
		newNode.prev = null;

		if(head != null) {
			newNode.next = head;
			head.prev = newNode;
		}

		head = newNode;
		size++;
	}

	public T pop() {
		if(head == null)
			return null;

		if(head.next != null)
			head = head.next;
		head.prev = null;

		return (T) head.data;
	}

	public void append(T data) {
		Node newNode = new Node(data);
		newNode.next = null;

		if(head == null) {
			head = newNode;
			newNode.prev = null;
		}
		else {
			Node curr = head;
			while(curr.next != null)
				curr = curr.next;
			curr.next = newNode;
			newNode.prev = curr;
		}
		size++;
	}

	public void insert(T data, int index) {
		if(index < 0 || index > size)
			return;

		Node newNode = new Node(data);

		if(head == null) {
			head = newNode;
			newNode.prev = null;
			newNode.next = null;
		}
		else if(index == 0) {
			newNode.next = head;
			head.prev = newNode;
			newNode.prev = null;
			head = newNode;
		}
		else {
			Node curr = head;
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
			if(head.next != null) {
				head = head.next;
				head.prev.next = null;
				head.prev = null;
			}
		}

		Node curr = head;

		while(curr != null && curr.data != data)
			curr = curr.next;

		if(curr == null)	// not found
			return;

		if(curr.prev != null)
			curr.prev.next = curr.next;
		if(curr.next != null)
			curr.next.prev = curr.prev;

		curr.prev = null;
		curr.next = null;
	}

	public void print() {
		System.out.print(this + " : ");
		Node curr = head;
		while(curr != null) {
			System.out.print("[ " + curr.data + " ] ");
			curr = curr.next;
		}
		System.out.println();
	}

	public static void main(String[] args) {
		
		DoublyLinkedList<String> strings = new DoublyLinkedList();
		DoublyLinkedList<Integer> ints = new DoublyLinkedList();

		strings.push("one");
		strings.append("hello");
		strings.insert("world", 2);
		strings.remove("one");

		ints.push(1);
		ints.push(2);
		ints.append(3);
		ints.pop();

		strings.print();
		ints.print();
	}
}