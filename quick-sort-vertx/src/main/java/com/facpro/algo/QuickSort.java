package com.facpro.algo;

import io.vertx.core.AbstractVerticle;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class QuickSort extends AbstractVerticle {

  @Override
  public void start(){

                vertx.executeBlocking(future -> {
                  BufferedReader br = null;
                  try{
                    System.out.print("Enter comma separated integer array : ");
                    br = new BufferedReader(new InputStreamReader(System.in));
                    String input = br.readLine();

                    /*if ("q".equals(input)) {
                        System.out.println("Exit!");
                        break;
                    }*/

                    System.out.println("input : " + input);
                    System.out.println("-----------\n");
                    future.complete(input);
                  }catch (IOException e) {
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

                }, res -> {
                  try{
                    String input = (String)res.result();
                    String[] intStrArr = input.split(",");
                    List<Integer> inpArr = new ArrayList<Integer>();
                    Arrays.stream(intStrArr).forEach(intArray -> inpArr.add(Integer.parseInt(intArray)));
                    quickSort(inpArr, 0, inpArr.size());
                    inpArr.forEach(item -> System.out.print(item + ", "));
                  } catch(NumberFormatException e){
                     e.printStackTrace();
                  }
                });
  }


  private List quickSort(List<Integer> inpArr, int low, int high){
    if(inpArr != null && inpArr.size() > 0){
      if((low + 1) < high){
        int p = partition(inpArr, low, high);
        inpArr.forEach(item -> System.out.print(item + ", "));
        System.out.println();
        quickSort(inpArr, low, p);
        quickSort(inpArr, p+1, high);
      }
    }
    return inpArr;
  }

  private int partition (List<Integer> inpArr, int low, int high){
    int pivot = inpArr.get(high-1);
    int i = low - 1;
    for(int j = low; j < high-2; j++){
      if(inpArr.get(j) < pivot){
        i++;
        swap(inpArr, i, j);
      }
    }
    System.out.println(pivot);
    swap(inpArr, i+1, high-1);
    return (i+1);
  }

  private void swap (List<Integer> inpArr, int i, int j){
    int swap = inpArr.get(i);
    inpArr.set(i,inpArr.get(j));
    inpArr.set(j,swap);
  }
}
