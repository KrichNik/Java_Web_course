package practice6.part2;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Написать программу, которая моделирует следующий процесс. В кругу стоят n
 * человек, пронумерованных от 0 до n-1. При ведении счета по кругу
 * вычеркивается каждый k-й человек (0 < k < n), пока не останется один. Решить
 * задачу двумя способами - с использованием класса ArrayList и с использованием
 * класса LinkedList.
 *
 * 23.11.16
 *
 * @author Nikita Datsenko
 *
 */
public class Part2 {

	private static final int N = 100000;

	private static final int K = 3;

	public static void main(String[] args) {
		System.out.println("ArrayList:");
		List<Integer> list1 = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			list1.add(i);
		}
		doCircle(list1);
		System.out.println();
		System.out.println("LinkedList:");
		List<Integer> list2 = new LinkedList<>();
		for (int i = 0; i < N; i++) {
			list2.add(i);
		}
		doCircle(list2);
	}

	static void doCircle(List<Integer> list) {
		long afterTime = new Date().getTime();
		while (list.size() > 1) {
			Iterator<Integer> iter = list.iterator();
			while (iter.hasNext()) {
				for (int i = 0; i < K; i++) {
					if (iter.hasNext()) {
						iter.next();
					}
				}
				iter.remove();
			}

		}
		long beforeTime = new Date().getTime();
		long resultTime = beforeTime - afterTime;
		System.out.println("time: " + resultTime + " ms");
	}
}
