public class HashMap<K, V> {

	private static class Node<K, V> {
		private K key;
		private V value;
		private Node next;

		public Node(K key, V value) {
				this.key = key;
				this.value = value;
		}
	}

	private static final int initial_capacity = 8;
	private static final double load_factor  = 0.7;
	private Node[] table;
	private int size;

	public HashMap() {
		table = new Node[ initial_capacity ];
		size = 0;
	}

	public void put(K key, V value) {
		int hashCode = getHashCode( key );

		if(++size > table.length * load_factor )
			table = resizeArray(table);

		if(table[hashCode] == null)
			table[hashCode] = new Node(key, value);
		else {
			Node curr = table[hashCode];
			while(curr.next != null)
				curr = curr.next;
			curr.next = new Node(key, value);
		}
	}

	public V get(K key) {
		int hashCode = getHashCode( key );
		
		Node curr = table[hashCode];
		while(curr != null && curr.key != key)
			curr = curr.next;

		if(curr != null) 
			return (V)curr.value;
		// if entry not found due to resize
		else {
			for(Node list : table) {	// search the whole table, it could be anywhere
				curr = list;
				while(curr != null && curr.key != key)
					curr = curr.next;
				if(curr != null && curr.key == key)
					return (V)curr.value;
			}
			return null;
		}
	}

	public boolean containsKey(K key) {
		int hashCode = getHashCode( key );
		
		Node curr = table[hashCode];
		while(curr != null && curr.key != key)
			curr = curr.next;

		// if entry found
		if(curr != null) 
			return true;
		// if entry not found due to array resizing after first hash
		else {
			for(Node list : table) {	// search the whole table, it could be anywhere
				curr = list;
				while(curr != null && curr.key != key)
					curr = curr.next;
				if(curr != null && curr.key == key)
					return true;
			}
				return false;
		}
	}

	public Node[] resizeArray(Node[] oldTable) {
		Node[] newTable = new Node[oldTable.length * 2];
		for(int i = 0; i < oldTable.length; i++) 
			newTable[i] = oldTable[i];
		return newTable;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		int i = 0;
		for (Node n : table) {
			Node curr = n;
			sb.append( "bucket " + i++ + ": " );
			while(curr != null) {
				sb.append( "{" + curr.key + ", " + curr.value + "} " );
				curr = curr.next;
			}
			sb.append("\n");
		}
		sb.append("\n");
		return sb.toString();
	}

	private int getHashCode(K key) {
		return Math.abs(key.hashCode()) % table.length;
	}

	public static void main(String[] args) {
		HashMap<String, Integer> map = new HashMap<>();
		HashMap<Integer, String> map1 = new HashMap<>();

		map.put("first", 1);
		map.put("second", 2);
		map.put("third", 3);
		map.put("fourth", 4);
		map1.put(5, "fifth");
		map1.put(6, "six");
		map1.put(7, "seven");
		map1.put(8, "eight");

		System.out.print(map.toString());
		System.out.print(map1.toString());
	}

}