#include "Node.h"
#include "Heaptree.h"
//#ifndef Stack
//#define Stack

struct stack{

	/*vars*/
	struct binaryTreeNode *root;
	struct binaryTreeNode *front;
	struct binaryTreeNode *rear;
	int size;

};


/*methods*/
struct stack *newStack();
void push(struct stack *, struct binaryTreeNode *);
struct binaryTreeNode *pop(struct stack *);
int isStackEmpty(struct stack *);
int isLastStackNode(struct binaryTreeNode *);
void printStack(struct stack *);
void printStackPreorderTraversal(struct binaryTreeNode *n);
void heapSort(struct stack *, int optionD);
void siftUp(struct binaryTreeNode *, int optionD);


//#endif
