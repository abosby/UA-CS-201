#include "Heaptree.h"
#ifndef Queue
#define Queue

struct queue{

	/*vars*/
	struct binaryTreeNode *root;
	struct binaryTreeNode *front;
	struct binaryTreeNode *rear;
	int size;

};


/*methods*/
struct queue *newQueue();
void enqueue(struct queue *, struct binaryTreeNode *);
struct binaryTreeNode *dequeue(struct queue *);
int isLastQueueNode(struct binaryTreeNode *);
int isQueueEmpty(struct queue *);
void printQueue(struct queue *);
void printQueuePreorderTraversal(struct binaryTreeNode *);
void printQueueSorted(struct queue *);

#endif
