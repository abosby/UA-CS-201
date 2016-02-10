#include <stdio.h>
#include <stdlib.h>
#include "Timer.h"

/*Cnostructor for the Timer Class*/
struct timer *
newTimer(char t){
	struct timer *tT = malloc(sizeof(struct timer));
	tT->startTime;
	tT->endTime;
	tT->timerType = t;
	return tT;
	
}

void startTimer(struct timer *t){

	t->startTime = clock();
	return;
}

void stopTimer(struct timer *t){

	t->endTime = clock() - t->startTime;
	return;
}
