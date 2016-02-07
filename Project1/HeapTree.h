#include <stdio.h>

struct binaryTreeNode{
	/*vars*/
	int value;
	struct binaryTreeNode *left, *right, *parent;

};

struct binaryTreeNode *newBinaryTreeNode();

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

//void siftDown(binaryTr);
