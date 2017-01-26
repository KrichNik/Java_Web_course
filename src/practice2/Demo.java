package practice2;

import java.util.Arrays;
import java.util.Iterator;

/**
 * Practice 2
 *
 * @author Nikita Datsenko
 * @see MyList
 * @see ListIterable
 * @see ListIterator
 * @see MyListImpl
 *
 * 21.10.16
 */
public class Demo {

	public static void main(String[] args) {

		/*
		 * Task 1:
		 */
		System.out.println("Part1:");
		MyList list = new MyListImpl();
		list.add("A");
		list.add("B");
		list.add(433);
		list.add(888);
		list.add(new Object());
		list.add("A");
		list.add("B");
		list.add(433);
		list.add(888);
		list.add(null);
		System.out.println(list.toString());
		System.out.println(list.contains(433));
		System.out.println(list.containsAll(list));
		System.out.println(list.size());
		System.out.println(list.remove(null));
		System.out.println(list.remove(433));
		System.out.println(list.toString());
		System.out.println(list.contains(null));
		System.out.println(Arrays.toString(list.toArray()));
		list.clear();
		System.out.println(list.toString());

		/*
		 * Task 2:
		 */
		System.out.println("\nPart2:");
		list.add("A");
		list.add("B");
		list.add(433);
		list.add(888);
		list.add(new Object());
		list.add("A");
		list.add("B");
		list.add(433);
		list.add(888);
		list.add(null);

		for (Object o : list) {
			System.out.print(o + " ");
		}
		System.out.println();

		Iterator<Object> iter = list.iterator();
		iter.next();
		iter.remove();
		while (iter.hasNext()) {
			System.out.print(iter.next() + " ");
		}
		System.out.println();

		/*
		 * Task 3:
		 */
		System.out.println("\nPart3:");
		MyListImpl myList = new MyListImpl();
		myList.add("A");
		myList.add("B");
		myList.add("C");
		myList.add("D");
		myList.add(433);
		myList.add(888);
		myList.add(new Object());
		myList.add(null);
		myList.add(633);
		myList.add(788);

		ListIterator listIter = myList.listIterator();
		while (listIter.hasNext()) {
			System.out.print(listIter.next() + " ");
		}
		System.out.println();

		while (listIter.hasPrevious()) {
			System.out.print(listIter.previous() + " ");
		}
	}

}
