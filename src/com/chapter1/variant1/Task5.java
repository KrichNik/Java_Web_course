package com.chapter1.variant1;

/**
 * @author Krich
 *         08.10.2016.
 *
 * Числа как аргументы командной строки.
 */
public class Task5 {
    public static void main(String[] args) {
        int product = 1;
        int sum = 0;
        for (String arg:args) {
            int number = stringToNumber(arg);
            sum += number;
            product *= number;
        }
        System.out.printf("Сумма: %1$d, произведение: %2$d\n", sum, product);
    }

    static int stringToNumber(String arg) {
        int number = 0;
        try {
            number = Integer.parseInt(arg);
        } catch (NumberFormatException e) {
            System.out.println("Неверные данные!");
        }
        return number;
    }
}
