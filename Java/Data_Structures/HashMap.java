import java.util.Set;
import java.util.HashSet;
import java.util.Random;

public class HashMap<K, V> {

	private static class Node<K, V> {
		private K key;
		private V value;
		private Node<K, V> next;

		public Node(K key, V value) {
				this.key = key;
				this.value = value;
		}
	}

	private Object[] table;
	private int size;
	private final int initial_capacity = 4;
	private final double load_factor = .75;

	public HashMap() {
		table = new Object[initial_capacity];
	}

	public void put(K key, V value) {
		if(++size > table.length * load_factor )
			resizeArray();

		int index = hash(key);

		Node<K, V> newEntry = new Node<>(key, value);
		newEntry.next = (Node<K, V>) table[index];
		table[index] = newEntry;
	}

	public V get(K key) {
		int index = hash(key);
		
		Node<K, V> curr = (Node<K, V>) table[index];
		while(curr != null && !curr.key.equals(key))
			curr = curr.next;

		return curr != null ? curr.value : null;
	}

	private void resizeArray() {
		Set<Node<K, V>> entrySet = entrySet();

		table = new Object[table.length * 2];	// resize table
		size = 0;
		
		for(Node<K, V> entry : entrySet)		// rehash items into new table 
			put(entry.key, entry.value);	
	}
	
	public boolean containsKey(K key) {
		int index = hash(key);
		
		Node<K, V> curr = (Node<K, V>) table[index];
		while(curr != null && !curr.key.equals(key))
			curr = curr.next;

		return curr != null ? true : false;
	}

	public Set<K> keySet() {
		Set<K> keys = new HashSet<>();

		for (Object bucket : table) {
			Node<K, V> curr = (Node<K, V>) bucket;
			while(curr != null) {
				keys.add(curr.key);
				curr = curr.next;
			}
		}

		return keys;
	}

	public void remove(K key) {
		int index = hash(key);

		if(table[index] == null) 
			return;

		if(((Node<K, V>) table[index]).key.equals(key)) 
			table[index] = ((Node<K, V>) table[index]).next;
		else {
			Node<K, V> curr = (Node<K, V>) table[index];
			while(curr.next != null) {
				if(curr.next.key.equals(key)) {
					curr.next = curr.next.next;
					break;
				}
				curr = curr.next;
			}
		}
	}

	public Set<Node<K, V>> entrySet() {
		Set<Node<K, V>> entries = new HashSet<>();

		for (Object bucket : table) {
			Node<K, V> curr = (Node<K, V>) bucket;
			while(curr != null) {
				entries.add(curr);
				curr = curr.next;
			}
		}

		return entries;
	}

	@Override 	
	public String toString() {
		StringBuilder sb = new StringBuilder();

		int i = 0;
		for (Object bucket : table) {
			Node<K, V> curr = (Node<K, V>) bucket;
			sb.append( "bucket " + i++ + ": " );
			while(curr != null) {
				sb.append( "{" + curr.key + ", " + curr.value + "} " );
				curr = curr.next;
			}
			sb.append('\n');
		}

		return sb.toString();
	}

	private int hash(K key) {
		return Math.abs(key.hashCode()) * 37 % table.length;
	}

	public static void main(String[] args) {
		HashMap<Integer, String> map = new HashMap<>();

		Random rand = new Random();
		for (int i = 0; i < 10; i++) {
			int randInt = rand.nextInt(1000);
			map.put(randInt, randInt + "");
		}

		System.out.println(map);	
		for(Integer key : map.keySet())
			if(key % 2 == 1)
				map.remove(key);
		System.out.println(map);	 
	}
}
