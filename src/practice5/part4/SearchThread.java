package practice5.part4;

public class SearchThread extends Thread {

	private final int columnNumber;

	private final int[][] matrix;

	private static int maxValue;

	public SearchThread(int columnNumber, int[][] matrix) {
		this.columnNumber = columnNumber;
		this.matrix = matrix.clone();
	}

	public static int getMaxValue() {
		return maxValue;
	}

	@Override
	public void run() {
		int max = 0;
		int current;
		try {
			for (int i = 0; i < matrix[columnNumber].length; i++) {
				current = matrix[columnNumber][i];
				if (current > max) {
					max = current;
				}
			}
			synchronized (SearchThread.class) {
				Thread.sleep(1);
				if (max > maxValue) {
					maxValue = max;
				}
			}
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
	}
}
