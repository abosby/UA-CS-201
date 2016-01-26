#include <stdio.h>

struct node{
	int value;
	node *next;
};

class Queue{

	private:
		node *front;
		node *rear;

	public:

		void enqueue(int value);
		int dequeue();
};

void Queue::enqueue(int value){
	node *tNode = new node;
	tNode->value = value;
	if ((front == NULL) && (rear == NULL)){
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

