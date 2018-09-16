public class QuickSort {
    public static void quickSort(int[] arr, int left, int right) {
		int partition = partition(arr, left, right);  // index of first element in rightside partition

		if(partition - left > 1)                      // if leftside partition contains more than 1 element
			quickSort(arr, left, partition - 1);      // recursively apply quicksort

		if(right - partition > 0)                     // if rightside partiton contains more than 1 element
			quickSort(arr, partition, right);         // recursively apply quicksort
    }

    public static int partition(int[] arr, int left, int right) {
    	int pivot = arr[ (left + right) / 2 ];

    	while(left <= right) {  
    		while(arr[left] < pivot)	// find leftside element greater than pivot
    			left++;

    		while(arr[right] > pivot)	// find rightside element less than pivot
    			right--;

    		if(left <= right)     		// swap the two elements
    			swap(arr, left++, right--);
    	}

    	return left;		// return index of first element in rightside partition
    }

    private static void swap(int[] arr, int a, int b) {
        int temp = arr[a];   
        arr[a] = arr[b];
        arr[b] = temp;
    } 

    public static void main(String[] args) {
        int[] arr = new int[]{9, 5, 1, 8, 7, 2, 4, 3, 6, 10, 12, 11};

        System.out.println( java.util.Arrays.toString(arr) );

        quickSort(arr, 0, arr.length - 1);

        System.out.println( java.util.Arrays.toString(arr) );
    }
}

