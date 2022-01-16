package org.albistudio.mainops.model;

public interface TreeH2 {
    void add(int value);
    boolean containsNode(int value);
    void delete(int value);
    void traverseLevelOrder();
    void traversePostOrder(NodeH2 node);
    void traversePreOrder(NodeH2 node);
    void traverseInOrder(NodeH2 node);
}


