package com.chapter1.variant1;

/**
 * @author Krich
 *         08.10.2016.
 *
 * Вывод аргументов командной строки в обратном порядке.
 */
public class Task2 {
    public static void main(String[] args) {
        for (int i=args.length-1;i>=0;i--) {
            System.out.println(args[i]);
        }
    }
}
