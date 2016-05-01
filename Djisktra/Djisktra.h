#ifndef Djisktra_H_
#define Djisktra_H_
#include "Queue.h"
#include <stdio.h>
#include <stdlib.h>

/*Structure for Djisktra Edge*/
struct djisktraNode{

	/*vars*/
	int v1;
	int v2;
	int weight;

};

/*Methods*/
struct djisktraNode *newDjisktraNode();
void printNode();

/*Structure for Binary Tree*/
struct djisktraQueue{

	/*vars*/
	struct queue *djisktraQueue;
	int size;
		
};
	
/*methods*/
struct djisktraQueue *newQueue();
void addEdge(struct djisktraNode *);
void processDjisktra(int root);
void printDjisktraQueue();

#endif
