package practice4.part1;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import practice4.Util;

/**
 * Задание 1.
 *
 * Создать класс, который выводит содержимое текстового файла в консоль, заменяя
 * в каждом слове длиннее трех символов все строчные символы (нижний регистр)
 * прописными (верхний регистр). При решении задачи использовать регулярные
 * выражения.
 *
 * @author Nikita Datsenko
 *
 */
public class Part1 {

	private static final String FILE_NAME = "src/practice4/file1.txt";

	public static void main(String[] args) {
		String input = Util.readFromFile(FILE_NAME);
		String result = change(input);
		System.out.println(result);
	}

	static String change(String value) {
		String regex = "([\\S]{3,})";
		StringBuffer sb = new StringBuffer();
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(value);
		while (m.find()) {
			m.appendReplacement(sb, m.group().toUpperCase());
		}
		m.appendTail(sb);
		return sb.toString();
	}

}
