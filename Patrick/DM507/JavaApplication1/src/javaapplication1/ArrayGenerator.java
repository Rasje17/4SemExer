/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

/**
 *
 * @author pvies
 */
public class ArrayGenerator {

    public int[] sortedArray(int index) {
        int[] array = new int[index];
        for (int i = 0; i < index; i++) {
            array[i] = i;
        }
        return array;
    }

    public int[] reverseSortedArray(int index) {
        int[] array = new int[index];
        int j = 0;
        for (int i = index-1; i >= 0; i--) {
            array[j] = i;
            j++;
        }
        return array;
    }
    
    public int[] randomArray(int index) {
        int[] array = new int[index];
        for (int i = 0; i < index; i++) {
            array[i] = (int) (Math.random()*100);
        }
        return array;
    }
}
