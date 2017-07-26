public class ArrayList {

	private int[] arr;
	private int size;

	public ArrayList() {
		arr = new int[8];
		size = 0;
	}

	private int[] resizeArr(int[] arr) {
		int[] newArr = new int[size * 2];
		for(int i = 0; i < arr.length; i++) 
			newArr[i] = arr[i];
		return newArr;
	}

	public void add(int n) {
		if(size == arr.length) 
			arr = resizeArr(arr);

		arr[size++] = n;
	}

	public void add(int n, int index) {

		if(index < 0 || index > size)
			return;

		if(size == arr.length) 
			arr = resizeArr(arr);
			
		int[] temp = new int[arr.length + 1];

		for(int i = 0; i < index; i++)
			temp[i] = arr[i];

		temp[index] = n;

		for(int i = index; i < arr.length; i++) 
			temp[i + 1] = arr[i];
		
		arr = temp;
		size++;
	}

	public void remove(int index) {

		if(index < 0 || index >= size)
			return;

		int[] temp = new int[arr.length - 1];

		for (int i = 0; i < index; i++) 
			temp[i] = arr[i];
		for (int i = index; i < temp.length; i++) 
			temp[i] = arr[i+1];	
		
		arr = temp;
		size--;
	}

	public int get(int index) {
		if(index < 0 || index >= size)
			throw new RuntimeException("Out of bounds: " + index + " not within [0," + (size - 1) + "]");
		else
			return arr[index];
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < size; i++)
			sb.append(arr[i] + " ");
		return sb.toString();
	}

	public static void main(String[] args) {

		ArrayList list = new ArrayList();
	
	}

}