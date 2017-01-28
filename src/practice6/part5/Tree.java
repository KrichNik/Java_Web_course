package practice6.part5;

public class Tree<E extends Comparable<E>> {

	private Node<E> root;

	public Tree() {
		root = null;
	}

	public void add(E[] elements) {
		for (E element : elements) {
			add(element);
		}
	}

	public boolean add(E element) {
		Node<E> newNode = new Node<>();
		newNode.key = element;

		if (root == null) {
			root = newNode;
			return true;
		}

		Node<E> current = root;
		Node<E> parent = null;

		while (true) {
			parent = current;
			if (element.compareTo(current.key) == -1) {
				current = current.leftChild;
				if (current == null) {
					parent.leftChild = newNode;
					return true;
				}
			} else if ((element.compareTo(current.key) == 1)) {
				current = current.rightChild;
				if (current == null) {
					parent.rightChild = newNode;
					return true;
				}
			} else {
				return false;
			}
		}
	}

	public boolean remove(E element) {
		if (root == null) {
			throw new IllegalArgumentException("Tree is empty!");
		}

		Node<E> current = root;
		Node<E> parent = root;
		boolean isLeftChild = true;

		// searching node
		while (current.key.compareTo(element) != 0) {
			parent = current;
			if (element.compareTo(current.key) == -1) {
				isLeftChild = true;
				current = current.leftChild;
			} else {
				isLeftChild = false;
				current = current.rightChild;
			}
			if (current == null) {
				return false;
			}
		}
		// node was found

		if (current.leftChild == null && current.rightChild == null) {
			if (current.key == root.key) {
				root = null;
			} else if (isLeftChild) {
				parent.leftChild = null;
			} else {
				parent.rightChild = null;
			}
		} else if (current.rightChild == null) {
			if (current.key == root.key) {
				root = current.leftChild;
			} else if (isLeftChild) {
				parent.leftChild = current.leftChild;
			} else {
				parent.rightChild = current.leftChild;
			}
		} else if (current.leftChild == null) {
			if (current.key == root.key) {
				root = current.rightChild;
			} else if (isLeftChild) {
				parent.leftChild = current.rightChild;
			} else {
				parent.rightChild = current.rightChild;
			}
		} else {
			Node<E> successor = getSuccessor(current);
			if (current.key == root.key) {
				root = successor;
			} else if (isLeftChild) {
				parent.leftChild = successor;
			} else {
				parent.rightChild = successor;
			}
			successor.leftChild = current.leftChild;
		}

		return true;
	}

	private Node<E> getSuccessor(Node<E> delNode) {
		Node<E> successorParent = delNode;
		Node<E> successor = delNode;
		Node<E> current = delNode.rightChild;

		while (current != null) {
			successorParent = successor;
			successor = current;
			current = current.leftChild;
		}

		if (successor.key != delNode.rightChild.key) {
			successorParent.leftChild = successor.rightChild;
			successor.rightChild = delNode.rightChild;
		}

		return successor;
	}

	public void print() {
		inOrder(root);
	}

	private void inOrder(Node<E> lokalRoot) {
		if (lokalRoot != null) {
			inOrder(lokalRoot.leftChild);
			printNode(lokalRoot);
			inOrder(lokalRoot.rightChild);
		}
	}

	private void printNode(Node<E> node) {
		if(node.key == root.key) {
			System.out.println(node.key);
			return;
		} else {
			System.out.print(" ");
		}

		System.out.print("  ");

		if (node.leftChild != null || node.rightChild != null) {
			System.out.println(node.key);
			return;
		} else {
			System.out.print(" ");
		}

		System.out.print("  ");
		System.out.println(node.key);
	}

	private static class Node<E> {

		private E key;

		private Node<E> leftChild;

		private Node<E> rightChild;

		@Override
		public String toString() {
			return String.valueOf(key);
		}

	}

}
