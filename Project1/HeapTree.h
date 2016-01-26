#include <stdio.h>

struct binaryTreeNode{
	int value;
	binaryTreeNode *left, *right, *parent;
};

struct binaryTree{
	binaryTreeNode *root;
};

class HeapTree{

	private:
		binaryTree heapTree;

	public:

		void insert(int value);
		int removeTop();

		//Traversals
		void preOrderTraversal(binaryTreeNode *node);
		void inOrderTraversal(binaryTreeNode *node);
		void postOrderTraversal(binaryTreeNode *node);
		void levelOrderTraversal(binaryTreeNode *node);
		
		//void siftDown(binaryTr);
};

void HeapTree::insert(int value){

	binaryTreeNode *node = new binaryTreeNode;
	node->left = NULL;
	node->right = NULL;
	node->value = value;
}

int HeapTree::removeTop(){
	//IMPLEMENT
}

void HeapTree::preOrderTraversal(binaryTreeNode *node){

	if (node){
		//print node
		printf("%d ", node->value);

		//traverse left
		if (node->left){
			preOrderTraversal(node->left);
		}

		//traverse right
		if (node->right){
			preOrderTraversal(node->right);
		}
	}
}

void HeapTree::inOrderTraversal(binaryTreeNode *node){

	if (node){
		//traverse left
		if (node->left){
			inOrderTraversal(node->left);
		}

		//print node
		printf("%d ", node->value);

		//traverse right
		if (node->right){
			inOrderTraversal(node->right);
		}
	}
}

void HeapTree::postOrderTraversal(binaryTreeNode *node){

	if (node){
		//traverse left
		if (node->left){
			postOrderTraversal(node->left);
		}

		//traverse right
		if (node->right){
			postOrderTraversal(node->right);
		}

		//print node
		printf("%d ", node->value);
	}
}