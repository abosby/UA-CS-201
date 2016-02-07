#include "Node.h"
//#ifndef Stack
//#define Stack

struct stack{

	/*vars*/
	struct node *front;
	struct node *rear;
	int size;

};


/*methods*/
struct stack *newStack();
void push(struct stack *, int value);
struct node *pop(struct stack *);
int isStackEmpty(struct stack *);
int isLastStackNode(struct node *);
void printStack(struct stack *);


//#endif
