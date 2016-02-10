#include "Stack.h"
#include "Queue.h"
#include "Node.h"
#include <stdio.h>
#include <stdlib.h>
#include <stdarg.h>
#include <string.h>
#include <time.h>

/* options */
int optionV = 0;
int optionD = 0;
clock_t start;
//int Special = 0;    /* option -s      */
//int Number = 0;     /* option -n XXXX */
//char* Name = 0;     /* option -N YYYY */

int ProcessOptions(int, char **);
void Fatal(char *, ...);

int main(int argc, char **argv)
{
	/* argument handler*/
	int argIndex;

	if (argc == 1) Fatal("%d arguments!\n", argc - 1);

	argIndex = ProcessOptions(argc, argv);

	printf("optionV is %s\n", optionV == 0 ? "false" : "true");
	printf("optionD is %s\n", optionD == 0 ? "false" : "true");

	if (argIndex == argc)
		printf("No arguments\n");
	else
	{
		
		printf("Remaining arguments:%d\n", argc);
		//int i;
		/*for (i = argIndex; i < argc; ++i){
			printf("argc is %d\n",argc);
			printf("argIndex is %d\n",argIndex);
			printf("i is %d\n",i);
			printf("argv[i] is %s\n",argv[i]);
			//printf("    %s\n", argv[i-1]);
			printf("made it here");
		}
		*/
		
	}

	/* Main Program */
	struct queue *hQueue = newQueue();
	struct stack *hStack = newStack();
	struct stack *holder = newStack();
	//hQueue = newQueue();
	//Stack hStack = newStack();


	/*Read in values*/
	FILE *fFile;
	char ch[255];
	char *last;
	fFile = fopen(argv[argc-1], "r");
	//fFile = fopen("test.txt", "r");

	if (fFile == NULL){
		printf("Incorrect file\n");
	}
	struct binaryTreeNode *n;
	while ((fgets(ch,255,fFile)) != NULL){
		last = strtok(ch," ");
		while(last != NULL){
			//printf("%s\n",last);
			n = newBinaryTreeNode();
			n->value = atoi(last);
			push(holder,n,optionD);
			//push(sStack,n);
			last = strtok(NULL," ");
		}
		//if (last == ' '){
			//end of token add token to heap
			//printf ("%ch", ch);
        		//ch = fgetc(fFile);
		//	printf(word + '\n');
			//memset(word, "", 255);
		//}
		//else{
			//add to word
		//	strcat(word, ch);
		//}
	}
	start = clock();
	while(isStackEmpty(holder)==0){
		enqueue(hQueue,hStack,pop(holder),optionD);	
	}
	//Performing Heapsort in (n log(n)) time
	//struct stack *oStack = newStack();
	//oStack = hStack;
	dequeueRest(hQueue,hStack, optionD);
	printf("Time is %f\n",(double)(clock()-start)/CLOCKS_PER_SEC);
	//heapSort(hStack, optionD);
	printSortedStack(hStack, optionD);
	//printf("The Stack preorder of the two is:\n");
	//printStackPreorderTraversal(hStack->root);
	//printStackPreorderTraversal(oStack->root);
	//printQueue(hQueue);
	//printStack(hStack);
	//printf("The Queue Preorder is:\n");
	//printQueuePreorderTraversal(hQueue->root);
	//printf("The Stack Preorder is:\n");
	//printStackPreorderTraversal(hStack->root);
	//printQueueSorted(hQueue);

	fclose(fFile);
	return 0;

}

void Fatal(char *fmt, ...)
{
	va_list ap;

	fprintf(stderr, "An error occured: ");
	va_start(ap, fmt);
	vfprintf(stderr, fmt, ap);
	va_end(ap);

	exit(-1);
}

/* only -oXXX  or -o XXX options */

int ProcessOptions(int argc, char **argv)
{
	int argIndex;
	int argUsed;
	int separateArg;
	char *arg;

	argIndex = 1;

	while (argIndex < argc && *argv[argIndex] == '-')
	{
		/* check if stdin, represented by "-" is an argument */
		/* if so, the end of options has been reached */
		if (argv[argIndex][1] == '\0') return argIndex;

		separateArg = 0;
		argUsed = 0;

		if (argv[argIndex][2] == '\0')
		{
			arg = argv[argIndex + 1];
			separateArg = 1;
		}
		else
			arg = argv[argIndex] + 2;

		switch (argv[argIndex][1])
		{
			/*
			* when option has an argument, do this
			*
			*     examples are -m4096 or -m 4096
			*
			*     case 'm':
			*         MemorySize = atol(arg);
			*         argUsed = 1;
			*         break;
			*
			*
			* when option does not have an argument, do this
			*
			*     example is -a
			*
			*     case 'a':
			*         PrintActions = 1;
			*         break;
			*/

		case 'v': /* give the author's name and explanation on how the implementation performs the sorting in 0(n log n) time
				  -also give some empirical evidence (such as a table of input sizes versus time) that indicates your sort runs in 0(n log n) time - then immediatley exit */
			optionV = 1;
			break;
		case 'd': /* sort in decreasing order (default is increasing order) */
			optionD = 1;
			break;
		default:

			Fatal("option %s not understood\n", argv[argIndex]);
		}

		if (separateArg && argUsed)
			++argIndex;

		++argIndex;
	}

	return argIndex;
}
