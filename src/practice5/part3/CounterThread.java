package practice5.part3;

public class CounterThread implements Runnable {

	private Counter counter;

	private boolean isSync;

	public CounterThread(Counter counter, boolean isSync) {
		this.counter = counter;
		this.isSync = isSync;
	}

	@Override
	public void run() {
		for (int i = 0; i < 5; i++) {
			try {
				if (isSync) {
					runWithSync();
				} else {
					runWithoutSync();
				}
			} catch (InterruptedException ex) {
				break;
			}
		}
	}

	public void runWithSync() throws InterruptedException {
		boolean result;
		String threadName = Thread.currentThread().getName();
		synchronized (counter) {
			result = counter.getCounter1() == counter.getCounter2();
			System.out.println(threadName + ":::compare result - " + result);
			counter.increment1();
			Thread.sleep(10);
			counter.increment2();
		}
	}

	public void runWithoutSync() throws InterruptedException {
		boolean result;
		String threadName = Thread.currentThread().getName();
		result = counter.getCounter1() == counter.getCounter2();
		System.out.println(threadName + ":::compare result - " + result);
		counter.increment1();
		Thread.sleep(10);
		counter.increment2();
	}

}
