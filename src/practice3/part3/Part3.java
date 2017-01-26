package practice3.part3;

import practice3.Util;

public class Part3 {

	private static final String EOL = System.lineSeparator();

	public static void main(String[] args) {
		String input = Util.getInput("src/practice3/part3.txt");
		String[] arrSplit = input.split("\\s");
		String s;
		char c;
		for (int i = 0; i < arrSplit.length; i++) {
			s = arrSplit[i];
			if (s.length() != 0) {
				c = s.charAt(0);
				s = s.replace(c, Character.toUpperCase(c));
				arrSplit[i] = s + " ";
			} else {
				arrSplit[i] = EOL;
			}

		}
		for (String str : arrSplit) {
			System.out.print(str);
		}
		System.out.println();
	}
}
