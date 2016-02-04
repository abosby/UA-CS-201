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
