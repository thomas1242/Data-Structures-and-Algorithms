public class Heap {
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
        
        // heap.print();

    }

    int[] arr;
    int size;
    public Heap() {
    	arr = new int[8];
    	size = 0;
    }

    void insert(int val) {
    	arr[++size] = val; // bubble up
    	int index = size;
    	while( index > 0 && arr[index] < arr[ getParent(index) ] ) {
    		int temp = arr[ getParent(index) ];
            arr[ getParent(index) ] = arr[index];
            arr[index] = temp;
            index = getParent(index);
    	}
    }

    int remove() {
        print();

        int res = arr[1];
        arr[1] = arr[size--];
        int index = 1;

        while(index <= size && ( arr[index] > arr[getLeft(index)] || arr[index] > arr[getRight(index)] )) {
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
                print();

        return res;
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

    void print() {
        for(Integer n: arr)
            System.out.print(n + " ");
        System.out.println();
    }

    boolean isEmpty() {
        return size == 0;
    }


  //  	    1
 	//   2     3
 	// 4   5  6   7 


}