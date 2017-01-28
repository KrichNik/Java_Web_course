package practice6.part6;

/**
 * Реализовать консольное приложение, которое анализирует текст.
 *
 * Part 1 (frequency)
 *
 * Во входном файле найти три слова, которые встречаются наиболее часто, и
 * распечатать их отсортированными по алфавиту в обратном порядке в формате:
 * слово ==> частота
 *
 * Part 2 (length)
 *
 * Во входном файле найти три самых длинных слова и распечатать их в формате:
 * слово ==> количество букв в слове. Список должен быть отсортирован по
 * убыванию количества букв в слове.
 *
 * Part 3 (duplicates)
 *
 * Во входном файле найти первые три слова, которые имеют дубликаты, и
 * напечатать их инверсию в верхнем регистре.
 *
 * 01.12.16
 *
 * @author Nikita Datsenko
 *
 */
public class Part6 {

	public static void main(String[] args) {
		Main.main(new String[] { "-i src/practice6/input.txt -t frequency" });
		Main.main(new String[] { "--input src/practice6/input.txt --task length" });
		Main.main(new String[] { "-i src/practice6/input.txt -t duplicates" });
	}

}
