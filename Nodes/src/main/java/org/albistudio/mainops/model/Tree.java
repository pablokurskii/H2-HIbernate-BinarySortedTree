package org.albistudio.mainops.model;

public interface Tree {
    void add(int value);
    boolean containsNode(int value);
    void delete(int value);
    void traverseLevelOrder();
    void traversePostOrder(Node node);
    void traversePreOrder(Node node);
    void traverseInOrder(Node node);
}


