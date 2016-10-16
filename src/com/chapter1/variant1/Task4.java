package com.chapter1.variant1;

import java.util.Scanner;

/**
 * @author Krich
 *         08.10.2016.
 *
 * Сравнение пароля.
 */
public class Task4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String standardPassword = "pass";
        System.out.print("Введите пароль: ");
        String comparedPassword = scanner.nextLine();
        if (standardPassword.equals(comparedPassword)) {
            System.out.println("Верный пароль!");
        } else {
            System.out.println("Неверный пароль!");
        }
    }
}
