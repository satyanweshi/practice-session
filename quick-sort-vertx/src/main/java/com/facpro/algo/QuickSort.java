import com.facpro.algo;

import io.vertx.core.AbstractVerticle;
import io.vertx.example.util.Runner;

import java.util.List;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class QuickSort extends AbstractVerticle {

  // Convenience method so you can run it in your IDE
  public static void main(String[] args) {
    Runner.runExample(QuickSort.class);
  }

  @Override
  public void start(){
    BufferedReader br = null;

        try {
          
            br = new BufferedReader(new InputStreamReader(System.in));

            while (true) {

                System.out.print("Enter something : ");
                String input = br.readLine();

                if ("q".equals(input)) {
                    System.out.println("Exit!");
                    System.exit(0);
                }

                System.out.println("input : " + input);
                System.out.println("-----------\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
  }


  private List quickSort(List<Integer> inpArr, int low, int high){
    if(inpArr != null && inpArr.size() > 0){
      if(low < high){
        int p = partition(inpArr, low, high);
        quickSort(inpArr, low, p-1);
        quickSort(inpArr, p+1, high);
        inpArr.forEach(System.out::println);
      }
    }
  }

  private int partition (List<Integer> inpArr, int low, int high){
    int pivot = inpArr.get(high);
    int i = low - 1;
    for(j = low; j < high-1; j++){
      if(inpArr.get(j) < pivot){
        i++;
        swap(inpArr, i, j);
      }
    }
    swap(inpArr, i+1, high);
    return (i+1);
  }

  private void swap (List<Integer> inpArr, int i, int j){
    int swap = inpArr.get(i);
    inpArr.set(i,inpArr.get(j));
    inpArr.set(j,swap);
  }
}
