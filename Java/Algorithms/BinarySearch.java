public class BinarySearch { 
	
	// recursive 
	static int binarySearch(int[] arr, int val, int low, int high) {
		if(low > high)
			return -1;

		int mid = (low + high) / 2;

		if(val > arr[mid])
			return binarySearch(arr, val, mid + 1, high);
		else if (val < arr[mid])
			return binarySearch(arr, val, low, mid - 1);
		else
			return mid;
	}

	// iterative
	static int binarySearch(int[] arr, int val) {
		int left = 0;
		int right = arr.length - 1;

		while(left <= right) {
			int mid = (left + right) / 2;
			
			if(val < arr[mid])
				right = mid - 1;
			else if(val > arr[mid])
				left = mid + 1;
			else
				return mid;
		}

		return -1;
	}

	public static void main(String[] args) {
		System.out.println(binarySearch(new int[]{1, 2, 3, 4, 5, 6, 7}, 3));
	}
}