#include <stdio.h>
#include <stdlib.h>
#include "Queue.h"
#include "Djisktra.h"

/*Constructor for Binary Tree Node*/
struct djisktraNode *
newDjisktraNode(){
	struct djisktraNode *n = malloc(sizeof(struct djisktraNode));
	n->weight= (int)NULL;
	n->v1 = (int)NULL;
	n->v2 = (int)NULL;
	return n;
}

/*Inserts a Binary Tree Node into the Tree*/
void 
insert(struct djisktraNode *n){

	enqueue(n);
}

void printDjistkraNode(struct djisktraNode *n){
	printf("[&d &d (&d)]", n->v1, n->v2, n->weight);
}

