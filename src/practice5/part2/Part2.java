package practice5.part2;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * Задание 2
 *
 * Создать класс Spam, который получает массив интервалов времени
 * в миллисекундах и согласованный с ним массив сообщений, и выводит
 * соответствующие сообщения на экран через заданные интервалы времени.
 * По нажатию на клавишу Enter приложение должно завершать свою работу.
 * При демонстрации работы смоделировать ввод Enter через 5 сек.
 */
public class Part2 {

	public static void main(String[] args) {
		try {
			runSpam(3000);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void runSpam(long millis) throws InterruptedException {
		// save standard input
		InputStream stdIn = System.in;

		// create input stream with line terminator (=ENTER)
		ByteArrayInputStream bais = new ByteArrayInputStream(System.lineSeparator().getBytes());

		// move internal pointer of input stream to the end of input
		bais.skip(System.lineSeparator().length());

		// assign new value of standard input
		System.setIn(bais);

		// main functionality
		Thread thread = Spam.initialize();
		Thread.sleep(millis);

		// move internal pointer to begin of input
		bais.reset();

		// wait for a child termination
		thread.join();

		// restore standard input
		System.setIn(stdIn);
	}

}
