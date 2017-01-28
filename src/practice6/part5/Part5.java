package practice6.part5;

/**
 * Создать generic класс Tree, который реализует структуру данных "двоичное
 * дерево поиска".
 *
 * 02.12.16
 *
 * @author Nikita Datsenko
 *
 */
public class Part5 {

	public static void main(String[] args) {
		Tree<Integer> tree = new Tree<>();

		System.out.println(tree.add(3));
		System.out.println(tree.add(3));

		System.out.println("~~~~~~~");
		tree.add(new Integer[] { 1, 2, 5, 4, 6, 0 });
		tree.print();

		System.out.println("~~~~~~~");
		System.out.println(tree.remove(5));
		System.out.println(tree.remove(5));

		System.out.println("~~~~~~~");
		tree.print();
	}

}
