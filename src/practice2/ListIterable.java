package practice2;

/**
 * Allows to get ListIterator.
 *
 * @see MyListImpl
 * @see ListIterator
 */
interface ListIterable {

	/**
	 * @return instance of the ListIterator.
	 */
	ListIterator listIterator();
}
