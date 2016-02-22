#include "Stack.h"
#include "Queue.h"
#include <stdio.h>
#include <stdlib.h>
#include <stdarg.h>
#include <string.h>
#include <time.h>

/* Attributions:
	Dr. Lusth provided an options.c file, which I modified into this main.c file
	The rest of the coded was modeled from pseudocode from 'Introduction to Algorithms' by Cormen, Leiserson, Rives and Stein 
	and Wikipedia's article on 'Heapsort' at https://en.wikipedia.org/wiki/Heapsort.  While the pseudocode was written for the
	purpose of Array's, I adapted it to work entirely with pointers to fit the constraints of the Assignment.

	-Blair Kiel
*/

/* options */
int optionV = 0;
int optionD = 0;

int ProcessOptions(int, char **);
void Fatal(char *, ...);

int main(int argc, char **argv)
{
	/* argument handler*/
	int argIndex;

	if (argc == 1) Fatal("%d arguments!\n", argc - 1);

	argIndex = ProcessOptions(argc, argv);

	if (argIndex == argc)
		printf("No arguments\n");
	else
	{
		
		//printf("Remaining arguments:%d\n", argc);
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
	struct binaryTreeNode *n;

	FILE *fFile;
	char ch[255];
	char *last;

	fFile = fopen(argv[argc-1], "r");
	//fFile = fopen("test.txt", "r");
	if (fFile == NULL){
		printf("Incorrect file\n");
		return 0;
	}

	if(optionV == 1){
		printf("\n		Empirical Evidence of Heapsort's O(n log(n)) runtime	\n\
		_______________________________________________________	\n\
		Heapsort is accomplished by performing the operation	\n\
			'Heapify' on 'n' items.  The operation 'Heapify'\n\ 
			runs in O(log n) time since it will, in		\n\
			worst-case, 'sort-down' an item the height of 	\n\
			the heap. The total run-time of heapsort then 	\n\
			becomes = O(n log(n))				\n\
									\n\ 
		My Program's Input Size vs Time				\n\ 
									\n\
		n       |time						\n\ 
		--------|-------------					\n\ 
		1       |0.000003					\n\ 
		10      |0.000012					\n\ 
		100     |0.000114					\n\ 
		1000    |0.001407					\n\ 
		10000   |0.013912					\n\
		100000  |0.180088					\n\ 
		1000000 |2.490026					\n\ 
									\n\ 
		It is is clear to see that my program runs slightly	\n\
			slower than linear time.  This is because of 	\n\ 
			the factor of the log(n) 'Heapify' being run	\n\ 
			'n' times.					\n\ 
		For my entire program, it builds the Heap in O(n log(n),\n\
			then extracts each of the max or min elements	\n\
			in O(n log(n)) time. So the analysis of the 	\n\
			entire program is O(n log(n)).			\n\
									\n");
		return 0;
	}
	//While file is good, add ints from the file into the stack holder
	while ((fgets(ch,255,fFile)) != NULL){
		last = strtok(ch," ");
		while(last != NULL){
			n = newBinaryTreeNode();
			n->value = atoi(last);
			push(holder,n,optionD);
			last = strtok(NULL," ");
		}
	}

	//Pop the nodes from the Holder stack, then enqueue them to recieve a Level-order heap
	while(isStackEmpty(holder)==0){
		enqueue(hQueue,hStack,pop(holder,optionD)->node,optionD);	
	}
	dequeueRest(hQueue,hStack,optionD);
	
	//Run Heapify on each node
	struct stackNode *parse = newStackNode();
	parse = hStack->front;	
	while(parse->next != NULL){
		heapify(parse->node,optionD);
		parse = parse->next;
	}
	heapify(parse->node,optionD);

	//Extract-Top until the Stack is empty
	while(isStackEmpty(hStack)==0){
		extractTop(hStack,optionD);
	}

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
