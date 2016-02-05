#ifndef Stack
#define Stack

//struct
//{
//    int value;
//    struct node *next;
//} node;

typedef struct stack_type{

/*vars*/
struct node *front;
struct node *rear;

/*methods*/
void (*push)(int value);
struct node (*pop)();
int (*isEmpty)();
int (*isLast)(struct node *tNode);
void (*printStack)();

}Stack;

Stack *newStack();
#endif
