#include <time.h>
#ifndef Timer_H_
#define Timer_H_

/*Structure for Timer Class */

struct timer{

	clock_t startTime;
	clock_t endTime; 
	char timerType;

};

/*methods*/
struct timer *newTimer(char t);
void startTimer(struct timer *);
void stopTimer(struct timer *);
void printTable(struct timer *, int numN);


#endif
