package org.albistudio.mainops.model;

import javax.persistence.*;

@Entity
@Table(name = "nodes")
public class Node {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "value", length = Integer.MAX_VALUE, nullable = false)
    int value;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    Node left;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    Node right;

    /*TODO make LeftLeaf(discriminator Left) and RightLeaf(discriminator Right) classes that extend Node class(discriminator Root)*/
    @Column(name = "isLeft")
    boolean isLeft;

    /*TODO*/
    @Column(name = "height")
    long height; //not sure how to calc

    Node(int value, boolean isLeft) {
        this.value = value;
        right = null;
        left = null;
        this.isLeft = isLeft;

    }
}
