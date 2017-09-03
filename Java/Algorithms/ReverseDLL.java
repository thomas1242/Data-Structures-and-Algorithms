class Node {
	int val;
	Node next;
	Node prev;

	public Node(int val) {
		this.val = val;
	}
}

public class ReverseDLL {

	// reverse a doubly linked list
	static Node reverse(Node head) {
		if(head == null) return null;

		Node curr = head;
		Node next = null;
		Node prev = null;

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
		Node head = new Node(1);
		head.next = new Node(2);
		head.next.prev = head;
		head.next.next = new Node(3);
		head.next.next.prev = head.next;
		head.next.next.next = new Node(4);
		head.next.next.next.prev = head.next;
		
		print(head);
		head = reverse(head);
		print(head);
	}

	static void print(Node head) {
		Node curr = head;
		while(curr != null) {
			System.out.print(curr.val + " ");
			curr = curr.next;
		}
		System.out.println();
	}

}