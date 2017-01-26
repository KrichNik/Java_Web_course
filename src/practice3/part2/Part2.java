package practice3.part2;

import practice3.Util;

import java.util.HashSet;
import java.util.Set;

public class Part2 {

	private static final String EOL = System.lineSeparator();

	public static void main(String[] args) {
		String input = Util.getInput("src/practice3/part2.txt");
		input = input.replaceAll(",", "").replaceAll("[^\\w^\\s]|" + EOL, " ");
		String[] arrSplit = input.split("\\s");
		int minLength = Integer.MAX_VALUE;
		int maxLength = 0;
		for (String string : arrSplit) {
			if (string.length() > maxLength) {
				maxLength = string.length();
			}
			if (string.length() < minLength) {
				minLength = string.length();
			}
		}
		Set<String> stringsWithMinLength = new HashSet<>();
		Set<String> stringsWithMaxLength = new HashSet<>();
		for (String string : arrSplit) {
			if (string.length() == minLength) {
				stringsWithMinLength.add(string);
			}
			if (string.length() == maxLength) {
				stringsWithMaxLength.add(string);
			}
		}
		System.out.print("Min: ");
		printSet(stringsWithMinLength);
		System.out.println();
		System.out.print("Max: ");
		printSet(stringsWithMaxLength);
		System.out.println();
	}

	static void printSet(Set<String> set) {
		StringBuilder sb = new StringBuilder();
		for (String string : set) {
			sb.append(string).append(", ");
		}
		sb.deleteCharAt(sb.length() - 2);
		System.out.print(sb);
	}

}
