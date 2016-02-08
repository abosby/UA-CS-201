#include <stdio.h>
#include <stdlib.h>
#include "Heaptree.h"
#include "Stack.h"

/*Constructor for the Stack Data Structure*/
struct stack *
newStack(){
    	struct stack *s = malloc(sizeof(struct stack));
	s->front = newBinaryTreeNode();
	s->rear = newBinaryTreeNode();
	s->size = 0;
	return s;
}

/*'Pushes' a Node structure to the front of the Stack (LIFO)*/
void 
push(struct stack *s, struct binaryTreeNode *n){
	//printf("Value is: %d\n",value);
	//printf("tNode->value is: %d\n",tNode->value);
	if (isStackEmpty(s) == 1){
		//tNode->next = NULL;
		s->front = n;
		s->rear = n;
		s->root = n;
		n->sNext = NULL;
		//printf("The root is: %d\n",s->root->value);
	}
	else{
		n->sNext = s->front;
		s->front = n;
		printf("On push | n parent is: %d\n",n->parent->value);
		printf("On push | n is: %d\n",n->value);
	}
	s->size += 1;
}

/*'Pops' a Node structure from the top of the Stack (LIFO)*/
struct binaryTreeNode *
pop(struct stack *s){
    	struct binaryTreeNode *tNode = malloc(sizeof(struct binaryTreeNode));
    	if (isStackEmpty(s) == 0){
		if(s->front == s->rear){
			tNode = s->front;	
			s->front = NULL;
			s->front = NULL;
		}
		else{
			tNode = s->front;
			s->front = s->front->sNext;
		}
		s->size -= 1;
		printf("tNode->parent is: %d\n",tNode->parent->value);
		printf("tNode is: %d\n",tNode->value);
		return tNode;
    	}
    	else{
        	return NULL;
    	}
}

/*Helper method to determine if the Stack Structure is Empty*/
int 
isStackEmpty(struct stack *s){

    	if (s->size == 0){
			return 1;
    	}
    	else{
			return 0;
    	}
}

/*Helper method to determine if the Node parameter is the Last Node in the Stack Structure*/
int 
isLastStackNode(struct binaryTreeNode *tNode){
    if (tNode->sNext == NULL){
        return 1;
    }
    else{
        return 0;
    }
}

/*Method that prints the contents of the Stack Structure*/
void 
printStack(struct stack *s){
	if(isStackEmpty(s) == 0){
		struct binaryTreeNode *tNode = malloc(sizeof(struct binaryTreeNode));
		tNode = s->front;
		printf("Stack Structure is \n");
		printf("Stack size is %d\n",s->size);
		while (isLastStackNode(tNode->sNext) == 0){
			printf("%d->",tNode->value);
			tNode = tNode->sNext;
		}
		printf("%d\n",tNode->value);
	}	
	return;
}

/*Method that prints the Preorder Traversal of the Heap*/
void
printStackPreorderTraversal(struct binaryTreeNode *n){
	if(n != NULL){
		printf("|%d|\n",n->value);
		if(n->left != NULL){
			printStackPreorderTraversal(n->left);
		}
		if(n->right != NULL){
			printStackPreorderTraversal(n->right);
		}
	}
	return;
}

/* heapsort algorithm runs in n log n time complexity 
	Time(n) - for each node in the tree
	Time(log n) - for the run time of siftUp(node)
*/
void 
heapSort(struct stack *s, int optionD){
	int i;
	int size = s->size;
	struct binaryTreeNode *tNode = malloc(sizeof(struct binaryTreeNode));
	for (i = 0; i < size-1; i++){
		tNode = pop(s);
		printf("\nPopped: %d\n",tNode->value);
		siftUp(tNode, optionD);
		printf("\nNew tree: \n\n");
		printStackPreorderTraversal(s->root);
	}	
}

/*Method that compares the Node parameter 'sifts' it up the
	tree until it reaches the appropriate position based off
	of optionD from the Command Line.
*/

void
siftUp(struct binaryTreeNode *n, int optionD){
	while(n->parent != NULL){
		//If optionD is 0 then Max-Ordered Heap
		printf("Parent is not null\n");
		if(optionD == 0){
			if(n->value > n->parent->value){
				printf("Wapping values");
				int temp;	
				temp = n->parent->value;
				n->parent->value = n->value;
				n->value = temp;
				n = n->parent;
			}
			else{
				return;
			}
		}
		//If optionD is 1 then Min-Ordered Heap
		else{
			//printf("n->value is: %d\n",n->value);
			//printf("n->parent is: %d\n",n->parent->value);
			if(n->value < n->parent->value){
				printf("Swapping values");
				int temp;	
				temp = n->parent->value;
				n->parent->value = n->value;
				n->value = temp;
				n = n->parent;
			}
			else{
				return;
			}
		}
	}	
}
