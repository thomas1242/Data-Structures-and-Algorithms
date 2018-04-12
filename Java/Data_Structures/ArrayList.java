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

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		for(int i = 0; i < size; i++)
			sb.append(arr[i]).append(' ');

		return sb.toString();
	}

	private Object[] resizeArr(Object[] arr) {
		Object[] newArr = new Object[size * 2];
		
		for(int i = 0; i < arr.length; i++) 
			newArr[i] = arr[i];

		return newArr;
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

	@SuppressWarnings("unchecked")	
	public T get(int index) {		
		if(index < 0 || index >= size)
			throw new IndexOutOfBoundsException("Out of bounds: " + index + " not within [0," + (size - 1) + "]");
		else
			return (T) arr[index];
	}

	@SuppressWarnings("unchecked")	 
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

	public static void main(String[] args) {
		ArrayList<Integer> integerList = new ArrayList<>();

		for(int i = 0; i <= 5; i++) 
			integerList.add( i * 10 );

		System.out.println( integerList );

		ArrayList<String> stringList = new ArrayList<>();
		
		for(String s : new String[]{"one", "two", "four", "eight", "sixteen"}) 
			stringList.add( s );
	
		stringList.remove( 0 );
		stringList.remove( "four" );

		System.out.println( stringList );
	}
}
