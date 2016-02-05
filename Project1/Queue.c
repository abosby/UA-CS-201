#include <stdio.h>
#include "Queue.h"

Queue::newQueue(){
    front = NULL;
    rear = NULL;
}

void Queue::enqueue(int value){
        node *tNode = new node;
        tNode->value = value;
        tNode->next = NULL;
        if (isEmpty()){
                front = tNode;
                rear = tNode;
        }
        else{
                rear->next = tNode;
                rear = tNode;
        }
}

int Queue::dequeue(){
        node *tNode = front;
        int temp;
        if (front == rear){
                temp = front->value;
                front == NULL;
                rear == NULL;
        }
        else{
                temp = front->value;
                front == front->next;
        }
        return temp;
}

int Queue::isEmpty(){

    if ((front == NULL) && (rear == NULL)){
        return 1;
    }
    else{
        return 0;
    }

}

void Queue::printQueue(){
        if(!isEmpty){
                node *tNode = front;
                printf("Queue Structure is \n");
                while(tNode->next != NULL){
                        printf(tNode->value + "\n");
                        tNode = tNode->next;
                }
                printf(tNode->value + "\n");
        }
}
