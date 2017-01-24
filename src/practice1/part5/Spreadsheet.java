package practice1.part5;

/**
 *
 * @author Nikita Datsenko
 *
 * Класс позволяет определять порядковый номер колонки по ее буквенному
 * номеру и наоборот.
 */
public class Spreadsheet {

	public static void main(String... args) {
		print("A");
		print("B");
		print("Z");
		print("AA");
		print("AZ");
		print("BA");
		print("ZZ");
		print("AAA");
	}

	public static void print(final String value) {
		System.out.println(value + " ==> " + charsToDigits(value) + " ==> " + digitsToChars(charsToDigits(value)));
	}

	/**
	 * Метод определения порядкового номера колонки по ее буквенному номеру.
	 *
	 * @param number
	 *            буквенный номер колонки.
	 * @return порядковый номер колонки.
	 */
	public static int charsToDigits(final String number) {
		char[] numbers = number.toUpperCase().toCharArray();
		int[] arrOfNum = new int[number.length()];
		int sum = 0;
		for (int i = 0; i < arrOfNum.length; i++) {
			arrOfNum[i] = (int) numbers[i] - 64;
		}
		for (int j = arrOfNum.length - 1, i = 0; j >= 0; i++, j--) {
			int sum1 = (int) Math.pow(26, j) * arrOfNum[i];
			sum = sum + sum1;
		}
		return sum;
	}

	/**
	 * Метод определения буквенного номера колонки по ее порядковому номеру.
	 *
	 * @param value
	 *            порядковый номер колонки.
	 * @return буквенный номер колонки.
	 */
	public static String digitsToChars(final int value) {
		int number = value;
		StringBuilder chars = new StringBuilder("");
		StringBuilder charsMirror = new StringBuilder("");
		int modul;
		while (number != 0) {
			modul = number % 26;
			if (modul == 0) {
				chars.append("Z");
				number = (number - 1) / 26;
			} else {
				chars.append((char) (modul + 64));
				number = (number - modul) / 26;
			}
		}
		for (int i = 0; i < chars.length(); i++) {
			charsMirror.append(chars.charAt(chars.length() - i - 1));
		}
		return charsMirror.toString();
	}

	/**
	 * Метод определения по буквенному номеру колонки номер колонки, которая
	 * находится справа от данной.
	 */
	public static String toRightColumn(final String number) {
		String chars = "";
		int num;
		num = charsToDigits(number);
		num++;
		chars = digitsToChars(num);
		return chars;
	}

}
