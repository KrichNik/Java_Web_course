package practice2;

import java.util.Iterator;

/**
 * Allows to traverse the list in either direction.
 *
 * @see Iterator
 * @see MyListImpl
 * @see ListIterator
 */
interface ListIterator extends Iterator<Object> {
	/**
	 * @return true if this list iterator has more elements when traversing the
	 *         list in the reverse direction.
	 */
	boolean hasPrevious();

	/**
	 * @return the previous element in the list and moves the cursor position
	 *         backwards.
	 */
	Object previous();

	/**
	 * Replaces the last element returned by next or previous with the specified
	 * element.
	 *
	 * @param e
	 *            specified element.
	 */
	void set(Object e);

	/**
	 * Removes from the list the last element that was returned by next or
	 * previous.
	 */
	void remove();
}
