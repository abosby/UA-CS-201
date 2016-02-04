#include <stdio.h>

struct node{
    int value;
    node *next;
}

class Stack{

    private:
        node *front;
        node *rear;
    public:

        void Stack();
        void push(int value);
        *node pop();
        int isEmpty();
        int isLast(*node tNode);

};

void Stack::Stack(){
    front = NULL;
    rear = NULL; 
}

void Stack::push(int value){
    node *tNode = new Node;
    tNode->value = value;
    if (isEmpty()){
        tNode->next = NULL;
        front = tNode;
        rear = tNode;
    }
    else{
        tNode->next = front;
        front = tNode;
    }
}

*node Stack::pop(){
    node *tNode = front;
    if (!isEmpty()){
        front = front->next;
        return tNode->value;
    }
    else{
        return NULL;
    }
}

int Stack::isEmpty(){
    if (front == NULL and rear == NULL){
        return 1;
    }
    else{
        return 0;
    }
}

int Stack::isLast(*node tNode){
    if (tNode->next == NULL){
        return 1;
    }
    else{
        return 0;
    }
}

