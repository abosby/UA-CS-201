#ifndef Heaptree_H_
#define Heaptree_H_
#include <stdio.h>
#include <stdlib.h>

/*Structure for Binary Tree Node*/
struct binaryTreeNode{

	/*vars*/
	int value;
	struct binaryTreeNode *left, *right, *parent;
	//struct binaryTreeNode *qNext; //Queue Pointer
	//struct binaryTreeNode *sNext; //Stack Pointer
	//struct binaryTreeNode *oNext; //Original Stack pointer

};

struct binaryTreeNode *newBinaryTreeNode();

/*Structure for Binary Tree*/
struct binaryTree{

	/*vars*/
	struct binaryTreeNode *root;
	int size;
		
};
	
void insert(struct binaryTree *,struct binaryTreeNode *);
int isHeapEmpty(struct binaryTree *);
void heapify(struct binaryTreeNode *, int optionD);

//Traversals
struct binaryTree *newTree();
void preOrderTraversal(struct binaryTreeNode *);
void inOrderTraversal(struct binaryTreeNode *);
void postOrderTraversal(struct binaryTreeNode *);
void levelOrderTraversal(struct binaryTreeNode *);

#endif
