public class BinarySearch {

	// recursive 
	static boolean binarySearch(int[] a, int val, int low, int high) {
		if(low > high)
			return false;

		int mid = (low + high) / 2;

		if(val > a[mid])
			return binarySearch(a, val, mid + 1, high);
		else if (val < a[mid])
			return binarySearch(a, val, low, mid - 1);
		else
			return true;
	}

	// iterative
	static boolean binarySearch(int[] arr, int val) {
		int left = 0;
		int right = arr.length - 1;
		int mid;

		while( left <= right ) {
		
			mid = (left + right) / 2;
			
			if(val < arr[mid])
				right = mid - 1;
			else if(val > arr[mid])
				left = mid + 1;
			else
				return true;
		}

		return false;
	}


	public static void main(String[] args) {}
}