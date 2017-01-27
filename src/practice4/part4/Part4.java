package practice4.part4;

import java.util.Iterator;

/**
 * Задание 4
 *
 * Создать класс, который реализует интерфейс java.lang.Iterable. Класс должен
 * разбирать текстовый файл и возвращать предложения из файла. Метод iterator
 * данного класса должен возвращать объект итератор - экземпляр внутреннего
 * класса.
 *
 * @author Nikita Datsenko
 *
 */
public class Part4 {

	public static void main(String[] args) {
		FileIterator fileIter = new FileIterator("src/practice4/file4.txt");
		Iterator<String> iter = fileIter.iterator();
		while (iter.hasNext()) {
			System.out.println(iter.next());
		}
	}

}
