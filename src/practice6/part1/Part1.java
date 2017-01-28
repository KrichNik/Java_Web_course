package practice6.part1;

import java.util.Scanner;

/**
 *
 * Разработать приложение, которое считывает текст из консольного ввода и
 * выводит слова в порядке убывания их частоты появления в тексте.
 *
 * 23.11.16
 *
 * @author Nikita Datsenko
 *
 */
public class Part1 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in, "UTF-8");
		System.out.println("Input string: ");
		String input = scanner.nextLine();
		System.out.print("==> " + input);
		String[] arrSplit = input.split("\\s");

		WordContainer wordContainer = new WordContainer();
		for (String value : arrSplit) {
			wordContainer.addWord(new Word(value));
		}
		wordContainer.sort();

		System.out.println();
		System.out.println("Output:");
		wordContainer.printWords();

		scanner.close();
	}

}
