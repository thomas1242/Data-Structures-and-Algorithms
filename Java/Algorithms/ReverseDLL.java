class Node {
	int data;
	Node next;
	Node prev;
	
	public Node(int data) {
		this.data = data;
	}
}

public class ReverseDLL {

	// reverse a doubly linked list
	static Node reverse(Node head) {
		if(head == null) return null;

		Node curr = head;
		Node next = null, prev = null;

		while(curr != null) {
			next = curr.next;
			curr.next = prev;
			curr.prev = next;	

			prev = curr;
			curr = next;			
		}

		return prev;
	}

	public static void main (String[] args) {
		Node head = buildDLL(10);
		
		print(head);
		head = reverse(head);
		print(head);
	}

	static Node buildDLL(int n) {
		Node head = new Node(0);
		Node curr = head; 
		for (int i = 1; i < n; i++) {
			Node node = new Node(i);
			node.prev = curr;
			curr.next = node;
			curr = node;
		}
		return head;
	}

	static void print(Node head) {
		Node curr = head;
		while(curr != null) {
			System.out.print(curr.data + " ");
			curr = curr.next;
		}
		System.out.println();
	}

}