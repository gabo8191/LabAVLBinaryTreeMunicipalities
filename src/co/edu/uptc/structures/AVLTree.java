package co.edu.uptc.structures;

import java.util.ArrayList;
import java.util.Comparator;

public class AVLTree<T> {
	private NodeAVL<T> root;
	private Comparator<T> comparator;

	public AVLTree(Comparator<T> comparator) {
		this.root = null;
		this.comparator = comparator;
	}

	public void insert(T value) {
		root = insert(root, value);
	}

	private NodeAVL<T> insert(NodeAVL<T> currentNode, T value) {
		NodeAVL<T> aux = currentNode;
		if (currentNode != null && comparator.compare(value, currentNode.getValue()) != 0) {
			if (comparator.compare(value, currentNode.getValue()) < 0) {
				currentNode.setLeft(insert(currentNode.getLeft(), value));
			} else if (comparator.compare(value, currentNode.getValue()) > 0) {
				currentNode.setRight(insert(currentNode.getRight(), value));
			}
			currentNode.setHeight(1 + max(getHeight(currentNode.getLeft()), getHeight(currentNode.getRight())));
			int fe = getBalanceFactor(currentNode);
			switch (fe) {
				case 2:
					if (comparator.compare(value, currentNode.getLeft().getValue()) < 0) {
						aux = rotateII(currentNode);
					} else {
						aux = rotateDI(currentNode);
					}
					break;
				case -2:
					if (comparator.compare(value, currentNode.getRight().getValue()) > 0) {
						aux = rotateDD(currentNode);
					} else {
						aux = rotateID(currentNode);
					}
					break;
			}
		} else {
			aux = new NodeAVL<T>(value);
		}
		return aux;
	}

	public boolean search(T value) {
		return search(root, value);
	}

	private boolean search(NodeAVL<T> currentNode, T value) {
		boolean search = false;
		if (currentNode != null) {
			if (comparator.compare(value, currentNode.getValue()) == 0) {
				search = true;
			} else if (comparator.compare(value, currentNode.getValue()) < 0) {
				search = search(currentNode.getLeft(), value);
			} else {
				search = search(currentNode.getRight(), value);
			}
		}
		return search;
	}

	public void delete(T value) {
		root = delete(root, value);
	}

	private NodeAVL<T> delete(NodeAVL<T> currentNode, T value) {
		NodeAVL<T> aux = currentNode;
		if (currentNode != null) {
			if (comparator.compare(value, currentNode.getValue()) < 0) {
				currentNode.setLeft(delete(currentNode.getLeft(), value));
			} else if (comparator.compare(value, currentNode.getValue()) > 0) {
				currentNode.setRight(delete(currentNode.getRight(), value));
			} else {
				if ((currentNode.getLeft() == null) || (currentNode.getRight() == null)) {
					NodeAVL<T> temp = null;
					if (temp == currentNode.getLeft()) {
						temp = currentNode.getRight();
					} else {
						temp = currentNode.getLeft();
					}
					if (temp == null) {
						currentNode = null;
					} else {
						currentNode = temp;
					}
				} else {
					NodeAVL<T> temp = getNodeMax(currentNode.getLeft());
					currentNode.setValue(temp.getValue());
					currentNode.setLeft(delete(currentNode.getLeft(), temp.getValue()));
				}
			}
			if (currentNode != null) {
				currentNode.setHeight(max(getHeight(currentNode.getLeft()), getHeight(currentNode.getRight())) + 1);
				int fe = getBalanceFactor(currentNode);
				switch (fe) {
					case 2:
						if (getBalanceFactor(currentNode.getLeft()) >= 0) {
							aux = rotateII(currentNode);
						} else {
							aux = rotateDI(currentNode);
						}
						break;
					case -2:
						if (getBalanceFactor(currentNode.getRight()) <= 0) {
							aux = rotateDD(currentNode);
						} else {
							aux = rotateID(currentNode);
						}
						break;
				}
			} else {
				aux = currentNode;
			}
		} else {
			aux = currentNode;
		}
		return aux;
	}

	private NodeAVL<T> rotateII(NodeAVL<T> currenteNode) {
		NodeAVL<T> newRoot = currenteNode.getLeft();
		currenteNode.setLeft(newRoot.getRight());
		newRoot.setRight(currenteNode);
		currenteNode.setHeight(max(getHeight(currenteNode.getLeft()), getHeight(currenteNode.getRight())) + 1);
		newRoot.setHeight(max(getHeight(newRoot.getLeft()), getHeight(newRoot.getRight())) + 1);
		return newRoot;
	}

	private NodeAVL<T> rotateDD(NodeAVL<T> currenteNode) {
		NodeAVL<T> newRoot = currenteNode.getRight();
		currenteNode.setRight(newRoot.getLeft());
		newRoot.setLeft(currenteNode);
		currenteNode.setHeight(max(getHeight(currenteNode.getLeft()), getHeight(currenteNode.getRight())) + 1);
		newRoot.setHeight(max(getHeight(newRoot.getLeft()), getHeight(newRoot.getRight())) + 1);
		return newRoot;
	}

	private NodeAVL<T> rotateID(NodeAVL<T> node) {
		node.setRight(rotateII(node.getRight()));
		return rotateDD(node);
	}

	private NodeAVL<T> rotateDI(NodeAVL<T> node) {
		node.setLeft(rotateDD(node.getLeft()));
		return rotateII(node);
	}

	private int getHeight(NodeAVL<T> currentNode) {
		return (currentNode == null) ? 0 : currentNode.getHeight();
	}

	private int max(int a, int b) {
		return (a > b) ? a : b;
	}

	private int getBalanceFactor(NodeAVL<T> currentNode) {
		return (currentNode == null) ? 0 : getHeight(currentNode.getLeft()) - getHeight(currentNode.getRight());
	}

	private NodeAVL<T> getNodeMax(NodeAVL<T> node) {
		NodeAVL<T> current = node;
		while (current.getRight() != null) {
			current = current.getRight();
		}
		return current;
	}

	public ArrayList<T> preOrder() {
		return preOrder(root, new ArrayList<T>());
	}

	private ArrayList<T> preOrder(NodeAVL<T> node, ArrayList<T> list) {
		if (node != null) {
			list.add(node.getValue());
			preOrder(node.getLeft(), list);
			preOrder(node.getRight(), list);
		}
		return list;
	}

	public ArrayList<T> inOrder() {
		return inOrder(root, new ArrayList<T>());
	}

	private ArrayList<T> inOrder(NodeAVL<T> node, ArrayList<T> list) {
		if (node != null) {
			inOrder(node.getLeft(), list);
			list.add(node.getValue());
			inOrder(node.getRight(), list);
		}
		return list;
	}

	public ArrayList<T> postOrder() {
		return postOrder(root, new ArrayList<T>());
	}

	private ArrayList<T> postOrder(NodeAVL<T> node, ArrayList<T> list) {
		if (node != null) {
			postOrder(node.getLeft(), list);
			postOrder(node.getRight(), list);
			list.add(node.getValue());
		}
		return list;
	}

	public boolean find(T value) {
		return find(root, value);
	}

	private boolean find(NodeAVL<T> currentNode, T value) {
		boolean find = false;
		if (currentNode != null) {
			if (comparator.compare(value, currentNode.getValue()) == 0) {
				find = true;
			} else if (comparator.compare(value, currentNode.getValue()) < 0) {
				find = find(currentNode.getLeft(), value);
			} else {
				find = find(currentNode.getRight(), value);
			}
		}
		return find;
	}
}
