JCC = javac
JFLAGS = -d classfiles -sourcepath src

top: BQueue.class BinaryNode.class BinarySearchTree.class RBNode.class RBTree.class

BQueue.class: src/BQueue.java
	$(JCC) $(JFLAGS) src/BQueue.java

BinaryNode.class: src/BinaryNode.java BinarySearchTree.class BQueue.class
	$(JCC) $(JFLAGS) src/BinaryNode.java

BinarySearchTree.class: src/BinarySearchTree.java BinaryNode.class BQueue.class
	$(JCC) $(JFLAGS) src/BinarySearchTree.java

RBNode.class: src/RBNode.java
	$(JCC) $(JFLAGS) src/RBNode.java

RBTree.class: src/RBTree.java
	$(JCC) $(JFLAGS) src/RBTree.java

clean :
	rm -f /classfiles/* .class

