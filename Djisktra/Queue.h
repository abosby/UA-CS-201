#include "Djisktra.h"
#ifndef Queue
#define Queue

/*Structure for Queue Data Structure*/
struct queue{

	/*vars*/
	struct queueNode *front;
	struct queueNode *rear;
	int size;

};

/*Methods*/
struct queueNode *newQueueNode();
void enqueue(struct queue *,struct djisktraNode *);
struct djiskstraNode *dequeue(struct queue *);
void dequeueRest(struct queue *);
int isQueueEmpty(struct queue *);
int isLastQueueNode(struct queueNode *);
void printQueue(struct queue *);


/*Structure for the Queue Node Structure
	This is a Node that holds our Binary Tree Nodes*/
struct queueNode{

	/*vars*/
	struct djisktraNode *node;
	struct queueNode *next;
};

/*methods*/
struct queue *newQueue();

#endif
