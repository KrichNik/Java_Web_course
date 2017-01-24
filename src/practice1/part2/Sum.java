package practice1.part2;

/**
 *
 * @author Nikita Datsenko
 *
 * Класс реализует функциональность сложения двух чисел,
 * переданных в приложение как параметры командной строки.
 *
 */
public class Sum {

	public static void main(String[] args) {
		System.out.println("Сумма: " + (Integer.parseInt(args[0]) + Integer.parseInt(args[1])));
	}

}
