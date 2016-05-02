#include <stdio.h>
#include <stdlib.h>
#include "Queue.h"

/* Constructor for the Queue data structure*/
struct queue *newQueue(){
	struct queue *q = malloc(sizeof(struct queue));
	q->front = newDjisktraNode();
	q->rear  = newDjisktraNode();
	q->size = 0;
	return q;
}

/* Constructor for the Queue Node Structure*/
struct djisktraNode *newDjisktraNode(){
	struct djisktraNode *q = malloc(sizeof(struct djisktraNode));
	q->v1 = (int) NULL;
	q->v2 = (int) NULL;
	q->weight = (int) NULL;
	q->next = NULL;
	return q;
}

/*Method that 'adds' the node parameter to the Queue (FIFO)
	Also builds the Heap Structure in level order.*/
void
enqueue(struct queue *q, struct djisktraNode *n){
	//Add node to the Queue
        if (isQueueEmpty(q) == 1){
		q->front = n;
		q->front->next = NULL;
		q->rear = q->front;
        }
        else{
		struct djisktraNode *temp = malloc(sizeof(struct djisktraNode));
		temp->next = NULL;
		temp = n;
                q->rear->next = temp;
                q->rear = temp;
        }
	q->size += 1;
	return;
}

/*'Removes' the Node that has 'Waited' the Longest in the Queue
	Also pushes the Node to our Stack Structure*/
struct djisktraNode * dequeue(struct queue *q){
        struct djisktraNode *temp = malloc(sizeof(struct djisktraNode));
	if (isQueueEmpty(q) == 0){
		if (q->front == q->rear){
			temp = q->front;
			q->front = NULL;
			q->front->next = NULL;
			q->rear = NULL;
			q->rear->next = NULL;
		}
		else{
			temp = q->front;
			q->front = q->front->next;
		}
		q->size -= 1;
		return temp;
	}
	else{
		return NULL;
	}
}

/*Helper Method adds the rest of the queue to our stack*/
void
dequeueRest(struct queue *q){
	while(isQueueEmpty(q) == 0){
		dequeue(q);
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
isLastQueueNode(struct djisktraNode *tNode){
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
                struct djisktraNode *tNode = malloc(sizeof(struct djisktraNode));
		tNode = q->front;
                printf("\nQueue Structure is \n");
		while(tNode->next != NULL){
                        printf("[(%d) %d-%d] -> ",tNode->weight,tNode->v1,tNode->v2);
                        tNode = tNode->next;
                }
                printf("[(%d) %d-%d]\n",tNode->weight,tNode->v1,tNode->v2);
        }
	return;
}
