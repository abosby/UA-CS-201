#include "Heaptree.h"
#include "Stack.h"
#ifndef Queue
#define Queue

/*Structure for Queue Data Structure*/
struct queue{

	/*vars*/
	struct queueNode *front;
	struct queueNode *rear;
	int size;

};

struct queueNode{
	struct binaryTreeNode *node;
	struct queueNode *next;
};

/*methods*/
struct queue *newQueue();
struct queueNode *newQueueNode();
void enqueue(struct queue *, struct stack *,struct binaryTreeNode *, int OptionD);
struct binaryTreeNode *dequeue(struct queue *, struct stack *, int optionD);
void dequeueRest(struct queue *, struct stack *, int optionD);
int isLastQueueNode(struct queueNode *);
int isQueueEmpty(struct queue *);
void printQueue(struct queue *);
void printQueuePreorderTraversal(struct binaryTreeNode *);
void printQueueSorted(struct queue *);

#endif
