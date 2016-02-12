#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include "Heaptree.h"
#include "Stack.h"

/*Constructor for the Stack Data Structure*/
struct stack *
newStack(){
    	struct stack *s = malloc(sizeof(struct stack));
	s->front = newStackNode();
	s->rear = newStackNode();
	s->size = 0;
	return s;
}

struct stackNode *newStackNode(){
	struct stackNode *s = malloc(sizeof(struct stackNode));
	s->node = NULL;
	s->next = NULL;
	return s;
}

/*'Pushes' a Node structure to the front of the Stack (LIFO)*/
void 
push(struct stack *s, struct binaryTreeNode *n, int optionD){
	//printf("Value is: %d\n",value);
	//printf("tNode->value is: %d\n",tNode->value);
	if (isStackEmpty(s) == 1){
		//tNode->next = NULL;
		s->front->node = n;
		s->front->next = NULL;
		s->rear->node = n;
		s->rear->next = NULL;
		s->start = clock();
	}
	else{
		struct stackNode *temp = malloc(sizeof(struct stackNode));
		temp->next = s->front;
		temp->node = n;
		s->front = temp;
		//siftUp(s->front, optionD);
		//printf("On push | n parent is: %d\n",n->parent->value);
		//printf("On push | n is: %d\n",n->value);
	}
//		//heapify(n,optionD);
	s->size += 1;
	//printf("Made it to the end of push\n");
	return;
	/*
	if(s->size == 1){
		printf("   n	| time  \n");
		printf("----------------\n");	
		printf("1	|%f	\n",0.000);
	}
	if(s->size == 10){
		printf("10	|%lu	\n",(clock()-s->start));
	}
	if(s->size == 100){
		printf("100	|%lu	\n",(clock()-s->start));
	}
	if(s->size == 1000){
		printf("1000	|%lu	\n",(clock()-s->start));
	}
	if(s->size == 10000){
		printf("10000	|%lu	\n",(clock()-s->start));
	}
	if(s->size == 100000){
		printf("100000	|%lu	\n",(clock()-s->start));
	}
	*/
	//if(s->size == 1000000){
	//	printf("1000000	|%lu	\n",(clock()-s->start));
	//}
}


/*'Pops' a Node structure from the top of the Stack (LIFO)*/
struct stackNode *
pop(struct stack *s, int optionD){
    	if (isStackEmpty(s) == 0){
    		struct stackNode *tNode = malloc(sizeof(struct stackNode));
		if(s->front == s->rear){
			tNode = s->front;	
			s->front = NULL;
			s->rear = NULL;
		}
		else{
			tNode = s->front;
			s->front = s->front->next;
		}
		s->size -= 1;
		//printf("tNode->parent is: %d\n",tNode->parent->value);
		//printf("tNode is: %d\n",tNode->value);
		//heapify(tNode->node, optionD);
		//printf("Popping %d\n",tNode->node->value);
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
isLastStackNode(struct stackNode *tNode, struct stack *s){
    	if ((tNode == s->rear) && (tNode == s->front)){
       		return 1;
    	}
    	else{
        	return 0;
    	}
}

/*Method that prints the contents of the Stack Structure*/
void 
printStack(struct stack *s){
	//printf("While printing stack");
	if(isStackEmpty(s) == 0){
		struct stackNode *tNode = malloc(sizeof(struct stackNode));
		tNode = s->front;
		printf("Stack Structure is \n");
		printf("Stack size is %d\n",s->size);
		while(tNode->next != NULL){
			printf("%d->",tNode->node->value);
			tNode = tNode->next;
		}
		printf("%d\n",tNode->node->value);
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
	//int i;
	struct stack *temp;
	temp = newStack();
	//int size = s->size;
	struct stackNode *tNode = malloc(sizeof(struct stackNode));
	tNode = s->front;
	while(isLastStackNode(tNode,s)!= 1){
	//for (i = 0; i < size; i++){
		//printf("\nPopped: %d\n",tNode->value);
		siftUp(tNode->node, optionD);
		tNode = tNode->next;
		//printf("\nNew tree: \n\n");
		//printStackPreorderTraversal(s->root);
	}	
}

/*Method that compares the Node parameter 'sifts' it up the
	tree until it reaches the appropriate position based off
	of optionD from the Command Line.
*/
void
siftUp(struct binaryTreeNode *n, int optionD){
	while(n->parent != NULL){
		//If optionD is 0 then Max-Ordered Heap //printf("Parent is not null\n");
		if(optionD == 0){
			if(n->value > n->parent->value){
				//printf("Wapping values");
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
				//printf("Swapping values");
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

/*Method that sifts the node down to the appropriate position
	runs in O(log n) tim
*/
/*
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
*/
void
printSortedStack(struct stack *s, int optionD){

	//printf("The original Stack is: \n");
	//printStackPreorderTraversal(s->root);
	//printf("\n\n");
	//printStack(s);
	//printf("made it past print stack\n");
	//printf("Stack size is: %d\n",s->size);
	//while(isStackEmpty(s) != 1){
	while(isLastStackNode(s->rear,s) == 0){
		int temp;
		printf("%d ",s->rear->node->value);
		temp = s->rear->node->value;
		s->rear->node->value = s->front->node->value;
		s->rear->node->value = temp;
		//if(s->front->parent->right != NULL){
	//		s->front->parent->right = NULL;
	//	}
	//	else{
	//		s->front->parent->left = NULL;
	//	}	
		//printf("Made it here");
		//printf("%d ",s->front->value);
		//printf("%d ", pop(s)->value);
		if(s->rear->node->parent->left == s->front->node){
			s->front->node->parent->left = NULL;
		}
		else{
			s->front->node->parent->right = NULL;	
		}
		pop(s,optionD);
		siftDown(s->front->node, optionD);
		//siftDown(s->rear, optionD);
		//printf("\nNew tree is: \n");
		//printStackPreorderTraversal(s->rear);
		//printf("\nNew stack is: \n");
		//printStack(s);
	}		
	printf("%d\n",s->rear->node->value);
	return;

}
