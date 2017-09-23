class MaximumContiguousSubarray {

	static int valueOfMaximumContiguousSubarr(int[] arr) {
		int[] dp = new int[arr.length];
		dp[0] = arr[0];
		int maxSum = dp[0];

		for (int i = 1; i < dp.length; i++) {
			dp[i] = arr[i] + (dp[i - 1] > 0 ? dp[i - 1] : 0);
			maxSum = Math.max(maxSum, dp[i]);
		}

		return maxSum;
	}

	// find pivot in rotated array, return index of rightmost element in left subarr: [6, 7, 8, 1, 2, 3] 
	static int findPivot(int[] arr, int low, int high) {
		if (low > high) 
			return -1;

		int mid = (low + high) / 2;

		if(mid < high && arr[mid] > arr[mid + 1]) 
			return mid;
		if(low < mid && arr[mid] < arr[mid - 1])
			return mid - 1;
		if(arr[mid] < arr[low])
			return findPivot(arr, low, mid - 1);
		else
			return findPivot(arr, mid + 1, high);
	}

	static int findElementInRotatedSortedArr(int[] arr, int element) {

		int pivotIndex = findPivot(arr, 0, arr.length - 1);

		if(arr[0] <= element && element <= arr[pivotIndex])
			return binarySearch(arr, 0, pivotIndex, element);
		else
			return binarySearch(arr, pivotIndex + 1, arr.length - 1, element);
	}

	static int binarySearch(int[] arr, int low, int high, int element) {
		if(low > high)
			return -1;

		int mid = (low + high) / 2;

		if(element < arr[mid])
			return binarySearch(arr, low, mid - 1, element);
		else if(element > arr[mid])
			return binarySearch(arr, mid + 1, high, element);
		else 
			return mid;
	}

	public static void main(String[] args) {
		int[] arr = new int[]{1, 3, -2, 5, -10, 3, 3, -1};
		System.out.println( valueOfMaximumContiguousSubarr(arr) );
		arr = new int[]{-2, -3, 4, -1, -2, 1, 5, -3};
		System.out.println( valueOfMaximumContiguousSubarr(arr) );



		int[] rotatedSortedArr = new int[]{5, 6, 2, 3, 4};
		System.out.println( findElementInRotatedSortedArr(rotatedSortedArr, 5) );
	}
}