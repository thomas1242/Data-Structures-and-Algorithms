public class MergeSort {

    static void mergeSort(int[] arr, int left, int right) {
        if(left >= right)
            return;

        int middle = (left + right) / 2;
        
        mergeSort(arr, left, middle);            // sort left half including middle element
        mergeSort(arr, middle + 1, right);       // sort right half
        merge(arr, left, right, middle);         // merge two sorted halfs together
    }

    static void merge(int[] arr, int left, int right, int middle) {
        int[] leftArr  = new int[ middle - left + 1 ];
        int[] rightArr = new int[ right - middle ];

        for(int i = 0; i < leftArr.length;  i++) leftArr[i] = arr[left + i];
        for(int i = 0; i < rightArr.length; i++) rightArr[i] = arr[middle + i + 1];

        int i = 0, j = 0, k = left;
        while(i < leftArr.length && j < rightArr.length) {
            if(leftArr[i] < rightArr[j]) arr[k++] =  leftArr[i++];
            else                         arr[k++] = rightArr[j++];
        }

        while(i < leftArr.length )   arr[k++] =  leftArr[i++];
        while(j < rightArr.length)   arr[k++] = rightArr[j++];
    }

    public static void main(String[] args) {
        int[] arr = new int[]{9, 5, 1, 8, 7, 2, 4, 3, 6};
        
        System.out.println( java.util.Arrays.toString(arr) );

        mergeSort(arr, 0, arr.length - 1);

        System.out.println( java.util.Arrays.toString(arr) ); 
    }

}