#include <stdio.h>
#include <stdlib.h>
#include "Stack.h"

struct node *
newNode(){
	struct node *n = malloc(sizeof(struct node));
	n->value = (int)NULL;
	n->next = NULL;
	return n;
}

void 
setValue(struct node *n, int value){
	n->value = value;
	return;
}

int
getValue(struct node *n){
	return n->value;
}

void setNext(struct node *n){
	n->next = n;
	return;
}

struct node *
getNext(struct node *n){
	return n->next;
}
