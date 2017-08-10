public class Heap {
    
    private int[] arr;
    private int size;

    public Heap() {
    	arr = new int[8];
    }

    int getLeft(int index) {
        return index * 2;
    }
    
    int getRight(int index) {
        return index * 2 + 1;
    }

    int getParent(int index) {
        return index / 2;
    }

    boolean isEmpty() {
        return size == 0;
    }

    void insert(int val) {
        if(size == arr.length - 1)  
            arr = resizeArr(arr);
    	arr[++size] = val;         

    	int index = size;
    	while( index > 0 && arr[index] < arr[ getParent(index) ] ) {   // bubble up
    		swap(index, getParent(index));
            index = getParent(index);
    	}
    }

    int remove() {
        int top = arr[1];
        arr[1] = arr[size--];

        int index = 1;
        while(index < size && (arr[index] > arr[getLeft(index)] || arr[index] > arr[getRight(index)])) {     // bubble down
            if( getLeft(index) <= size && arr[getLeft(index)] < arr[getRight(index)] ) {
                swap(index, getLeft(index));
                index = getLeft(index);
            }
            else if ( getRight(index) <= size && arr[getRight(index)] < arr[getLeft(index)] ) {
                swap(index, getRight(index));
                index = getRight(index);
            }
            else
                break;  // heap property is restored
        }

        return top;
    }

    private void swap(int v1, int v2) {
        int temp = arr[v1];
        arr[v1]  = arr[v2];
        arr[v2]  = temp;
    }

    private int[] resizeArr(int[] arr) {
        int[] newArr = new int[size * 2];
        for(int i = 0; i < arr.length; i++) 
            newArr[i] = arr[i];
        return newArr;
    }

    static int[] heapSort(int[] unsortedArr) {
        int[] sorted = new int[unsortedArr.length];
        Heap tempHeap = new Heap();
        for(Integer n : unsortedArr)
            tempHeap.insert(n);
        for(int i = 0; i < sorted.length; i++)
            sorted[i] = tempHeap.remove();
        return sorted;
    }

    static void print1DArray(int[] arr) {
        for(Integer n: arr)
            System.out.print(n + " ");
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = new int[]{10, 15, 13, 1, 3, 5, 9, 7, 8, 6, 2, 4, 14, 12, 11};
        print1DArray(arr);
        arr = Heap.heapSort( arr );
        print1DArray(arr);
    }
}