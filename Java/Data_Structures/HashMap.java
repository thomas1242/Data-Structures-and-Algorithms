import java.util.Set;
import java.util.HashSet;

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
	private final double load_factor = 0.75;

	public HashMap() {
		table = new Object[initial_capacity];
	}

	@SuppressWarnings("unchecked")
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

	@SuppressWarnings("unchecked")	
	public void put(K key, V value) {
		int hashCode = getHashCode( key );

		if(++size > table.length * load_factor )
			resizeArray();

		if(table[hashCode] == null)
			table[hashCode] = new Node<K, V>(key, value);
		else {
			Node<K, V> curr = (Node<K, V>) table[hashCode];
			while(curr.next != null)
				curr = curr.next;
			curr.next = new Node<K, V>(key, value);
		}
	}

	@SuppressWarnings("unchecked")	
	public V get(K key) {
		int hashCode = getHashCode( key );
		
		Node<K, V> curr = (Node<K, V>) table[hashCode];
		while(curr != null && curr.key != key)
			curr = curr.next;

		return curr != null ? curr.value : null;
	}

	@SuppressWarnings("unchecked")	
	public boolean containsKey(K key) {
		int hashCode = getHashCode( key );
		
		Node<K, V> curr = (Node<K, V>) table[hashCode];
		while(curr != null && curr.key != key)
			curr = curr.next;

		return curr != null ? true : false;
	}

	private void resizeArray() {
		Set<Node<K, V>> entrySet = entrySet();

		table = new Object[table.length * 2];
		size = 0;
		
		for(Node<K, V> entry : entrySet)	// rehash items into new table 
			put(entry.key, entry.value);	// because hash function uses updated table size
	}

	@SuppressWarnings("unchecked")
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

	@Override @SuppressWarnings("unchecked")	
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
			sb.append("\n");
		}

		return sb.toString();
	}

	private int getHashCode(K key) {
		return Math.abs(key.hashCode()) % table.length;
	}

	public static void main(String[] args) {
		HashMap<Integer, String> map = new HashMap<>();
		map.put(5,  "five");
		map.put(6,  "six");
		map.put(7,  "seven");	
		System.out.println(map);	// map is 75% full, next insert will trigger resize

		map.put(8,  "eight");
		map.put(9,  "nine");
		map.put(10, "ten");
		System.out.println(map);	// print map after resize

		System.out.println(map.get(5));	
	}
}
