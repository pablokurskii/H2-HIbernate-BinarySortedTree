package org.albistudio.mainops.model;

import javax.persistence.*;

@Entity
@Table(name = "Nodes")
/*TODO implement interface*/
public class NodeH2 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "value", length = Integer.MAX_VALUE, nullable = false)
    int value;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    NodeH2 left;

    public void setLeft(NodeH2 left) {
        this.left = left;
    }

    public void setRight(NodeH2 right) {
        this.right = right;
    }

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    NodeH2 right;


    /*TODO s BELOW*/

    /*make LeftLeaf(discr Left) and RightLeaf(discr Right), Node class(discr Root)*/
    @Column(name = "isLeft")
    final boolean isLeft;

    @Column(name = "height")
    long height; //not sure how to calc

    private NodeH2(int value, boolean isLeft) {
        this.value = value;
        /*TODO possibly delete later*/
        right = null;
        left = null;
        this.isLeft = isLeft;
    }

    public static NodeH2 getInstance(int value, boolean isLeft) {
        return new NodeH2(value, isLeft);
    }

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Node{" +
                "id=" + id +
                ", value=" + value +
                ", isLeft=" + isLeft +
                ", height=" + height +
                '}';
    }
}

