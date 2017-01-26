package practice3.part1;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import practice3.Util;

public class Part1 {

	private static final String FILE_NAME = "src/practice3/part1.txt";

	private static final String EOL = System.lineSeparator();

	public static void main(String[] args) {
		print();
	}

	public static void print() {
		System.out.println("convert1:");
		System.out.println(convert1(Util.getInput(FILE_NAME)));
		System.out.println("convert2:");
		System.out.println(convert2(Util.getInput(FILE_NAME)));
		System.out.println("convert3:");
		System.out.println(convert3(Util.getInput(FILE_NAME)));
		System.out.println("convert4:");
		System.out.println(convert4(Util.getInput(FILE_NAME)));
	}

	public static String convert1(final String input) {
		String[] arrSplit = input.split(EOL);
		StringBuilder sb = new StringBuilder();
		String regex = "(\\p{Lower}+);(.+\\s.+);([\\w]+@[\\S]+$)";
		Pattern p = Pattern.compile(regex);
		for (String value : arrSplit) {
			Matcher m = p.matcher(value);
			if (m.matches()) {
				sb.append(m.replaceAll("$1 ==> $3")).append(EOL);
			}
		}
		return sb.toString();
	}

	public static String convert2(final String input) {
		String[] arrSplit = input.split(EOL);
		StringBuilder sb = new StringBuilder();
		String regex = "\\p{Lower}+;(.+)\\s(.+);(.+$)";
		Pattern p = Pattern.compile(regex);
		for (String value : arrSplit) {
			Matcher m = p.matcher(value);
			if (m.matches()) {
				sb.append(m.replaceAll("$2 $1 (email: $3)")).append(EOL);
			}
		}
		return sb.toString();
	}

	public static String convert3(final String input) {
		StringBuilder sb = new StringBuilder();
		String[] arrSplit = input.split(EOL);
		for (String domain : findDomains(arrSplit)) {
			sb.append(domain).append(" ==> ");
			boolean isFirst = false;
			for (int i = 0; i < arrSplit.length; i++) {
				String regex = "(\\p{Lower}+);.+\\s.+;[\\w]+@(" + domain + ")$";
				Pattern p = Pattern.compile(regex);
				Matcher m = p.matcher(arrSplit[i]);
				if (m.matches()) {
					if (isFirst) {
						sb.append(", ");
					}
					sb.append(m.group(1));
					isFirst = true;
				}
			}
			sb.append(EOL);
		}
		return sb.toString();
	}

	private static Set<String> findDomains(final String[] arrSplit) {
		String regex = "(\\p{Lower}+);.+\\s.+;[\\w]+@([\\S]+)$";
		Pattern p = Pattern.compile(regex);
		Set<String> domains = new HashSet<>();
		for (String value : arrSplit) {
			Matcher m = p.matcher(value);
			if (m.matches()) {
				domains.add(m.group(2));
			}
		}
		return domains;
	}

	public static String convert4(final String input) {
		Random random = new Random();
		StringBuilder result = new StringBuilder("");
		String[] arrInput = input.split(EOL);
		for (int i = 0; i < arrInput.length; i++) {
			if (i == 0) {
				result.append(arrInput[i]).append(";Password").append(EOL);
			} else {
				int number = random.nextInt(8999) + 1000;
				result.append(arrInput[i]).append(";").append(number).append(EOL);
			}
		}
		return result.toString();
	}
}
