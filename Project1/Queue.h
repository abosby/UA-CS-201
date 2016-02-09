#include "Heaptree.h"
#include "Stack.h"
#ifndef Queue
#define Queue

/*Structure for Queue Data Structure*/
struct queue{

	/*vars*/
	struct binaryTreeNode *root;
	struct binaryTreeNode *front;
	struct binaryTreeNode *rear;
	int size;

};


/*methods*/
struct queue *newQueue();
void enqueue(struct queue *, struct stack *,struct binaryTreeNode *);
struct binaryTreeNode *dequeue(struct queue *, struct stack *);
void dequeueRest(struct queue *, struct stack *);
int isLastQueueNode(struct binaryTreeNode *);
int isQueueEmpty(struct queue *);
void printQueue(struct queue *);
void printQueuePreorderTraversal(struct binaryTreeNode *);
void printQueueSorted(struct queue *);

#endif
