JC = javac
JFLAGS = -d classfiles -sourcepath src
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

SOURCES= src/BQueue.java src/BinarySearchTree.java src/DisjointSet.java src/Edge.java src/RedBlackTree.java src/SinglyLinkedList.java src/Vertex.java src/Kruscal.java
CLASSES= classfiles/BQueue.class classfiles/BinarySearchTree.class classfiles/DisjointSet.calss classfiles/Edge.java classfiles/RedBlackTree.java classfiles/SinglyLinkedList.java classfiles/Vertex.java classfiles/Kruscal.class

top: classes

classes: $(SOURCES:.java=.class)

test:

stresstest:
	@echo testing big graph
	java -classpath classfiles/ Kruscal g5

mediumtest:

clean:
	rm -f classfiles/*

