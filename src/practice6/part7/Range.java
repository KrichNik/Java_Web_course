package practice6.part7;

import java.util.Iterator;

public class Range implements Iterable<Integer> {

	private int[] arr;

	private boolean reverse;

	public Range(int n, int m, boolean reverse) {
		if (n >= m) {
			throw new IllegalArgumentException("Invalid value!");
		}
		this.reverse = reverse;
		arr = fillArr(n, m);
	}

	@Override
	public Iterator<Integer> iterator() {
		return new IteratorImpl();
	}

	private static int[] fillArr(int n, int m) {
		int length = m - n + 1;
		int[] arr = new int[length];
		for (int i = 0; i < length; i++) {
			arr[i] = i + n;
		}
		return arr;
	}

	public class IteratorImpl implements Iterator<Integer> {

		private int cursor = 0;

		public IteratorImpl() {
			if (!reverse) {
				cursor = arr.length - 1;
			}
		}

		@Override
		public boolean hasNext() {
			if (reverse) {
				return cursor != arr.length;
			} else {
				return cursor != -1;
			}
		}

		@Override
		public Integer next() {
			if (cursor >= arr.length || cursor < 0) {
				throw new IllegalStateException("No element!");
			}
			if (reverse) {
				return arr[cursor++];
			} else {
				return arr[cursor--];
			}
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}

	}

}
