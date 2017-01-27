package practice4.part2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Random;
import java.util.Scanner;

/**
 * Задание 2
 *
 * Создать класс, который создает и заполняет файл случайными целыми числами от
 * 0 до 50 (всего 10 чисел), затем читает файл и выводит его содержимое в другой
 * файл, отсортировав числа по возрастанию. Содержимое обоих файлов (числа
 * разделенные пробелом) вывести в консоль.
 *
 * @author Nikita Datsenko
 *
 */
public class Part2 {

	public static void main(String[] args) throws IOException {

		writeToFile(generateNumbers(), "src/practice4/file2input.txt");
		int[] arrInput = readFromFile("src/practice4/file2input.txt");

		System.out.print("input ==> ");
		printArr(arrInput);

		writeToFile(bubbleSort(arrInput), "src/practice4/file2output.txt");
		int[] arrOutput = readFromFile("src/practice4/file2output.txt");

		System.out.print("output ==> ");
		printArr(arrOutput);

	}

	static int[] readFromFile(final String fileName) throws FileNotFoundException {
		int[] arrNumbers = new int[10];
		Scanner s = new Scanner(new File(fileName), "UTF-8");
		int counter = 0;
		while (s.hasNextInt()) {
			arrNumbers[counter++] = s.nextInt();
		}
		s.close();
		return arrNumbers;
	}

	static void writeToFile(final int[] numbers, final String fileName) throws IOException {
		FileOutputStream fos = new FileOutputStream(fileName);
		Writer writer = new OutputStreamWriter(fos, "UTF-8");
		for (int i : numbers) {
			writer.write(String.valueOf(i));
			writer.write(' ');
		}
		writer.close();
	}

	static int[] generateNumbers() {
		int[] arrNumbers = new int[10];
		Random random = new Random();
		for (int i = 0; i < 10; i++) {
			arrNumbers[i] = random.nextInt(50);
		}
		return arrNumbers;
	}

	public static int[] bubbleSort(final int[] values) {
		int[] arr = values;
		for (int i = arr.length - 1; i > 0; i--) {
			for (int j = 0; j < i; j++) {
				if (arr[j] > arr[j + 1]) {
					int tmp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = tmp;
				}
			}
		}
		return arr;
	}

	static void printArr(int[] arr) {
		for (int i : arr) {
			System.out.print(i + " ");
		}
		System.out.println();
	}
}
