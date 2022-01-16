package org.albistudio.mainops.model;

import org.albistudio.annotations.StaticFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinarySortedTree implements Tree {
    private Node root;

    @StaticFactory
    public static BinarySortedTree of(List<Integer> nodes) {
        BinarySortedTree bt = new BinarySortedTree();
        for (Integer node : nodes) {
            bt.add(node);
        }
        return bt;
    }

    private Node addRecursive(Node current, Integer rootValue, int value, boolean isLeft) {

        if (current == null) {
            System.out.printf("root.value %s, inserted: %s%n", rootValue, value);
            return new Node(value, isLeft);
        }

        if (value < current.value) {
            current.left = addRecursive(current.left, current.value, value, true);
        } else current.right = addRecursive(current.right, current.value, value, false);


        return current;
    }

    private Node deleteRecursive(Node current, int value) {
        if (current == null) return null;

        if (value == current.value) {
            if (current.left == null && current.right == null) return null;

            if (current.right == null) return current.left;

            if (current.left == null) return current.right;

            int smallestValue = findSmallestValue(current.right);
            current.value = smallestValue;
            current.right = deleteRecursive(current.right, smallestValue);
            return current;
        } else if (value < current.value) {
            current.left = deleteRecursive(current.left, value);
            return current;
        }

        current.right = deleteRecursive(current.right, value);
        return current;
    }

    private boolean containsNodeRecursive(Node current, int value) {
        if (current == null) {
            return false;
        }
        if (value == current.value) {
            return true;
        }
        return value < current.value
                ? containsNodeRecursive(current.left, value)
                : containsNodeRecursive(current.right, value);
    }

    private int findSmallestValue(Node root) {
        return root.left == null ? root.value : findSmallestValue(root.left);
    }

    public void add(int value) {
        root = addRecursive(root, null, value, false);
    }

    public void delete(int value) {
        root = deleteRecursive(root, value);
    }

    public boolean containsNode(int value) {
        return containsNodeRecursive(root, value);
    }

    public void traverseInOrder(Node node) {
        if (node != null) {
            traverseInOrder(node.left);
            System.out.printf(" %s", node.value);
            traverseInOrder(node.right);
        }
    }

    public void traversePreOrder(Node node) {
        if (node != null) {
            System.out.printf(" %s", node.value);
            traversePreOrder(node.left);
            traversePreOrder(node.right);
        }
    }

    public void traversePostOrder(Node node) {
        if (node != null) {
            traversePostOrder(node.left);
            traversePostOrder(node.right);
            System.out.printf(" %s", node.value);
        }
    }

    public void traverseLevelOrder() {
        if (root == null) {
            return;
        }

        Queue<Node> nodes = new LinkedList<>();
        nodes.add(root);

        while (!nodes.isEmpty()) {

            Node node = nodes.remove();

            System.out.printf(" %s = isLeft: %s, ", node.value, node.isLeft);

            if (node.left != null) {
                nodes.add(node.left);
            }

            if (node.right != null) {
                nodes.add(node.right);
            }
        }
    }

}
