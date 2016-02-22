#include <stdio.h>
#include <stdlib.h>
#include <stdarg.h>
#include <string.h>

int main(int argc, char **argv)
{

	FILE *fFile;
	//char ch[255];
	//char *cur;
	//char *last;
	int cur = 0;
	int last = 0;
	int counter = 0;
	
	fFile = fopen(argv[argc-1], "r");

	if (fFile == NULL){
		printf("Incorrect file\n");
	}	

	/*while ((fgets(ch,255,fFile))!= NULL){
		cur = strtok(ch," ");
		//printf("cur is %d\n",atoi(cur));
		while(cur != NULL){
			//printf("cur is %d\n",atoi(cur));
			last = cur;
			cur = strtok(NULL, " ");
		}
		if((int)cur < (int)last){
			printf("Last | cur: %d | %d \n",(int)cur,(int)last);
			counter += 1;
		}
	}
	*/
	while(!feof (fFile)){

		fscanf(fFile, "%d", &cur);
		if((int)cur < (int)last){
			printf("Last | cur: %d | %d \n",last,cur);
			counter += 1;
		}
		last = cur;	

	}
	fclose(fFile);

	if(counter == 0){
		printf("The ints are sorted \n");		
	}
	else{
		printf("There are %d ints out of order", counter);
	}

	return 0;
}
