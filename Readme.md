## Storing Sorted Binary Tree nodes in H2 with Hibernate 
#### DISCLAIMER: This is not production ready code, neither even not close to production code. Author understands that this code still requires a lot of refactoring, cleaning, layering and test coverage. The main goal of this project is to demonstrate  how Sorted Binary Tree can be created, filled by nodes and saved into H2 DB with Hibernate.
### Simple class Node represents an entity of Sorted Binary tree.
Each Node takes one of the forms: Binary tree, Root or Leaf.
If it's a root it has no root key, probably has child nodes, and its height is zero. 
Else it has a root key, the height greater than zero and possibly has child nodes.

Nodes DB table example \
id|rootId|value|height|isLeft \
1 |   0  |  6  |   0  |  0 \
2 |   1  |  4  |   1  |  1 \
3 |   1  |  8  |   1  |  0 \
4 |   2  |  3  |   2  |  1 \
5 |   2  |  5  |   2  |  0 \
6 |   3  |  7  |   2  |  1 \
7 |   3  |  9  |   2  |  0 


Tools versions: \
Hibernate 5.6.1.Final \
H2 1.4.200 \
Gradle 7.3.3 \
Java Liberika JDK 17.0.1


References: \
Theory \
https://en.wikipedia.org/wiki/Node_(computer_science) \
https://en.wikipedia.org/wiki/Binary_tree \
https://en.wikipedia.org/wiki/Binary_search_tree

JPA
https://www.baeldung.com/jpa-entities \
https://www.baeldung.com/jpa-hibernate-persistence-context \
https://thorben-janssen.com/complete-guide-inheritance-strategies-jpa-hibernate/ \
https://www.javaguides.net/2019/11/hibernate-h2-database-example-tutorial.html \
https://www.baeldung.com/hibernate-save-persist-update-merge-saveorupdate