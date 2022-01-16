package org.albistudio.mainops.model;

import org.albistudio.annotations.StaticFactory;
import org.hibernate.Session;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Entity also saves its elements into DB. In the next versions this class will be separated into business layer and service layer.
 * Business part creates and fills Binary Sorted Tree by Nodes.
 * Service part intercepts addRecursive method, makes an instance of Node and persists in DB.
 * */
public class BinarySortedTreeH2 implements TreeH2 {
    private NodeH2 root;
    private Session session;

    public void setSession(Session session) {
        this.session = session;
    }

    @Deprecated
    @StaticFactory
    public static BinarySortedTreeH2 of(List<Integer> nodes) {
        BinarySortedTreeH2 bt = new BinarySortedTreeH2();
        for (Integer node : nodes) {
            bt.add(node);
        }
        return bt;
    }

    @StaticFactory
    public static BinarySortedTreeH2 getInstance() {
        return new BinarySortedTreeH2();
    }

    private NodeH2 addRecursive(NodeH2 current, Integer rootValue, int value, boolean isLeft) {

        if (current == null) {
            System.out.printf("root.value %s, inserted: %s%n", rootValue, value);
            var node = NodeH2.getInstance(value, isLeft);
            session.persist(node);
            return node;
        }

        if (value < current.value) {
            current.left = addRecursive(current.left, current.value, value, true);
        } else current.right = addRecursive(current.right, current.value, value, false);


        return current;
    }

    /*TODO do we need this method?*/
    private NodeH2 deleteRecursive(NodeH2 current, int value) {
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

    private boolean containsNodeRecursive(NodeH2 current, int value) {
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

    private int findSmallestValue(NodeH2 root) {
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

    public void traverseInOrder(NodeH2 node) {
        if (node != null) {
            traverseInOrder(node.left);
            System.out.printf(" %s", node.value);
            traverseInOrder(node.right);
        }
    }

    public void traversePreOrder(NodeH2 node) {
        if (node != null) {
            System.out.printf(" %s", node.value);
            traversePreOrder(node.left);
            traversePreOrder(node.right);
        }
    }

    public void traversePostOrder(NodeH2 node) {
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

        Queue<NodeH2> nodes = new LinkedList<>();
        nodes.add(root);

        while (!nodes.isEmpty()) {

            NodeH2 node = nodes.remove();

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
