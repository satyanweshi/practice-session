
import java.util.Arrays;

public class RotateArray {
    
    public static void main(String args[]){
        int[] arr = {1,2,3,4,5,6,7};

        rotateArray(arr, 7);
    }


    private static void rotateArray(int[] arr, int rotate){
        if(arr == null || arr.length == 0) {
            System.out.println("Incorrect input. Quitting!!!");
            return;
        }

        if(rotate >= arr.length) {
            System.out.println("Asked to rotate more than the array length. Quitting!!!");
            return;
        }

        for(int i=0;i<rotate;i++){
            for(int j=0;j<arr.length-1;j++){
                swap(arr ,j, j+1);
            }
        }

        Arrays.stream(arr).forEach(System.out::println);
    }

    private static void swap(int arr[], int i, int j){
        int hold = arr[i];
        arr[i] = arr[j];
        arr[j] = hold;
    }
}