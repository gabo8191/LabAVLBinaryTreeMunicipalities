package co.edu.uptc.structures;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BinaryTree<T> {
  private Node<T> root;
  private Comparator<T> comparator;

  public BinaryTree(Comparator<T> comparator) {
    this.comparator = comparator;
    this.root = null;
  }

  public Node<T> getRoot() {
    return root;
  }

  public void insert(T data) {
    Node<T> newNode = new Node<>(data);
    if (root == null) {
      root = newNode;
    } else {
      insert(root, newNode);
    }
  }

  private void insert(Node<T> root, Node<T> newNode) {
    if (comparator.compare(newNode.getData(), root.getData()) == 0) {
      return;
    }
    if (comparator.compare(newNode.getData(), root.getData()) < 0) {
      if (root.getLeft() == null) {
        root.setLeft(newNode);
      } else {
        insert(root.getLeft(), newNode);
      }
    } else {
      if (root.getRight() == null) {
        root.setRight(newNode);
      } else {
        insert(root.getRight(), newNode);
      }
    }
  }

  public boolean find(T data) {
    return find(root, data);
  }

  private boolean find(Node<T> root, T data) {
    if (root == null) {
      return false;
    }
    if (comparator.compare(data, root.getData()) == 0) {
      return true;
    }
    if (comparator.compare(data, root.getData()) > 0) {
      return find(root.getLeft(), data);
    }
    return find(root.getRight(), data);

  }

  public T search(T data) {
    return search(root, data);
  }

  private T search(Node<T> root, T data) {
    if (root == null) {
      return null;
    }
    if (comparator.compare(data, root.getData()) == 0) {
      return root.getData();
    }
    if (comparator.compare(data, root.getData()) < 0) {
      return search(root.getLeft(), data);
    }
    return search(root.getRight(), data);
  }

  public ArrayList<T> inOrder() {
    ArrayList<T> list = new ArrayList<>();
    inOrder(root, list);
    return list;
  }

  private void inOrder(Node<T> root, List<T> list) {
    if (root != null) {
      inOrder(root.getLeft(), list);
      list.add(root.getData());
      inOrder(root.getRight(), list);
    }
  }

  public ArrayList<T> preOrder() {
    ArrayList<T> list = new ArrayList<>();
    preOrder(root, list);
    return list;
  }

  private void preOrder(Node<T> root, List<T> list) {
    if (root != null) {
      list.add(root.getData());
      preOrder(root.getLeft(), list);
      preOrder(root.getRight(), list);
    }
  }

  public ArrayList<T> postOrder() {
    ArrayList<T> list = new ArrayList<>();
    postOrder(root, list);
    return list;
  }

  private void postOrder(Node<T> root, List<T> list) {
    if (root != null) {
      postOrder(root.getLeft(), list);
      postOrder(root.getRight(), list);
      list.add(root.getData());
    }
  }

  public void delete(T data) throws Exception {
    root = delete(root, data);
  }

  private Node<T> delete(Node<T> root, T data) throws Exception {
    if (root == null) {
      throw new Exception("Node not found");
    } else if (comparator.compare(data, root.getData()) < 0) {
      Node<T> left = delete(root.getLeft(), data);
      root.setLeft(left);
    } else if (comparator.compare(data, root.getData()) > 0) {
      Node<T> right = delete(root.getRight(), data);
      root.setRight(right);
    } else {
      Node<T> aux = root;
      if (aux.getLeft() == null) {
        root = aux.getRight();
      } else if (aux.getRight() == null) {
        root = aux.getLeft();
      } else {
        aux = replace(aux);
        aux = null;
      }
    }
    return root;
  }

  private Node<T> replace(Node<T> root) {
    Node<T> aux = root;
    Node<T> auxLeft = root.getLeft();
    while (auxLeft.getRight() != null) {
      aux = auxLeft;
      auxLeft = auxLeft.getRight();
    }
    root.setData(auxLeft.getData());
    if (aux == root) {
      aux.setLeft(auxLeft.getLeft());
    } else {
      aux.setRight(auxLeft.getLeft());
    }
    return auxLeft;
  }

  public int size() {
    return size(root);
  }

  private int size(Node<T> root) {
    if (root == null) {
      return 0;
    }
    return 1 + size(root.getLeft()) + size(root.getRight());
  }
}
