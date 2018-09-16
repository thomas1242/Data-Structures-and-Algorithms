public class Heap<T extends Comparable<T>> {  // min heap 
    
    private Object[] arr;
    private int size;

    public Heap() {
        this(8);
    }

    public Heap(int initialCapacity) {
    	arr = new Object[initialCapacity];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(T val) {
        if(size == arr.length - 1) 
            arr = resizeArr(arr);

        arr[++size] = val;    // Insert element at bottom of heap and bubble element up tree to restore heap property
        int child = size;

        while(child > 1 && ((T)arr[child]).compareTo((T) arr[getParent(child)]) < 0) { 
            swap(child, getParent(child));
            child = getParent(child);  
        }
    }

    public T remove() {
        if(size == 0) return null;

        T data = (T) arr[1];   
        arr[1] = arr[size--]; // Replace root of heap with the last element on the last level
                              // and bubble element down tree to restore heap property
        heapify(1);
        return data;
    }

    public void heapify(int root) {
        if(getLeft(root) > size)  
            return;

        int minChild = getRight(root) > size ? getLeft(root) : ((T)arr[getLeft(root)]).compareTo(((T)arr[getRight(root)])) < 0 ? getLeft(root) : getRight(root);
       
        if(((T)arr[minChild]).compareTo((T)arr[root]) < 0) {
            swap(minChild, root);
            heapify(minChild);
        }
    }

    private void swap(int index1, int index2) {
        Object temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }

    private int getLeft(int index) {
        return index * 2;
    }
    
    private int getRight(int index) {
        return index * 2 + 1;
    }

    private int getParent(int index) {
        return index / 2;
    }

    private Object[] resizeArr(Object[] arr) {
        Object[] newArr = new Object[size * 2];
        for(int i = 0; i < arr.length; i++) 
            newArr[i] = arr[i];
        return newArr;
    }

    public static void main(String[] args) {
        Heap<Integer> heap = new Heap<>();
    }
}