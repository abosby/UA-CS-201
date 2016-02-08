#include <stdio.h>
#include <stdlib.h>
#include "Heaptree.h"

struct binaryTreeNode *
newBinaryTreeNode(){
	struct binaryTreeNode *n = malloc(sizeof(struct binaryTreeNode));
	n->value = (int)NULL;
	n->left = NULL;
	n->right = NULL;
	n->parent = NULL;
	n->qNext = NULL;
	n->sNext = NULL;
	return n;
}

struct binaryTree *
newTree(){
	struct binaryTree *b = malloc(sizeof(struct binaryTree));
	b->root = newBinaryTreeNode();
	return b;
}

void 
insert(struct binaryTree *tree, int value){

        struct binaryTreeNode *node = malloc(sizeof(struct binaryTreeNode));
        node->left = NULL;
        node->right = NULL;
        node->value = value;
}

int 
removeTop(struct binaryTree *tree){
        //IMPLEMENT
	return 0;
}

void 
preOrderTraversal(struct binaryTreeNode *node){

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

void 
inOrderTraversal(struct binaryTreeNode *node){

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

void 
postOrderTraversal(struct binaryTreeNode *node){

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
