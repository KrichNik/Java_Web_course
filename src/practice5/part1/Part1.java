package practice5.part1;

/**
 * Задание 1
 *
 * Создать дочерний поток, который бы в течение примерно 5 сек печатал
 * свое имя каждые полсекунды. Сделать это двумя способами - при помощи
 * расширения класса Thread и при помощи реализации интерфейса Runnable.
 */
public class Part1 {

	public static final int COUNT = 10;

	public static final int MILLIS = 500;

	public static void main(String[] args) throws InterruptedException {
		Thread thread1 = new MyThread();
		thread1.start();

		Thread thread2 = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < COUNT; i++) {
					System.out.println("myThread2:::" + Thread.currentThread().getName());
					try {
						Thread.sleep(MILLIS);
					} catch (InterruptedException e) {
						System.out.println(e.getMessage());
					}
				}
			}
		});
		thread2.start();

		thread1.join();
		thread2.join();

	}
}
