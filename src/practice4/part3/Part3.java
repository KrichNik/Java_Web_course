package practice4.part3;

import practice4.Util;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



/**
 * Задание 3
 *
 * Файл содержит символы, слова, целые числа и числа с плавающей точкой.
 * Написать класс, который имеет следующую функциональность: в цикле
 * пользователь вводит тип данных (один из: char, String, int, double), в ответ
 * приложение печатает в консоль все значения соответствующих типов, которые
 * существуют в файле. Задачу решить с использованием регулярных выражений.
 *
 * Замечание: под строкой понимать последовательность символов два и более.
 * Символы - латинские или кириллические буквы в верхнем или нижнем регистре
 * (обязательно предусмотреть наличие кириллицы во входном файле).
 *
 *
 * @author Nikita Datsenko
 *
 */
public class Part3 {

	private static final String FILE_NAME = "src/practice4/file3.txt";

	private static final String FILE_INPUT;

	private static final String REGEX_FIND_CHARS = "^[a-zA-Z\u0430-\u044F\u0410-\u042F]\\s|\\s[a-zA-Z\u0430-\u044F\u0410-\u042F]\\s([a-zA-Z\u0430-\u044F\u0410-\u042F]\\s)*|\\s[a-zA-Z\u0430-\u044F\u0410-\u042F]$";

	private static final String REGEX_FIND_STRING = "[a-zA-Z\u0430-\u044F\u0410-\u042F]{2,}";

	private static final String REGEX_FIND_INT = "^\\d+|\\s\\d+\\s(\\d\\s)*|\\d+$";

	private static final String REGEX_FIND_DOUBLE = "\\d+\\.\\d*|\\d*\\.\\d+";

	static {
		FILE_INPUT = Util.readFromFile(FILE_NAME);
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in, "UTF-8");
		String input;
		while (!(input = scanner.nextLine()).equals("exit")) {
			System.out.print(input + ": ");
			switch (input) {
			case "char": {
				findMatches(REGEX_FIND_CHARS);
				break;
			}
			case "String": {
				findMatches(REGEX_FIND_STRING);
				break;
			}
			case "int": {
				findMatches(REGEX_FIND_INT);
				break;
			}
			case "double": {
				findMatches(REGEX_FIND_DOUBLE);
				break;
			}
			default: {
				System.out.println("Invalid input!");
			}
			}
			System.out.println();
		}
	}

	public static void findMatches(final String regex) {
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(FILE_INPUT);
		while (m.find()) {
			System.out.print(m.group().trim() + " ");
		}
	}

}
