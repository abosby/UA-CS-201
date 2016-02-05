#include <stdio.h>
#include <stdlib.h>
#include "Stack.h"

static node *
newStack(){
    front = NULL;
    rear = NULL;
}

void 
push(int value){
    node *tNode = new node;
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

node *
pop(){
    node *tNode = front;
    if (!isEmpty()){
        front = front->next;
        return tNode->value;
    }
    else{
        return NULL;
    }
}

int 
isEmpty(){
    if (front == NULL and rear == NULL){
        return 1;
    }
    else{
        return 0;
    }
}

int 
isLast(*node tNode){
    if (tNode->next == NULL){
        return 1;
    }
    else{
        return 0;
    }
}

void 
printStack(){
        node *tNode = front;
        printf("Stack Structure is \n");
        while (tNode->next != NULL){
                fprint(tNode->value + "\n");
                tNode = tNode->next;
        }
        fprint(tNode->value + "\n");
}
