package practice3;

import java.io.*;
import java.util.Scanner;

public class Util {
	public static String getInput(String fileName) {
		StringBuilder sb = new StringBuilder();
		Scanner scanner = null;
		try {
			scanner = new Scanner(new File(fileName), "Cp1251");
			while (scanner.hasNextLine()) {
				sb.append(scanner.nextLine()).append(System.lineSeparator());
			}
			return sb.toString().trim();
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		} finally {
			if (scanner != null) {
				scanner.close();
			}
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		String input = Util.getInput("src/practice3/part1.txt");
		System.out.println(input);
	}
}
