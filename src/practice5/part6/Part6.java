package practice5.part6;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

/**
 * Задание 6
 *
 * Создать десять потоков, которые одновременно пишут в один и тот же файл
 * цифры:
 *
 * первый поток пишет цифру 0 ровно 20 раз на 1й строке файла; второй поток
 * пишет цифру 1 ровно 20 раз на 2й строке файла; ... девятый поток пишет цифру
 * 9 ровно 20 раз на 10-й строке файла.
 *
 */
public class Part6 extends Thread {

	private static final Object MONITOR = new Object();

	private static final int THREADS_NUMBER = 10;

	private static final int COLUMNS = 20;

	private static final int EOL_LENGTH = System.lineSeparator().length();

	private static final String FILE_NAME = "src/practice5/test.txt";

	private static final RandomAccessFile OUT;

	private static final Thread[] THREADS;

	private final int number;

	private final int rowNumber;

	static {
		OUT = initRandomAccessFile();
		THREADS = new Thread[THREADS_NUMBER];
	}

	public Part6(int number, int rowNumber) {
		this.number = number;
		this.rowNumber = rowNumber;
	}

	public static void main(String[] args) {
		try {
			writeFromThreads();
			read();
		} catch (InterruptedException | IOException e) {
			System.out.println(e.getMessage());
		}

	}

	public static RandomAccessFile initRandomAccessFile() {
		File file;
		RandomAccessFile out = null;
		try {
			file = new File(FILE_NAME);
			if (file.exists() && !file.delete()) {
				throw new IOException("The file is not deleted!");
			}
			if (!file.createNewFile()) {
				throw new IOException("The named file already exists!");
			}
			out = new RandomAccessFile(file, "rw");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return out;
	}

	public static void writeFromThreads() throws InterruptedException, IOException {
		int lineLength = COLUMNS + EOL_LENGTH;
		int offset = 0;
		for (int i = 0; i < THREADS_NUMBER; i++) {
			Thread thread = new Part6(i, offset);
			thread.start();
			THREADS[i] = thread;
			offset += lineLength;
		}
		for (Thread thread : THREADS) {
			thread.join();
		}
		OUT.close();
	}

	public static void read() throws IOException {
		String content;
		StringBuilder sb = new StringBuilder();
		Scanner scanner = new Scanner(new File(FILE_NAME), "Cp1251");
		while (scanner.hasNextLine()) {
			sb.append(scanner.nextLine()).append(System.lineSeparator());
		}
		content = sb.substring(0, sb.length() - System.lineSeparator().length());
		scanner.close();
		System.out.println(content);
	}

	@Override
	public void run() {
		try {
			writeNumbers();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public void writeNumbers() throws IOException {
		synchronized (MONITOR) {
			OUT.seek(rowNumber);
			for (int i = 0; i < COLUMNS; i++) {
				OUT.write('0' + number);
			}
			OUT.write(System.lineSeparator().getBytes());
		}
	}
}
