package practice6.part7;

import java.util.Iterator;

/**
 *
 *
 * 01.12.16
 *
 * @author Nikita Datsenko
 *
 */
public class Part7 {

	public static void main(String[] args) {
		System.out.println("Reverse true:");
		Range range = new Range(3, 10, true);
		Iterator<Integer> iter = range.iterator();
		while (iter.hasNext()) {
			System.out.print(iter.next());
			if (iter.hasNext()) {
				System.out.print(", ");
			}
		}
		System.out.println(System.lineSeparator());

		System.out.println("Reverse false:");
		Range range2 = new Range(3, 10, false);
		Iterator<Integer> iter2 = range2.iterator();
		while (iter2.hasNext()) {
			System.out.print(iter2.next());
			if (iter2.hasNext()) {
				System.out.print(", ");
			}
		}
		System.out.println();
	}

}
