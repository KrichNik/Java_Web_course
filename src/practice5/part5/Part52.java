package practice5.part5;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Part52 {

	private static final int ITERATION_NUMBER = 3;

	private static final int READERS_NUMBER = 3;

	// shared resource (not thread-safe!!!)
	private static final StringBuilder BUFFER = new StringBuilder();

	private static final int BUFFER_LENGTH = 5;

	// speed parameter
	private static final int PAUSE = 5;

	// stop signal
	private static volatile boolean stop;

	private static final Lock LOCK = new ReentrantLock();

	private static final Condition COND1 = LOCK.newCondition();

	private static final Condition COND2 = LOCK.newCondition();

	private static int readersCounter;

	// reader
	private static class Reader extends Thread {
		public void run() {
			while (!stop) {
				LOCK.lock();
				try {
					if (++readersCounter == READERS_NUMBER) {
						// 4. wait until all the readers will read
						// and wake up writer by the latest stream
						COND2.signal();
						readersCounter = 0;
					}
					// 1. readers wait until the writer writes
					COND1.await();
					if (!stop) {
						// read from the buffer
						read(getName());
					}
				} catch (InterruptedException e) {
					System.out.println(e.getMessage());
				} finally {
					LOCK.unlock();
				}
			}
		}
	}

	private static void read(String threadName) throws InterruptedException {
		System.out.printf("Reader %s:", threadName);
		for (int j = 0; j < BUFFER_LENGTH; j++) {
			Thread.sleep(PAUSE);
			System.out.print(BUFFER.charAt(j));
		}
		System.out.println();
		Thread.sleep(5);
	}

	// writer
	private static class Writer extends Thread {
		public void run() {
			int tact = 0;
			while (!stop) {
				LOCK.lock();
				try {
					// write to the buffer
					write();
					// 2. wake up readers
					COND1.signalAll();
					// 3. wait until all readers read
					COND2.await();
					Thread.sleep(PAUSE);
				} catch (InterruptedException e) {
					System.out.println(e.getMessage());
				} finally {
					if (++tact == ITERATION_NUMBER) {
						stop = true;
						// 5. when the writer has finished, wake up readers
						COND1.signalAll();
					}
					LOCK.unlock();
				}
			}

		}
	}

	private static void write() throws InterruptedException {
		// clear buffer
		BUFFER.setLength(0);

		// write to buffer
		System.err.print("Writer writes:");

		Random random = new Random();
		for (int j = 0; j < BUFFER_LENGTH; j++) {
			Thread.sleep(PAUSE);
			char ch = (char) ('A' + random.nextInt(26));
			System.err.print(ch);
			BUFFER.append(ch);
		}
		System.err.println();
		Thread.sleep(5);
	}

	public static void main(String[] args) throws InterruptedException {
		// create writer
		Writer writer = new Writer();

		// create readers
		List<Thread> readers = new ArrayList<>();
		for (int j = 0; j < READERS_NUMBER; j++) {
			readers.add(new Reader());
		}

		// start readers
		Thread.sleep(10);
		for (Thread reader : readers) {
			reader.start();
		}

		// start writer
		Thread.sleep(10);
		writer.start();

		// main thread is waiting for the child threads
		writer.join();
		for (Thread reader : readers) {
			reader.join();
		}

		//to work correctly when re-call main()
		stop = false;
	}
}
