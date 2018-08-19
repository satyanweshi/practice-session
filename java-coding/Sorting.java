
import java.util.Arrays;
import java.lang.Math;

public class Sorting {
    
    // Insertion sort
    public static void insertionSort(int arr[]){
        if(arr == null || arr.length < 2) return;

        for(int i = 1; i < arr.length; i++){
            for(int j=i; j>= 1; j--){
                if(arr[j] < arr[j-1]) swap(arr, j, j-1);
            }
        }
    }

    private static void swap(int arr[], int i, int j){
        if(arr == null || arr.length <= 1) return;
        if(i == j) return;
        if(i < 0 || j < 0) return;

        int temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
    }

    // Heap Sort
    private static void heapSort(int arr[]){
        if(arr == null || arr.length < 2) return;

        int heapSize = arr.length;
        buildMaxHeap(arr, heapSize);
        for(int i = arr.length-1; i > 0; i--){
            swap(arr, 0, i);
            heapSize = heapSize - 1;
            maxHeapify(arr, 0, heapSize);
        }
    }

    private static void buildMaxHeap(int arr[], int heapSize){
        int start = Math.floorDiv(arr.length, 2);
        for(int i = start; i >= 0; i--){
            maxHeapify(arr, i, heapSize);
        }
    }

    private static void maxHeapify(int arr[], int index, int heapSize){
        int left = getLeftChildOfHeap(index);
        int right = getRightChildOfHeap(index);
        int largest = index;
        if(left < heapSize && (arr[index] < arr[left])){
            largest = left;
        }
        if(right < heapSize && (arr[right] > arr[largest])){
            largest = right;
        }
        if(largest != index){
            swap(arr, index, largest);
            maxHeapify(arr, largest, heapSize);
        }
    }

    private static int getLeftChildOfHeap(int index){
        return (index << 1);
    }

    private static int getRightChildOfHeap(int index){
        return ((index << 1) + 1);
    }

    private static void printArray(int arr[]){
        Arrays.stream(arr).forEach(x -> {System.out.print(x + ", ");});
        System.out.println();
    }


    //quick sort
    private static void quickSort(int arr[]){
        if(arr == null || arr.length <= 1) return;
        quickSort(arr, 0, arr.length - 1);
    }

    private static void quickSort(int arr[], int p, int r){
        if(p >= r) return;

        int q = partition(arr, p, r);
        quickSort(arr, p, q-1);
        quickSort(arr, q+1, r);
    }

    private static int partition(int arr[], int p, int r){
        int pivot = arr[r];
        int i = p-1;
        for(int j = p; j < r; j++){
            if(arr[j] <= pivot){
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i+1, r);
        return i+1;
    }

    public static void main(String args[]){
        int[] arr = {29, 44, 31, 2, 4, 78, 65, 11, 52, 21, 100, 97, 27, 54, 23, 105, 123, 33, 89, 91};

        long timer = System.currentTimeMillis();
        printArray(arr);
        // insertionSort(arr);
        // heapSort(arr);
        quickSort(arr);
        printArray(arr);
        System.out.println(System.currentTimeMillis() - timer);
    }
}