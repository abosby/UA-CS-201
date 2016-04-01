JCC = javac
JFLAGS = -d classfiles -sourcepath src

top: BQueue.class BinaryNode.class BinarySearchTree.class RBNode.class RBTree.class

BQueue.class: src/BQueue.java
	$(JCC) $(JFLAGS) src/BQueue.java

BinaryNode.class: src/BinaryNode.java BinarySearchTree.class BQueue.class
	$(JCC) $(JFLAGS) src/BinaryNode.java

BinarySearchTree.class: src/BinarySearchTree.java BinaryNode.class BQueue.class
	$(JCC) $(JFLAGS) src/BinarySearchTree.java

<<<<<<< HEAD
RBNode.class: src/RBNode.java
	$(JCC) $(JFLAGS) src/RBNode.java

RBTree.class: src/RBTree.java
	$(JCC) $(JFLAGS) src/RBTree.java

clean :
	rm -f /classfiles/* .class
=======
stresstest:
	@echo testing big moby dick test file BST
	java -classpath classfiles/ Trees -1 inputFiles/mobyDickText.txt inputFiles/inputCommands.txt > outputFile3.txt
	@echo testing big lod test file RBT
	java -classpath classfiles/ Trees -2 inputFiles/lod-1.txt inputFiles/inputCommands.txt > outputFile4.txt

mediumtest:
	@echo testing mediaum BST
	java -classpath classfiles/ Trees -2 inputFiles/mediumMobyDick.txt inputFiles/commands.txt > outputFile3.txt
	

run: 

clean:
	rm -f classfiles/*
>>>>>>> parent of ba54b05... Completed Project

