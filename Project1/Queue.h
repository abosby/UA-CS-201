#include "Node.h"
//#ifndef Queue
//#define Queue

struct queue{

	/*vars*/
	struct node *front;
	struct node *rear;
	int size;

};


/*methods*/
struct queue *newQueue();
void enqueue(struct queue *, int value);
struct node *dequeue(struct queue *);
int isLastQueueNode(struct node *);
int isQueueEmpty(struct queue *);
void printQueue(struct queue *);

//#endif
