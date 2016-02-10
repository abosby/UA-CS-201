#include "Node.h"
#include "Heaptree.h"
#include <time.h>
#ifndef Stack
#define Stack

/*Structure for Stack Data Structure*/
struct stack{

	/*vars*/
	struct binaryTreeNode *root;
	struct binaryTreeNode *front;
	struct binaryTreeNode *rear;
	int size;
	clock_t start;

};


/*methods*/
struct stack *newStack();
void push(struct stack *, struct binaryTreeNode *, int optionD);
struct binaryTreeNode *pop(struct stack *);
int isStackEmpty(struct stack *);
int isLastStackNode(struct binaryTreeNode *, struct stack *);
void printStack(struct stack *);
void printStackPreorderTraversal(struct binaryTreeNode *n);
void heapSort(struct stack *, int optionD);
void siftUp(struct binaryTreeNode *, int optionD);
void siftDown(struct binaryTreeNode *, int optionD);
void printSortedStack(struct stack *, int optionD);


#endif
