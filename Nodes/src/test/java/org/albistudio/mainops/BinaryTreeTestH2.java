package org.albistudio.mainops;

import org.albistudio.mainops.model.BinarySortedTreeH2;
import org.albistudio.mainops.model.TreeH2;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class BinaryTreeTestH2 {
    @ParameterizedTest
    @ValueSource(ints = {8})
    void shouldContainAddedNode(int node) {
        TreeH2 bt = BinarySortedTreeH2.of(List.of(node, 3, 10, 1, 6, 14, 4, 7, 13));
        assertThat(bt.containsNode(node)).isTrue();
        bt.traverseLevelOrder();
    }

    @ParameterizedTest
    @ValueSource(ints = {2})
    public void shouldDeleteNode(int node) {
        TreeH2 bt = BinarySortedTreeH2.of(List.of(4, node, 6));
        assertThat(bt.containsNode(node)).isTrue();
        bt.delete(node);
        assertThat(bt.containsNode(node)).isFalse();
    }
}
