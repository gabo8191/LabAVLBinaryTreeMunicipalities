package co.edu.uptc.structures;

public class NodeAVL<T> {
	private int height;
	private T value;
	private NodeAVL<T> left;
	private NodeAVL<T> right;

	NodeAVL(T value) {
		this.value = value;
		height = 1;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T Value) {
		this.value = Value;
	}

	public NodeAVL<T> getLeft() {
		return left;
	}

	public void setLeft(NodeAVL<T> left) {
		this.left = left;
	}

	public NodeAVL<T> getRight() {
		return right;
	}

	public void setRight(NodeAVL<T> right) {
		this.right = right;
	}
}
