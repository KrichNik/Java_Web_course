package practice6.part4;

import java.util.ArrayList;
import java.util.List;

public class Graph {

	private final int countOfVertex;

	private final List<Vertex> vertexList;

	private final List<Edge> edgeList = new ArrayList<>();

	public Graph(int countOfVertex) {
		this.countOfVertex = countOfVertex;
		vertexList = createVertexes(countOfVertex);
	}

	public void addEdge(String name) {
		Vertex v1 = null;
		Vertex v2 = null;
		for (int i = 0; i < vertexList.size(); i++) {
			v1 = vertexList.get(i);
			if (v1.isNotReadyAdd) {
				v1 = null;
				continue;
			}
			for (int j = i + 1; j < vertexList.size(); j++) {
				v2 = vertexList.get(j);
				if (v2.hasContainVertex(v1)) {
					v2 = null;
					continue;
				}
				break;
			}
			break;
		}
		if (v1 == null || v2 == null) {
			System.out.println("Vertexes are not more!");
			return;
		}
		v1.addAdjacentVertexes(v2);
		v2.addAdjacentVertexes(v1);
		edgeList.add(this.new Edge(name, v1, v2));
	}

	public void removeEdge(String name) {
		Edge edgeForRemove = new Edge(name);
		int index = 0;
		if ((index = edgeList.indexOf(edgeForRemove)) != -1) {
			edgeForRemove = edgeList.get(index);
			Vertex v1 = edgeForRemove.getVertex1();
			Vertex v2 = edgeForRemove.getVertex2();
			v1.removeAdjacentVertexes(v2);
			v2.removeAdjacentVertexes(v1);
			edgeList.remove(edgeForRemove);
		} else {
			System.out.println("This edge is not exist!");
		}
	}

	public void printGraph() {
		for (Edge edge : edgeList) {
			System.out.println(edge);
		}
	}

	private List<Vertex> createVertexes(int count) {
		List<Vertex> vertexes = new ArrayList<>();
		for (int i = 0; i < count; i++) {
			vertexes.add(this.new Vertex(i));
		}
		return vertexes;
	}

	private class Vertex {

		private List<Vertex> adjacentVertexes = new ArrayList<>(countOfVertex - 1);

		private int index;

		private boolean isNotReadyAdd;

		public Vertex(int index) {
			this.index = index;
		}

		public boolean hasContainVertex(Vertex v) {
			return adjacentVertexes.contains(v);
		}

		public void addAdjacentVertexes(Vertex v) {
			if (isNotReadyAdd) {
				System.out.println("This vertex can't have adjacent vertexes!");
				return;
			}
			if (adjacentVertexes.contains(v)) {
				System.out.println("Edge is already exist!");
				return;
			}
			adjacentVertexes.add(v);
			if (adjacentVertexes.size() == countOfVertex - 1) {
				isNotReadyAdd = true;
			}
		}

		public void removeAdjacentVertexes(Vertex v) {
			if (!hasContainVertex(v)) {
				System.out.println("Edge is not already exist!");
				return;
			}
			adjacentVertexes.remove(v);
			if (isNotReadyAdd) {
				isNotReadyAdd = false;
			}
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + index;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (getClass() != obj.getClass()) {
				return false;
			}
			Vertex other = (Vertex) obj;
			if (!getOuterType().equals(other.getOuterType())) {
				return false;
			}
			if (index != other.index) {
				return false;
			}
			return true;
		}

		private Graph getOuterType() {
			return Graph.this;
		}
	}

	private class Edge {

		private String name;

		private Vertex vertex1;

		private Vertex vertex2;

		public Edge(String name, Vertex vertex1, Vertex vertex2) {
			this.name = name;
			this.vertex1 = vertex1;
			this.vertex2 = vertex2;
		}

		public Edge(String name) {
			this.name = name;
		}

		public Vertex getVertex1() {
			return vertex1;
		}

		public Vertex getVertex2() {
			return vertex2;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + ((name == null) ? 0 : name.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (getClass() != obj.getClass()) {
				return false;
			}
			Edge other = (Edge) obj;
			if (!getOuterType().equals(other.getOuterType())) {
				return false;
			}
			if (name == null) {
				if (other.name != null) {
					return false;
				}
			} else if (!name.equals(other.name)) {
				return false;
			}
			return true;
		}

		private Graph getOuterType() {
			return Graph.this;
		}

		@Override
		public String toString() {
			return "Edge " + name + " - vertexes: " + vertex1.index + ", " + vertex2.index;
		}
	}
}
