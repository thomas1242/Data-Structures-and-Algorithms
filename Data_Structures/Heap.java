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
    		int temp = arr[ getParent(index) ];
            arr[ getParent(index) ] = arr[index];
            arr[index] = temp;
            index = getParent(index);
    	}
    }

    int remove() {
        int res = arr[1];
        arr[1] = arr[size--];

        int index = 1;
        while(index < size && ( arr[index] > arr[getLeft(index)] || arr[index] > arr[getRight(index)] )) {     // bubble down
            if( getLeft(index) <= size && arr[getLeft(index)] < arr[getRight(index)]) {
                int temp = arr[index];
                arr[index] = arr[getLeft(index)];
                arr[getLeft(index)] = temp;
                index = getLeft(index);
            }
            else if ( getRight(index) <= size && arr[getRight(index)] < arr[getLeft(index)]) {
                int temp = arr[index];
                arr[index] = arr[getRight(index)];
                arr[getRight(index)] = temp;
                index = getRight(index);
            }
            else
                break;  // heap property is restored
        }

        return res;
    }

    private int[] resizeArr(int[] arr) {
        int[] newArr = new int[size * 2];
        for(int i = 0; i < arr.length; i++) 
            newArr[i] = arr[i];
        return newArr;
    }

    public int[] heapSort(int[] unsortedArr) {
        int[] sorted = new int[unsortedArr.length];
        Heap heap = new Heap();
        for(Integer n : unsortedArr)
            heap.insert(n);
        for(int i = 0; i < sorted.length; i++)
            sorted[i] = heap.remove();
        return sorted;
    }

    public static void print1DArray(int[] arr) {
        for(Integer n: arr)
            System.out.print(n + " ");
        System.out.println();
    }

    public static void main(String[] args) {

        int[] arr = new int[]{10, 15, 13, 1, 3, 5, 9, 7, 8, 6, 2, 4, 14, 12, 11};
        print1DArray(arr);

        arr = new Heap().heapSort( arr );

        print1DArray(arr);

    }

}