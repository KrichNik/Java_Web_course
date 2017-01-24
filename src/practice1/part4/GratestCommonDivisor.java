package practice1.part4;

/**
 *
 * @author Nikita Datsenko
 *
 * 	Класс реализует функциональность определения наибольшего
 * общего делителя двух целых положительных чисел, переданных в
 * приложение как параметры командной строки.
 * Алгоритм Евклида.
 *
 */
public class GratestCommonDivisor {

	public static void main(String[] args) {
		int a = Integer.parseInt(args[0]);
		int b = Integer.parseInt(args[1]);
		while (b != 0) {
			int tmp = a % b;
			a = b;
			b = tmp;
		}
		System.out.println("Наибольший общий делитель: " + a);
	}

}
