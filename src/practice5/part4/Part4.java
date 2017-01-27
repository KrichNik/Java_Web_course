package practice5.part4;

import java.util.Date;
import java.util.Random;

/**
 * Задание 4
 *
 * Распараллелить задачу поиска максимального значения в матрице MxN при помощи
 * M потоков. Каждую операцию сравнения снабдить задержкой в 1 мс. Создать и
 * заполнить матрицу 4x100 случайными числами. Дополнительно решить задачу
 * поиска максимального значения без распараллеливания. Вывести результат и
 * время выполнения кода для обоих вариантов.
 *
 */
public class Part4 {

	public static final int COLUMN = 4;

	public static final int ROW = 100;

	private static final int[][] MATRIX = new int[COLUMN][ROW];

	private static final Random RANDOM = new Random();

	private static final SearchThread[] THREADS = new SearchThread[COLUMN];

	public static void main(String[] args) {
		fillMatrix();
		System.out.println("MultiThread result:");
		multiSearch();
		System.out.println();
		System.out.println("Single-Thread result:");
		singleSearch();
	}

	public static void fillMatrix() {
		for (int i = 0; i < MATRIX.length; i++) {
			for (int j = 0; j < MATRIX[i].length; j++) {
				MATRIX[i][j] = RANDOM.nextInt();
			}
		}
	}

	public static void printMatrix() {
		for (int i = 0; i < MATRIX.length; i++) {
			for (int j = 0; j < MATRIX[i].length; j++) {
				System.out.print(MATRIX[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static void start() {
		for (int i = 0; i < THREADS.length; i++) {
			SearchThread thread = new SearchThread(i, MATRIX);
			thread.start();
			THREADS[i] = thread;
		}
	}

	public static void joins() {
		try {
			for (int i = 0; i < THREADS.length; i++) {
				THREADS[i].join();
			}
			System.out.println("Max value: " + SearchThread.getMaxValue());
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void multiSearch() {
		long afterTime = new Date().getTime();
		start();
		joins();
		long beforeTime = new Date().getTime();
		long resultTime = beforeTime - afterTime;
		System.out.println("time: " + resultTime + " ms");
	}

	public static void singleSearch() {
		long afterTime = new Date().getTime();
		int maxValue = 0;
		int current;
		for (int i = 0; i < MATRIX.length; i++) {
			for (int j = 0; j < MATRIX[i].length; j++) {
				current = MATRIX[i][j];
				if (current > maxValue) {
					maxValue = current;
				}
			}
		}
		System.out.println("Max value: " + maxValue);
		long beforeTime = new Date().getTime();
		long resultTime = beforeTime - afterTime;
		System.out.println("time: " + resultTime + " ms");
	}
}
