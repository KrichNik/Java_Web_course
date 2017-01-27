package practice5.part3;

/**
 * Задание 3
 *
 * Создать класс с двумя отдельными счетчиками и объект этого класса. Создать
 * несколько одинаковых потоков, каждый из которых повторяет следующее:
 * сравнивает значение счетчиков и печатает результат сравнения, увеличивает
 * первый счетчик, засыпает на 10 мсек, а затем увеличивает второй счетчик.
 * Сравнить работу программы при условии, что упомянутый блок кода
 * синхронизирован и не синхронизирован.
 *
 */
public class Part3 {

	private static final Counter COUNTER = new Counter();

	private static final Thread[] THREADS = new Thread[10];

	public static void main(String[] args) throws InterruptedException {
		System.out.println("Wiht synchronized:");
		for (int i = 0; i < 10; i++) {
			Thread thread = new Thread(new CounterThread(COUNTER, true));
			thread.start();
		}

		Thread.sleep(1000);

		System.out.println("Without synchronized:");
		for (int i = 0; i < 10; i++) {
			Thread thread = new Thread(new CounterThread(COUNTER, false));
			thread.start();
			THREADS[i] = thread;
		}

		for (Thread thread:THREADS) {
			thread.join();
		}
	}

}
