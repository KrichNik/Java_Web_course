package practice1.part3;

/**
 *
 * @author Nikita Datsenko
 *
 * Класс реализует функциональность определения суммы цифр целого
 * положительного числа, переданного в приложение как параметр командной строки.
 *
 */
public class ParseSum {

	public static void main(String... args) {
		int value = Integer.parseInt(args[0]);
		System.out.println("Заданное число: " + value);
		int sum = 0;
		while (value != 0) {
			sum += value % 10;
			value /= 10;
		}
		System.out.println("Сумма цифр в заданном числе: " + sum);
	}

}
