#include "Node.h"
#include "Heaptree.h"
#include <time.h>
#ifndef Stack
#define Stack

/*Structure for Stack Data Structure*/
struct stack{

	/*vars*/
	struct stackNode *front;
	struct stackNode *rear;
	int size;
	clock_t start;

};

struct stackNode{
	struct binaryTreeNode *node;
	struct stackNode *next;
};


/*methods*/
struct stack *newStack();
struct stackNode *newStackNode();
void push(struct stack *, struct binaryTreeNode *, int optionD);
struct stackNode *pop(struct stack *, int optionD);
int isStackEmpty(struct stack *);
int isLastStackNode(struct stackNode *, struct stack *);
void printStack(struct stack *);
//void printStackPreorderTraversal(struct binaryTreeNode *n);
void heapSort(struct stack *, int optionD);
void siftUp(struct binaryTreeNode *, int optionD);
void siftDown(struct binaryTreeNode *, int optionD);
void printSortedStack(struct stack *, int optionD);


#endif
