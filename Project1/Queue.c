#include <stdio.h>
#include <stdlib.h>
#include "Heaptree.h"
#include "Queue.h"

struct queue *newQueue(){
	struct queue *q = malloc(sizeof(struct queue));
	q->root = newBinaryTreeNode();
	q->front = newBinaryTreeNode();
	q->rear  = newBinaryTreeNode();
	q->size = 0;
	return q;
}

void
enqueue(struct queue *q, struct binaryTreeNode *n){
	//printf("Value is: %d\n",value);
	//printf("tNode->value is: %d\n",tNode->value);
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
		//if node is 'COMPLETE'
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
			dequeue(q);
			q->front->left = n;
			n->parent = q->front;
		}	
        }
	
	q->size += 1;
	return;
}

struct binaryTreeNode * 
dequeue(struct queue *q){
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
isLastQueueNode(struct binaryTreeNode *tNode){
	if(tNode->qNext == NULL){
		return 1;
	}
	else{
		return 0;
	}
}

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

void
printQueueSorted(struct queue *q){
	
}
