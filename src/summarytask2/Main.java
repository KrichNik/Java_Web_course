package summarytask2;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * The main application class that contains the entry point into the program.
 * The application allows you to swap the first and last word in the text.
 *
 * @see Util
 * @author Nikita Datsenko
 *
 */
public class Main {

	private static final String REGEX_FIND_FIRST_LAST_WORD = "[^\u0430-\u044F\u0410-\u042F\u0451\u0401a-zA-Z]*"
			+ "([\u0430-\u044F\u0410-\u042F\u0451\u0401a-zA-Z]+)"
			+ "([^\\.]*\\s)([\u0430-\u044F\u0410-\u042F\u0451\u0401a-zA-Z]+)([^\\.]*\\.\\s*)|"
			+ "(\\s*[\u0430-\u044F\u0410-\u042F\u0451\u0401a-zA-Z]+\\.)";

	private static final String REGEX_FIND_SPACES = "[ \\t]{2,}";

	public static void main(String[] args) {

		String fileName = args[0];

		Util util = new Util(fileName);
		String inputFromFile = util.readFromFile();

		inputFromFile = removeSpaceSequence(inputFromFile);

		System.out.println("input length: " + inputFromFile.length());
		System.out.println();
		System.out.println(inputFromFile);
		System.out.println("----------------------------------------");

		String result = replace(inputFromFile);

		System.out.println(result);
		System.out.println();
		System.out.println("output length: " + result.length());
	}

	public static String replace(final String input) {
		StringBuilder sb = new StringBuilder();
		String regex = REGEX_FIND_FIRST_LAST_WORD;
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(input);
		while (m.find()) {
			sb.append(m.replaceAll("$3$2$1$4$5"));
		}
		return sb.toString().trim();
	}

	public static String removeSpaceSequence(final String input) {
		String regex = REGEX_FIND_SPACES;
		String space = " ";
		StringBuffer sb = new StringBuffer();
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(input);
		while (m.find()) {
			m.appendReplacement(sb, space);
		}
		m.appendTail(sb);
		return sb.toString().trim();
	}

}
