#include <time.h>
#include "Heaptree.h"
#ifndef Stack_H_
#define Stack_H_

/*Structure for Stack Data Structure*/
struct stack{

<<<<<<< HEAD
class Stack{

    private:
        node *front;
        node *rear;
    public:

        void push(int value);
        int pop();
        int isEmpty();

};

void Stack::push(int value){
    node *tNode = new Node;
    tNode->value = value;
    if (isEmpty()){
        front = tNode;
        rear = tNode;
    }
    else{
        node temp = front;
        tNode->next = front;
        front = tNode;
    }
}

int Stack::pop(){
    node *tNode = front;
    if (!isEmpty()){
        front = front->next;
    }
    else{
        //empty
    }
}
=======
	/*vars*/
	struct stackNode *front;
	struct stackNode *rear;
	int size;
	clock_t start;

};

/*Structure for the Stack Node Structure
	This is a Node that holds our Binary Tree Nodes*/
struct stackNode{
	
	/*vars*/
	struct binaryTreeNode *node;
	struct stackNode *next;
};

/*Methods*/
struct stackNode *newStackNode();
struct stack *newStack();
void push(struct stack *s, struct binaryTreeNode *n, int optionD);
struct stackNode *pop(struct stack *, int optionD);
int isStackEmpty(struct stack *);
int isLastStackNode(struct stackNode *, struct stack *);
void printStack(struct stack *);


#endif
>>>>>>> aa3cf8ad2fa2e73c989cfc9afb25d99700e90d21
