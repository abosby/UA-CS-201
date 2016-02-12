#include <stdio.h>
#include <stdlib.h>
#include "Heaptree.h"
#include "Queue.h"
#include "Stack.h"

/* Constructor for the Queue data structure*/
struct queue *newQueue(){
	struct queue *q = malloc(sizeof(struct queue));
	q->front = newQueueNode();
	q->rear  = newQueueNode();
	q->size = 0;
	return q;
}

struct queueNode *newQueueNode(){
	struct queueNode *q = malloc(sizeof(struct queueNode));
	q->node = NULL;
	q->next = NULL;
	return q;
}

/*Method that 'adds' the node parameter to the Queue (FIFO)
	Also builds the Heap Structure in level order.*/
void
enqueue(struct queue *q, struct stack *s, struct binaryTreeNode *n, int optionD){
	//printf("Value is: %d\n",value);
	//printf("tNode->value is: %d\n",tNode->value);

		//ENQUEUE
	printf("Adding %d to the Queue\n",n->value);
        if (isQueueEmpty(q) == 1){
				//printf("Queue is empty");
		q->front->node = n;
		q->front->next = NULL;
		q->rear->node = n;
		q->rear->next = NULL;
        }
        else{
		struct queueNode *temp = malloc(sizeof(struct queueNode));
		temp->next = NULL;
		temp->node = n;
                q->rear->next = temp;
                q->rear = temp;
		//printf("q->rear->next = %d\n",q->rear->next->value);

		//'Adds' the Node to the Heap in level order
		if(q->front->node->left == NULL){
			q->front->node->left = n;	
			n->parent = q->front->node;
		}
		else if(q->front->node->right == NULL){
			q->front->node->right = n;
			printf("Made it back here\n");
			n->parent = q->front->node;
		}
		else{
			//struct binaryTreeNode *dequeue(q);		
			printf("Dequeueing\n");
			dequeue(q,s, optionD);
			printf("there is no front");
			printf("q->front is %d\n",q->front->node->value);
			printf("q->front->left is %d\n",q->front->node->left->value);
			q->front->node->left = n;
			printf("Made it here in the queue\n");
			n->parent = q->front->node;
		}	
        }
	
	q->size += 1;
	return;
}

/*'Removes' the Node that has 'Waited' in the Queue the longest*/
struct binaryTreeNode * 
dequeue(struct queue *q, struct stack *s, int optionD){
        struct binaryTreeNode *temp = malloc(sizeof(struct binaryTreeNode));
	if (isQueueEmpty(q) == 0){
		if (q->front == q->rear){
			temp = q->front->node;
			q->front->node = NULL;
			q->front->next = NULL;
			q->rear->node = NULL;
			q->rear->next = NULL;
		}
		else{
			temp = q->front->node;
			q->front = q->front->next;
			printf("Made it here in dequeue\n");
			//printf("New q->front is %d\n",q->front->node->value);
		}
		q->size -= 1;
		push(s,temp, optionD);
		printf("Made it here in the queue value: %d\n",temp->value);
		return temp;
	}
	else{
		return NULL;
	}
}

/*Adds the rest of the queue to our stack*/
void
dequeueRest(struct queue *q, struct stack *s, int optionD){
	while(isQueueEmpty(q) == 0){
		dequeue(q,s,optionD);
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
isLastQueueNode(struct queueNode *tNode){
	if(tNode->next == NULL){
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
                struct queueNode *tNode = malloc(sizeof(struct queueNode));
		tNode = q->front;
                printf("Queue Structure is \n");
                while(isLastQueueNode(tNode) == 0){
                        printf("%d->",tNode->node->value);
                        tNode = tNode->next;
                }
                printf("%d\n",tNode->node->value);
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
