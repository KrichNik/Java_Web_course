package practice5.part1;

public class MyThread extends Thread {

	@Override
	public void run() {
		for (int i = 0; i < Part1.COUNT; i++) {
			System.out.println("myThread1:::" + this.getName());
			try {
				Thread.sleep(Part1.MILLIS);
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
			}
		}
	}
}
