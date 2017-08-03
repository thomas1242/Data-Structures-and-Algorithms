public class DoublyLinkedList {

	private class Node {
		int data;
		Node next;
		Node prev;
		public Node(int data) {
			this.data = data;
		}
	}	

	public static void main(String[] args) {

		DoublyLinkedList list = new DoublyLinkedList();

		list.push(55);
		list.append(123);
		// list.pop();
		// list.insert(10, 3);
		// list.delete(123);

		list.print();
	}

	private Node head;
	int size;

	public DoublyLinkedList() {
		head = null;
		size = 0;
	}

	public void push(int data) {
		Node newNode = new Node(data);
		newNode.prev = null;

		if(head != null) {
			newNode.next = head;
			head.prev = newNode;
		}

		head = newNode;
		size++;
	}

	public void append(int data) {
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

	public void insert(int data, int index) {

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

	public void delete(int data) {
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

	public int pop() {
		if(head == null)
			return -1;
		int data = head.data;

		if(head.next != null)
			head = head.next;

		head.prev.next = null;
		head.prev = null;

		return data;
	}



	public void print() {
		Node curr = head;
		while(curr != null) {
			System.out.print(curr.data + " ");
			curr = curr.next;
		}
		System.out.println();
	}
}
