public class QuickSort {

    public static void quickSort(int[] arr, int left, int right) {

    		int partition = partition(arr, left, right);  // index of first element in rightside partition

    		if(left < partition - 1)                  // if leftside partition contains more than 1 element
    			quickSort(arr, left, partition - 1);  // recursively apply quicksort

    		if(right > partition)                     // if rightside partiton contains more than 1 element
    			quickSort(arr, partition, right);     // recursively apply quicksort
    }

    public static int partition(int[] arr, int left, int right) {

    	int pivot = arr[ (left + right) / 2 ];

    	while(left <= right) {

    		while(arr[left] < pivot)	// find leftside element greater than pivot
    			left++;

    		while(arr[right] > pivot)	// find rightside element less than pivot
    			right--;

    		if(left <= right) {			// swap the two elements
    			int temp = arr[left];	
    			arr[left] = arr[right];
    			arr[right] = temp;
    			left++;
    			right--;
    		}
    	}

    	return left;		// return index of first element in rightside partition
    }

    public static void print1DArray(int[] arr) {
        for(Integer n: arr)
            System.out.print(n + " ");
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = new int[]{9, 5, 1, 8, 7, 2, 4, 3, 6};

        System.out.println("BEFORE: " );
        print1DArray(arr);

        quickSort(arr, 0, arr.length - 1);

        System.out.println("AFTER: " );
        print1DArray(arr);
    }
}

