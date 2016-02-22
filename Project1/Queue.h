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

<<<<<<< HEAD
		void enqueue(int value);
		int dequeue();
        int isEmpty();
};

void Queue::enqueue(int value){
	node *tNode = new node;
	tNode->value = value;
	if (isEmpty()){
		front = tNode;
		rear = tNode;
	}
	else{
		rear->next = tNode;
		rear = tNode;
	}
}
=======
};

/*Methods*/
struct queueNode *newQueueNode();
void enqueue(struct queue *, struct stack *,struct binaryTreeNode *, int OptionD);
struct binaryTreeNode *dequeue(struct queue *, struct stack *, int optionD);
void dequeueRest(struct queue *, struct stack *, int optionD);
int isQueueEmpty(struct queue *);
int isLastQueueNode(struct queueNode *);
void printQueue(struct queue *);

>>>>>>> aa3cf8ad2fa2e73c989cfc9afb25d99700e90d21

/*Structure for the Queue Node Structure
	This is a Node that holds our Binary Tree Nodes*/
struct queueNode{

	/*vars*/
	struct binaryTreeNode *node;
	struct queueNode *next;
};

/*methods*/
struct queue *newQueue();

#endif
