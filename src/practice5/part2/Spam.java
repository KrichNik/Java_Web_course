package practice5.part2;

import java.io.IOException;

public class Spam {

	private long[] timeInrervals;

	private String[] messages;

	private Thread[] threads;

	public Spam(long[] timeInrervals, String[] messages) {
		this.timeInrervals = timeInrervals.clone();
		this.messages = messages.clone();
		threads = new Thread[timeInrervals.length];
	}

	public static void main(String[] args) {
		initialize();
	}

	public static Thread initialize() {
		long[] timeInrervals = { 500, 300, 1000, 600, 300 };
		String[] messages = { "mes1", "mes2", "mes3", "mes4", "mes5" };
		final Spam spam = new Spam(timeInrervals, messages);
		spam.start();
		Thread thread = new Thread() {
			public void run() {
				byte[] buffer = new byte[10];
				int count;
				try {
					do {
						while ((count = System.in.read(buffer)) == -1)
							{;}
					} while (!System.lineSeparator().equals(new String(buffer, 0, count)));
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
				System.out.println("ENTER has been obtained");
				System.out.println("Notify a child thread to terminate");
				spam.stop();
			}
		};
		thread.start();
		return thread;
	}

	public void start() {
		for (int i = 0; i < timeInrervals.length; i++) {
			Thread thread = new SpamThread(timeInrervals[i], messages[i]);
			thread.start();
			threads[i] = thread;
		}
	}

	public void stop() {
		for (int i = 0; i < timeInrervals.length; i++) {
			threads[i].interrupt();
		}
	}

	private static class SpamThread extends Thread {
		private long timeInrerval;

		private String message;

		public SpamThread(long timeInrerval, String message) {
			this.timeInrerval = timeInrerval;
			this.message = message;
		}

		@Override
		public void run() {
			while (!isInterrupted()) {
				try {
					System.out.println(message);
					Thread.sleep(timeInrerval);
				} catch (InterruptedException ex) {
					break;
				}
			}
		}
	}

}
