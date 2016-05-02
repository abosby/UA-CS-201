#ifndef Queue
#define Queue

/*Structure for Queue Data Structure*/
struct queue{

	/*vars*/
	struct djisktraNode *front;
	struct djisktraNode *rear;
	int size;

};

/*Methods*/
struct queueNode *newQueueNode();
void enqueue(struct queue *,struct djisktraNode *);
struct djisktraNode *dequeue(struct queue *);
void dequeueRest(struct queue *);
int isQueueEmpty(struct queue *);
int isLastQueueNode(struct djisktraNode *);
void printQueue(struct queue *);


/*Structure for Djisktra Edge*/
struct djisktraNode{

	/*vars*/
	int v1;
	int v2;
	int weight;
	struct djisktraNode *next;

};

/*Methods*/
struct djisktraNode *newDjisktraNode();
void printNode();

/*methods*/
struct queue *newQueue();

#endif
