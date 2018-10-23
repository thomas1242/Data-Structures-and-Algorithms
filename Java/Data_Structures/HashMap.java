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
	private final int initial_capacity = 8;
	private final double load_factor = .75;

	public HashMap() {
		table = new Object[initial_capacity];
	}

	private int hash(K key) {
		return Math.abs(key.hashCode()) % table.length;
	}

	public void put(K key, V value) {
		if(++size > load_factor * table.length)
			resizeArray();

		int index = hash(key);

		if(containsKey(key)) remove(key);

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

	public Set<V> values() {
		Set<V> values = new HashSet<>();

		for (Object bucket : table) {
			Node<K, V> curr = (Node<K, V>) bucket;
			while(curr != null) {
				values.add(curr.value);
				curr = curr.next;
			}
		}

		return values;
	}

	public void remove(K key) {
		int index = hash(key);

		if(table[index] == null) 
			return;

		if(((Node<K, V>)table[index]).key.equals(key)) 
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

	public static void main(String[] args) {
		HashMap<Integer, String> map = new HashMap<>();

		map.put(0x37, "test");
		map.put(0x37, "testing");

		System.out.println(map);	 	 
	}
}
