#ifndef Queue
#define Queue

struct {
	int value;
	struct node *next;
} node;

typedef struct queue_object{

/*vars*/
struct node *front;
struct node *rear;

/*methods*/
void (*enqueue)(int value);
struct node (*dequeue)();
int (*isEmpty)();
void (*printQueue)();

}Queue;

Queue *newQueue();
#endif
