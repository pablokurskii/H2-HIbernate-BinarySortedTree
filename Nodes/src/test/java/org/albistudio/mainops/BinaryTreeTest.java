package org.albistudio.mainops;

import org.albistudio.mainops.model.BinarySortedTree;
import org.albistudio.mainops.model.Tree;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class BinaryTreeTest {
    @ParameterizedTest
    @ValueSource(ints = {8})
    void shouldContainAddedNode(int node) {
        Tree bt = BinarySortedTree.of(List.of(node, 3, 10, 1, 6, 14, 4, 7, 13));
        assertThat(bt.containsNode(node)).isTrue();
        bt.traverseLevelOrder();
    }

    @ParameterizedTest
    @ValueSource(ints = {2})
    public void shouldDeleteNode(int node) {
        Tree bt = BinarySortedTree.of(List.of(4, node, 6));
        assertThat(bt.containsNode(node)).isTrue();
        bt.delete(node);
        assertThat(bt.containsNode(node)).isFalse();
    }
}
