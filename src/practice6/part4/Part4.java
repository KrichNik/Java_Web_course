package practice6.part4;

import java.util.Date;

/**
 * Реализовать класс Graph, представляющий собой неориентированный граф. В
 * конструкторе класса передается количество вершин в графе. Методы должны
 * поддерживать быстрое добавление и удаление ребер.
 *
 * 23.11.16
 *
 * @author Nikita Datsenko
 *
 */
public class Part4 {

	public static void main(String[] args) {
		int vertexCount = 10;
		long afterTime = new Date().getTime();
		Graph graph = new Graph(vertexCount);
		for (int i = 0; i < 20; i++) {
			graph.addEdge("edge" + i);
		}
		graph.removeEdge("edge10");
		graph.addEdge("new edge10");
		graph.printGraph();
		long beforeTime = new Date().getTime();
		long resultTime = beforeTime - afterTime;
		System.out.println("time: " + resultTime + " ms");
	}

}
