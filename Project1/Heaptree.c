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
	return n;
}

void 
insert(struct binaryTree *t,struct binaryTreeNode *n){

	if(isHeapEmpty(t) == 1){
		t->root = n;
		t->size += 1;
	}
	else{

	}
	return;


}

int 
isHeapEmpty(struct binaryTree *t){

	if(t->size == 0){
		return 1;
	}
	else{
		return 0;	
	}

}

void
heapify(struct binaryTreeNode *n, int optionD){

	struct binaryTreeNode *tNode = newBinaryTreeNode();
	tNode = n;
	if(optionD == 1){
		if(n->left != NULL){
			if(n->left->value <= tNode->value){
				tNode = n->left;	
			}	
		}		
		if(n->right != NULL){
			if(n->right->value <= tNode->value){
				tNode = n->right;	
			}	
		}
		if(tNode != n){
			int temp = tNode->value;
			tNode->value = n->value;
			n->value = temp;
			heapify(tNode, optionD);	
		}
		return;
	}
	else{
		if(n->left != NULL){
			if(n->left->value >= tNode->value){
				tNode = n->left;
			}
		}
		if(n->right != NULL){
			if(n->right->value >= tNode->value){
				tNode = n->right;	
			}	
		}
		if(tNode != n){
			int temp = tNode->value;
			tNode->value = n->value;
			n->value = temp;
			heapify(tNode, optionD);
		}
		return;	
	}
}

void 
extractTop(struct stack *s, int optionD){
	if(s->size != 1){

		//Replace root with last element in heap
		int temp;
		temp = s->rear->node->value;
		printf("%d ",temp);
		s->rear->node->value = s->front->node->value;
		s->front->node->value = temp;
		
		if(s->front->node->parent->left == s->front->node){
			s->front->node->parent->left = NULL;
		}
		else{
			s->front->node->parent->right = NULL;
		}
		pop(s,optionD);

		//Max heapify
		heapify(s->rear->node,optionD);	
		//siftDown(s->front->node, optionD);	
	}
	else{
		printf("%d ",s->rear->node->value);			
		pop(s,optionD);
	}
	return;

}

/*Method that sifts the node down to the appropriate position
        runs in O(log n) tim
*/
void
siftDown(struct binaryTreeNode *n, int optionD){

        struct binaryTreeNode *swap;
        swap = newBinaryTreeNode();
        //If optionD is 0 then Max-Ordered Heap
        if(optionD == 0){
                if(n->left != NULL){
                        if(n->value < n->left->value){
                                swap = n->left;
                        }
                        else{
                                swap = n;
                        }
                        if(n->right != NULL){
                                if(swap->value < n->right->value){
                                        swap = n->right;        
                                }
                        }
                        if(swap == n){
                                return;
                        }
                        else{
                                int temp;
                                temp = swap->value;     
                                swap->value = n->value;
                                n->value = temp;
                                //n = swap;
                                siftDown(swap,optionD);
                        }
                }
                return;
        }
        //If optionD is 1 then Min-Ordered Heap
        else{
                if(n->left != NULL){
                        if(n->value > n->left->value){
                                swap = n->left;
                        }
                        else{
                                swap = n;
                        }
                        if(n->right != NULL){
                                if(swap->value > n->right->value){
                                        swap = n->right;        
                                }
                        }
                        if(swap == n){
                                return;
                        }
                        else{
                                int temp;
                                temp = swap->value;     
                                swap->value = n->value;
                                n->value = temp;
                                //n = swap;
                                siftDown(swap,optionD);
                        }
                }
                return;
        }
}



void 
preOrderTraversal(struct binaryTreeNode *node){

        if (node){
                //print node
                printf("|%d|\n", node->value);

                //traverse left
                if (node->left){
                        preOrderTraversal(node->left);
                }

                //traverse right
                if (node->right){
                        preOrderTraversal(node->right);
                }
        }
	return;
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
	return;
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
	return;
}
