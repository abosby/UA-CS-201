#ifndef Heaptree_H_
#define Heaptree_H_
#include <stdio.h>
#include <stdlib.h>

/*Structure for Binary Tree Node*/
struct binaryTreeNode{

	/*vars*/
	int value;
	struct binaryTreeNode *left, *right, *parent;
	struct binaryTreeNode *qNext; //Queue Pointer
	struct binaryTreeNode *sNext; //Stack Pointer

};

struct binaryTreeNode *newBinaryTreeNode();

/*Structure for Binary Tree*/
struct binaryTree{

	/*vars*/
	struct binaryTreeNode *root;
		
};
	
void insert(struct binaryTree *,int value);
int removeTop(struct binaryTree *);

//Traversals
struct binaryTree *newTree();
void preOrderTraversal(struct binaryTreeNode *node);
void inOrderTraversal(struct binaryTreeNode *node);
void postOrderTraversal(struct binaryTreeNode *node);
void levelOrderTraversal(struct binaryTreeNode *node);

#endif
