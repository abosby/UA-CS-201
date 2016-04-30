JC = javac
JFLAGS = -d classfiles -sourcepath src
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

SOURCES= src/BQueue.java src/BinarySearchTree.java src/RBTree.java src/Trees.java
CLASSES= classfiles/BQueue.class classfiles/BinarySearchTree.class classfiles/RBTree.class classfiles/main.class

top: classes

classes: $(SOURCES:.java=.class)

test:
	@echo testing simple BST
	java -classpath classfiles/ Trees -1 inputCorpus.txt inputCommands.txt
	@echo testing red-black tree
	java -classpath classfiles/ Trees -2 inputCorpus.txt inputCommands.txt

stresstest:
	@echo testing big moby dick test file BST
	java -classpath classfiles/ Trees -1 inputFiles/mobyDickText.txt inputFiles/inputCommands.txt
	@echo testing big lod test file RBT
	java -classpath classfiles/ Trees -2 inputFiles/lod-1.txt inputFiles/inputCommands.txt

mediumtest:
	@echo testing mediaum BST
	java -classpath classfiles/ Trees -2 inputFiles/mediumMobyDick.txt inputFiles/commands.txt
	

clean:
	rm -f classfiles/*

