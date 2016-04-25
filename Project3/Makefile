JC = javac
JFLAGS = -d classfiles -sourcepath src
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

SOURCES= src/BQueue.java src/DisjointSet.java src/Edge.java src/EdgeRedBlackTree.java src/SinglyLinkedList.java src/Vertex.java src/kruskal.java
CLASSES= classfiles/BQueue.class classfiles/DisjointSet.class classfiles/Edge.java classfiles/EdgeRedBlackTree.java classfiles/SinglyLinkedList.java classfiles/Vertex.java classfiles/kruskal.class

top: classes

classes: $(SOURCES:.java=.class)

test:
	@echo testing
	java -classpath classfiles/ kruskal -r 0 g3

mediumtest:

bigtest:

	@echo testing with Dr Lusths provided example
	java -classpath classfiles/ kruskal -r 0 testgraph.txt > testGraphOutputRoot0.txt
	@echo testing G3 with root 0
	java -classpath classfiles/ kruskal -r 0 g3 > g3outputroot0.txt
	@echo testing G3 with no root argument
	java -classpath classfiles/ kruskal g3 > g3outputnoroot.txt
	@echo testing G5 with root 0
	java -classpath classfiles/ kruskal -r 0 g5 > g5outputroot0.txt
	@echo testing G5 with no root argument
	java -classpath classfiles/ kruskal g5 g5 > g5outputnoroot.txt


	#@echo testing G3 with root 0
	#java -classpath classfiles/ kruskal -r 0 g3
	#@echo testing G3 with no root argument
	#java -classpath classfiles/ kruskal g3
	#@echo testing G5 with root 0
	#java -classpath classfiles/ kruskal -r 0 g5
	#@echo testing G5 with no root argument
	#java -classpath classfiles/ kruskal g5
	


clean:
	rm -f classfiles/*

