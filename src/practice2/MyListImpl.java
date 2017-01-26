package practice2;

import java.util.Arrays;
import java.util.Iterator;

/**
 * MyListImpl is expandable container based on array.
 *
 * @author Nikita Datsenko
 * @see MyList
 * @see ListIterable
 * @see ListIterator
 */
public class MyListImpl implements MyList, ListIterable {
	/**
	 * Array to store all elements of list.
	 */
	private Object[] elementData;

	/**
	 * Default capacity of the list.
	 */
	private static final int DEFAULT_CAPACITY = 10;

	/**
	 * Number of all elements, that contains in list.
	 */
	private int size;

	/**
	 * Constructs an empty list with an initial capacity of ten.
	 */
	public MyListImpl() {
		this.elementData = new Object[DEFAULT_CAPACITY];
	}

	/**
	 * Constructs an empty list with the specified initial capacity.
	 */
	public MyListImpl(int capacity) {
		if (capacity >= 0) {
			elementData = new Object[capacity];
		} else {
			throw new IllegalArgumentException("Недопустимая емкость коллекции.");
		}
	}

	/**
	 * Add the specified element to the end of this list.
	 */
	@Override
	public void add(Object e) {
		int indexOfFreeCell = size;
		ensureCapacity(++size);
		elementData[indexOfFreeCell] = e;
	}

	/**
	 * Set specified element to specified index of list.
	 */
	public void set(int index, Object element) {
		elementData[index] = element;
	}

	/**
	 * Removes all of the elements from this list.
	 */
	@Override
	public void clear() {
		for (int i = 0; i < size; i++) {
			elementData[i] = null;
		}
		size = 0;
	}

	/**
	 * Removes the first occurrence of the specified element from this list.
	 *
	 * @param o
	 *            element to remove from list.
	 * @return true if specified element has been removed.
	 */
	@Override
	public boolean remove(Object o) {
		if (o == null) {
			for (int index = 0; index < size; index++) {
				if (elementData[index] == null) {
					fastRemove(index);
					return true;
				}
			}
		}
		for (int index = 0; index < size; index++) {
			if (o.equals(elementData[index])) {
				fastRemove(index);
				return true;
			}
		}
		return false;
	}

	/**
	 * @return an array containing all of the elements in this list in proper
	 *         sequence.
	 */
	@Override
	public Object[] toArray() {
		return Arrays.copyOf(elementData, size);
	}

	/**
	 * @return the number of elements in this list.
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Returns true if this list contains the specified element.
	 */
	@Override
	public boolean contains(Object o) {
		if (o == null) {
			for (int index = 0; index < size; index++) {
				if (elementData[index] == null) {
					return true;
				}
			}
		} else {
			for (int index = 0; index < size; index++) {
				if (o.equals(elementData[index])) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Returns true if this list contains all of the elements of the specified
	 * list.
	 */
	@Override
	public boolean containsAll(MyList c) {
		Object[] arrayToCompare = c.toArray();
		for (int i = 0; i < arrayToCompare.length; i++) {
			if (!contains(arrayToCompare[i])) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Print all of contain elements format {[elem1],[elem2],...,[elemN]}
	 */
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder("{");
		for (int i = 0; i < size; i++) {
			result.append("[");
			if (elementData[i] == null) {
				result.append("null");
			} else {
				result.append(elementData[i].toString());
			}
			result.append("]");
		}
		result.append("}");
		return result.toString();
	}

	/**
	 * @return instance of the IteratorImpl.
	 */
	@Override
	public Iterator<Object> iterator() {
		return new IteratorImpl();
	}

	/**
	 * @return instance of the ListIterator.
	 */
	@Override
	public ListIterator listIterator() {
		return new ListIteratorImpl();
	}

	/**
	 * Increases the capacity of this list instance.
	 *
	 * @param minCapacity
	 *            necessary capacity.
	 */
	private void ensureCapacity(final int minCapacity) {
		int currentCapacity = elementData.length;
		int newCapacity;
		if (currentCapacity < minCapacity) {
			currentCapacity = minCapacity;
			newCapacity = ((currentCapacity * 3) / 2) + 1;
			elementData = Arrays.copyOf(elementData, newCapacity);
		}
	}

	/**
	 * Remove the index element from list.
	 *
	 * @param index
	 */
	private void fastRemove(final int index) {
		int moved = size - index - 1;
		if (moved > 0) {
			System.arraycopy(elementData, index + 1, elementData, index, moved);
		}
		elementData[--size] = null;
	}

	private class IteratorImpl implements Iterator<Object> {
		private int cursor = 0;

		private boolean isRemove = true;

		/**
		 * Returns true if the iteration has more elements.
		 */
		@Override
		public boolean hasNext() {
			return cursor != size - 1;
		}

		/**
		 * Returns the next element in the iteration.
		 */
		@Override
		public Object next() {
			if (cursor >= size || cursor < 0) {
				throw new IllegalStateException("Элемента нет!");
			}
			isRemove = false;
			return elementData[cursor++];
		}

		/**
		 * Removes from the underlying collection the last element returned by
		 * this iterator.
		 */
		@Override
		public void remove() {
			if (cursor == 0 || isRemove) {
				throw new IllegalStateException("Невозможно удалить элемент!");
			}
			MyListImpl.this.fastRemove(cursor - 1);
			isRemove = true;
		}
	}

	private class ListIteratorImpl extends IteratorImpl implements ListIterator {
		@Override
		public boolean hasPrevious() {
			return super.cursor != 0;
		}

		@Override
		public Object previous() {
			int i = super.cursor - 1;
			if (i < 0) {
				throw new IllegalStateException();
			}
			if (i >= MyListImpl.this.elementData.length) {
				throw new IllegalStateException();
			}
			super.cursor = i;
			return MyListImpl.this.elementData[i];
		}

		@Override
		public void set(Object e) {
			MyListImpl.this.set(super.cursor, e);
		}

		@Override
		public void remove() {
			MyListImpl.this.fastRemove(super.cursor);
		}
	}
}
