public class ArrayList<T> {

	private Object[] arr;
	private int size;

	public ArrayList() {
		this(8);
	}

	public ArrayList(int initialCapacity) {
		arr = new Object[initialCapacity];
	}

	public T get(int index) {		
		if(index < 0 || index >= size)
			throw new IndexOutOfBoundsException("Out of bounds: " + index + " not within [0," + (size - 1) + "]");
		
		return (T) arr[index];
	}

	public void add(T element) {				
		if(size == arr.length) 			
			arr = resizeArr(arr);

		arr[size++] = element;
	}

	public void add(T element, int index) {	
		if(index < 0 || index > size)
			throw new IndexOutOfBoundsException("Out of bounds: " + index + " not within [0," + size + "]");

		if(size == arr.length) 					
			arr = resizeArr(arr);
			
		for(int i = size + 1; i > index; i--)
			arr[i] = arr[i - 1]; 

		arr[index] = element;

		size++;
	}

	public void remove(T element) {		
		if(isEmpty()) 
			return;
		
		int index = 0;
		while(index < size && element != (T) arr[index])
			index++;

		remove(index);
	}

	public void remove(int index) {	
		if(index < 0 || index >= size)
			return;

		for (int i = index; i < size - 1; i++) 
			arr[i] = arr[i + 1];	
		
		size--;
	}

	public boolean isEmpty() {
		return size == 0;	
	}

	private Object[] resizeArr(Object[] oldArr) {
		Object[] newArr = new Object[oldArr.length * 2];
		
		for(int i = 0; i < oldArr.length; i++) 
			newArr[i] = oldArr[i];

		return newArr;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		for(int i = 0; i < size; i++)
			sb.append( arr[i] ).append( ' ' );

		return sb.toString();
	}

	public static void main(String[] args) {
		ArrayList<String> list = new ArrayList<>();
	}
}