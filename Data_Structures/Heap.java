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
            if(arr[getLeft(index)] < arr[getRight(index)]) {
                int temp = arr[index];
                arr[index] = arr[getLeft(index)];
                arr[getLeft(index)] = temp;
                index = getLeft(index);
            }
            else {
                int temp = arr[index];
                arr[index] = arr[getRight(index)];
                arr[getRight(index)] = temp;
                index = getRight(index);
            }
        }

        return res;
    }

    void print() {
        for(Integer n: arr)
            System.out.print(n + " ");
        System.out.println();
    }

    public static void main(String[] args) {

        Heap heap = new Heap();
        heap.insert(5);
        heap.insert(7);
        heap.insert(1);
        heap.insert(3);
        heap.insert(2);
        heap.print();

        while( !heap.isEmpty() ) 
            System.out.println(heap.remove());
    }


    //       1
 	//   2      3
 	// 4   5  6   7 


}