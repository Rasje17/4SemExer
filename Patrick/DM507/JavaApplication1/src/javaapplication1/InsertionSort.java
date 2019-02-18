/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import static java.lang.System.currentTimeMillis;

/**
 *
 * @author pvies
 */
public class InsertionSort {

    public static int[] insertionSort(int[] array) {
        long start = currentTimeMillis();
        for (int j = 1; j < array.length; j++) {
            int temp = array[j];
            int i = j - 1;
            while (i >= 0 && array[i] > temp) {
                array[i + 1] = array[i];
                i--;
            }
            array[i + 1] = temp;
        }
        long end = currentTimeMillis();
        long time = end-start;
        System.out.println("Array sorted. Time elapsed: " + time);
        return array;
    }
    
    public static int[] reverseInsertionSort(int[] array) {
        for (int j = 1; j <= array.length; j++) {
            int temp = array[j];
            int i = j - 1;
            while (i >= 0 && array[i] < temp) {
                array[i + 1] = array[i];
                i--;
            }
            array[i + 1] = temp;
        }
        return array;
    }

    public static void main(String[] args) {
        ArrayGenerator ag = new ArrayGenerator();
        int[] array1, array2, array3, array4, array5, array6, array7, array8, array9;
        
        int value = 500000;
        
        array1 = ag.sortedArray(value);
        array2 = ag.sortedArray(value);
        array3 = ag.sortedArray(value);
        array4 = ag.reverseSortedArray(value);
        array5 = ag.reverseSortedArray(value);
        array6 = ag.reverseSortedArray(value);
        array7 = ag.randomArray(value);
        array8 = ag.randomArray(value);
        array9 = ag.randomArray(value);
        
        insertionSort(array1);
        insertionSort(array2);
        insertionSort(array3);
        insertionSort(array4);
        insertionSort(array5);
        insertionSort(array6);
        insertionSort(array7);
        insertionSort(array8);
        insertionSort(array9);
        
    }

}
