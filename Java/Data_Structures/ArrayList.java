public class ArrayList<T> {

	private Object[] arr;
	private int size;

	public ArrayList() {
		arr = new Object[8];
		size = 0;
	}

	public boolean isEmpty() {
		return size == 0;	
	}

	private Object[] resizeArr(Object[] arr) {
		Object[] newArr = new Object[size * 2];
		
		for(int i = 0; i < arr.length; i++) 
			newArr[i] = arr[i];

		return newArr;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		for(int i = 0; i < size; i++)
			sb.append(arr[i] + " ");

		return sb.toString();
	}

	public void add(T element) {				
		if(size == arr.length) 			// resize array if full
			arr = resizeArr(arr);

		arr[size++] = element;
	}

	public void add(T element, int index) {	
		if(index < 0 || index > size)
			return;

		if(size == arr.length) 					// resize array if full
			arr = resizeArr(arr);
			
		for(int i = size + 1; i > index; i--)	// shift elements over to make room
			arr[i] = arr[i - 1];

		arr[index] = element;

		size++;
	}

	@SuppressWarnings("unchecked")	
	public T get(int index) {			// get element at index
		if(index < 0 || index >= size)
			throw new RuntimeException("Out of bounds: " + index + " not within [0," + (size - 1) + "]");
		else
			return (T) arr[index];
	}

	public void remove(int index) {		// remove element at index
		if(index < 0 || index >= size)
			return;

		for (int i = index; i < size - 1; i++) 
			arr[i] = arr[i + 1];	
		
		size--;
	}

	@SuppressWarnings("unchecked")	 
	public void remove(T element) {			/// remove element from the list
		if(isEmpty()) 
			return;

		int index = 0;
		while(index < size && element != (T) arr[index])
			index++;

		remove(index);
	}

	public static void main(String[] args) {
		ArrayList<Integer> integerList = new ArrayList<>();
		ArrayList<String> stringList = new ArrayList<>();

		for(int i = 0; i <= 5; i++) 
			integerList.add( i * 10 );

		String[] words = new String[]{"one", "two", "four", "eight", "sixteen"};
		for(String s : words) 
			stringList.add( s );
	
		stringList.remove( 0 );
		stringList.remove( "four" );

		System.out.println( integerList.toString() );
		System.out.println( stringList.toString() );
	}
}
