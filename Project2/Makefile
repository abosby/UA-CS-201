JC = javac
JFLAGS = -d classfiles -sourcepath src
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

SOURCES= src/BQueue.java src/BinarySearchTree.java src/RBTree.java src/main.java
CLASSES= classfiles/BQueue.class classfiles/BinarySearchTree.class classfiles/RBTree.class classfiles/main.class

default: clean classes

classes: $(SOURCES:.java=.class)

test: clean classes
	@echo testing simple BST
	java -classpath classfiles/ main -1 inputFiles/inputCorpus.txt inputFiles/inputCommands.txt > outputFile1.txt
	@echo testing red-black tree
	java -classpath classfiles/ main -2 inputFiles/inputCorpus.txt inputFiles/inputCommands.txt > outputFile2.txt
run: 

clean:
	rm -f /classfiles/* .class

