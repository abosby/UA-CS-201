#include <stdio.h>
#include <stdlib.h>
#include "Heaptree.h"
#include "Queue.h"
#include "Stack.h"

/* Constructor for the Queue data structure*/
struct queue *newQueue(){
	struct queue *q = malloc(sizeof(struct queue));
	q->root = newBinaryTreeNode();
	q->front = newBinaryTreeNode();
	q->rear  = newBinaryTreeNode();
	q->size = 0;
	return q;
}

/*Method that 'adds' the node parameter to the Queue (FIFO)
	Also builds the Heap Structure in level order.*/
void
enqueue(struct queue *q, struct stack *s, struct binaryTreeNode *n){
	//printf("Value is: %d\n",value);
	//printf("tNode->value is: %d\n",tNode->value);

		//ENQUEUE
        if (isQueueEmpty(q) == 1){
				//printf("Queue is empty");
				q->root = n;	
                q->front = n;
                q->rear = n;
        }
        else{
                q->rear->qNext = n;
                q->rear = n;
		//printf("q->rear->next = %d\n",q->rear->next->value);

		//'Adds' the Node to the Heap in level order
		if(q->front->left == NULL){
			q->front->left = n;	
			n->parent = q->front;
		}
		else if(q->front->right == NULL){
			q->front->right = n;
			n->parent = q->front;
		}
		else{
			//struct binaryTreeNode *dequeue(q);		
			dequeue(q,s);
			q->front->left = n;
			n->parent = q->front;
		}	
        }
	
	q->size += 1;
	return;
}

/*'Removes' the Node that has 'Waited' in the Queue the longest*/
struct binaryTreeNode * 
dequeue(struct queue *q, struct stack *s){
        struct binaryTreeNode *temp = malloc(sizeof(struct binaryTreeNode));
	if (isQueueEmpty(q) == 0){
		if (q->front == q->rear){
			temp = q->front;
			q->front = NULL;
			q->rear = NULL;
		}
		else{
			temp = q->front;
			q->front = q->front->qNext;
		}
		q->size -= 1;
		push(s,temp);
		return temp;
	}
	else{
		return NULL;
	}
}

/*Adds the rest of the queue to our stack*/
void
dequeueRest(struct queue *q, struct stack *s){
	while(isQueueEmpty(q) == 0){
		dequeue(q,s);
	}
}

/*Helper method that determines if a Queue is empty*/
int 
isQueueEmpty(struct queue *q){

	//printf("Checking if empty\n");
	if (q->size == 0){
		//printf("Queue is empty\n");
		return 1;
	}
	else{
		//printf("Queue is not empty\n");
		return 0;
	}

}

/*Helper method that determines if the Node parameter is the last Node in the Structure*/
int
isLastQueueNode(struct binaryTreeNode *tNode){
	if(tNode->qNext == NULL){
		return 1;
	}
	else{
		return 0;
	}
}


/*Method that prints the Linked List structure of the Queue*/
void 
printQueue(struct queue *q){
        if(isQueueEmpty(q) == 0){
                struct binaryTreeNode *tNode = malloc(sizeof(struct binaryTreeNode));
				tNode = q->front;
                printf("Queue Structure is \n");
                while(isLastQueueNode(tNode) == 0){
                        printf("%d->",tNode->value);
                        tNode = tNode->qNext;
                }
                printf("%d\n",tNode->value);
        }
	return;
}

/*Method that prints the preorder traversal of the Heap*/
void
printQueuePreorderTraversal(struct binaryTreeNode *n){
	if(n != NULL){
		printf("|%d|\n",n->value);
		if(n->left != NULL){
			printQueuePreorderTraversal(n->left);
		}

		if(n->right != NULL){
			printQueuePreorderTraversal(n->right);
		}
	}
	return;
}

/*Method that prints the Queue in order*/
void
printQueueSorted(struct queue *q){
	
}
