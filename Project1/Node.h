#ifndef Node
#define Node

struct node{
	/*vars*/
	int value;
	struct node *next;
};


/*methods*/
struct node *newNode();
void setValue(struct node *n, int value);
int getValue(struct node *n);
void setNext(struct node *n);
struct node *getNext(struct node *n);

#endif
