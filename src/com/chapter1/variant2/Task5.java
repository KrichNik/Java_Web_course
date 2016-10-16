package com.chapter1.variant2;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Krich
 *         08.10.2016.
 *
 * Пузырьковая сортировка по убыванию модулей.
 */
public class Task5 {
    public static void main(String[] args) {
        //int n = Integer.parseInt(args[0]);
        int n=6;
        Scanner scanner = new Scanner(System.in);
        int[] arrayOfNumbers = new int[n];
        System.out.println("Введите " + n + " чисел");
        for (int i=0;i<n;i++) {
            arrayOfNumbers[i] = scanner.nextInt();
        }
        bubbleSort(arrayOfNumbers);
        System.out.println(Arrays.toString(arrayOfNumbers));
    }

    static void bubbleSort(int[] array) {
        for (int i=array.length-1;i>0;i--) {
            for (int j=0;j<i;j++) {
                if (Math.abs(array[j]) < Math.abs(array[j+1])) {
                    int temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }
            }
        }
    }
}
