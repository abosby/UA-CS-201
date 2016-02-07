#include <stdio.h>
#include <stdlib.h>
#include "Queue.h"

struct queue *newQueue(){
	struct queue *q = malloc(sizeof(struct queue));
	q->front = newNode();
	q->rear  = newNode();
	q->size = 0;
	return q;
}
void
enqueue(struct queue *q, int value){
        struct node *tNode = malloc(sizeof(struct queue));
        tNode->value = value;
	//printf("Value is: %d\n",value);
	//printf("tNode->value is: %d\n",tNode->value);
        if (isQueueEmpty(q) == 1){
		//printf("Queue is empty");
	
                q->front = tNode;
                q->rear = tNode;
        }
        else{
                q->rear->next = tNode;
                q->rear = tNode;
		//printf("q->rear->next = %d\n",q->rear->next->value);
        }
	q->size += 1;
	return;
}

struct node * 
dequeue(struct queue *q){
        struct node *temp = malloc(sizeof(struct queue));
	if (isQueueEmpty(q) == 0){
		if (q->front == q->rear){
			temp = q->front;
			q->front = NULL;
			q->rear = NULL;
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
int
isLastQueueNode(struct node *tNode){
	if(tNode->next == NULL){
		return 1;
	}
	else{
		return 0;
	}
}

void 
printQueue(struct queue *q){
        if(isQueueEmpty(q) == 0){
                struct node *tNode = malloc(sizeof(struct node));
		tNode = q->front;
                printf("Queue Structure is \n");
                while(isLastQueueNode(tNode) == 0){
                        printf("%d->",tNode->value);
                        tNode = tNode->next;
                }
                printf("%d\n",tNode->value);
        }
	return;
}
