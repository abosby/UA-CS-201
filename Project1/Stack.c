#include <stdio.h>
#include <stdlib.h>
#include "Stack.h"

struct stack *
newStack(){
    	struct stack *s = malloc(sizeof(struct stack));
	s->front = newNode();
	s->rear = newNode();
	s->size = 0;
	return s;
}

void 
push(struct stack *s, int value){
	struct node *tNode = malloc(sizeof(struct node));
	tNode->value = value;
	//printf("Value is: %d\n",value);
	//printf("tNode->value is: %d\n",tNode->value);
	if (isStackEmpty(s) == 1){
		//tNode->next = NULL;
		s->front = tNode;
		s->rear = tNode;
	}
	else{
		tNode->next = s->front;
		s->front = tNode;
	}
}

struct node *
pop(struct stack *s){
    	struct node *tNode = malloc(sizeof(struct node));
    	if (isStackEmpty(s) == 0){
		if(s->front == s->rear){
			tNode = s->front;	
			s->front = NULL;
			s->front = NULL;
		}
		else{
			tNode = s->front;
			s->front = s->front->next;
		}
		return tNode;
    	}
    	else{
        	return NULL;
    	}
}

int 
isStackEmpty(struct stack *s){
    if (s->front == s->rear ){
        return 1;
    }
    else{
        return 0;
    }
}

int 
isLastStackNode(struct node *tNode){
    if (tNode->next == NULL){
        return 1;
    }
    else{
        return 0;
    }
}

void 
printStack(struct stack *s){
	if(isStackEmpty(s) == 0){
		struct node *tNode = malloc(sizeof(struct node));
		tNode = s->front;
		printf("Stack Structure is \n");
		while (isLastStackNode(tNode->next) == 0){
			printf("%d->",tNode->value);
			tNode = tNode->next;
		}
		printf("%d\n",tNode->value);
	}	
	return;
}
