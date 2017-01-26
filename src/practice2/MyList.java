package practice2;

/**
 *
 * @see Iterable
 * @see MyListImpl
 */
public interface MyList extends Iterable<Object> {
	/**
	 * Appends the specified element to the end of this list.
	 *
	 * @param e
	 *            specified element.
	 */
	void add(Object e);

	/**
	 * Removes all of the elements from this list.
	 */
	void clear();

	/**
	 * Removes the first occurrence of the specified element from this list.
	 *
	 * @param o
	 *            element to remove from list.
	 * @return true if specified element has been removed.
	 */
	boolean remove(Object o);

	/**
	 * @return an array containing all of the elements in this list in proper
	 *         sequence.
	 */
	Object[] toArray();

	/**
	 * @return the number of elements in this list.
	 */
	int size();

	/**
	 * Returns true if this list contains the specified element.
	 *
	 * @param o
	 *            specified element.
	 * @return true if this list contains the specified element.
	 */
	boolean contains(Object o);

	/**
	 * Returns true if this list contains all of the elements of the specified
	 * list
	 *
	 * @param c
	 *            specified list.
	 * @return returns true if this list contains all of the elements of the
	 *         specified list
	 */
	boolean containsAll(MyList c);
}
