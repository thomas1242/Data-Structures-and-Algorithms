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

	public void add(T n) {
		if(size == arr.length) 
			arr = resizeArr(arr);

		arr[size++] = n;
	}

	public void add(T n, int index) {

		if(index < 0 || index > size)
			return;

		if(size == arr.length) 
			arr = resizeArr(arr);
			
		Object[] temp = new Object[arr.length + 1];

		for(int i = 0; i < index; i++)
			temp[i] = arr[i];

		temp[index] = n;

		for(int i = index; i < arr.length; i++) 
			temp[i + 1] = arr[i];
		
		arr = temp;
		size++;
	}

	public void remove(int index) {		// remove element at given index

		if(index < 0 || index >= size)
			return;

		Object[] temp = new Object[arr.length - 1];

		for (int i = 0; i < index; i++) 
			temp[i] = arr[i];
		for (int i = index; i < temp.length; i++) 
			temp[i] = arr[i+1];	
		
		arr = temp;
		size--;
	}

	@SuppressWarnings("unchecked")	// guaranteed to be legal at compile time	
	public void remove(T obj) {	// remove given object from the list

		if( isEmpty() )
			return;

		Object[] temp = new Object[arr.length - 1];

		int index = 0;
		while(index < size && obj != (T) arr[index])
			index++;

		remove(index);
	}

	@SuppressWarnings("unchecked")	// guaranteed to be legal at compile time
	public T get(int index) {
		if(index < 0 || index >= size)
			throw new RuntimeException("Out of bounds: " + index + " not within [0," + (size - 1) + "]");
		else
			return (T) arr[index];
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < size; i++)
			sb.append(arr[i] + " ");
		return sb.toString();
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
