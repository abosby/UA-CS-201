OBJs =  Stack.o Heaptree.o Queue.o main.o

heapsort: $(OBJs)
	gcc -Wall -g $(OBJs) -o heapsort

heapsort.o: main.c
	gcc -Wall -g -c main.c

Stack.o: Stack.c Stack.h
	gcc -Wall -g -c Stack.c

Queue.o: Queue.c Queue.h Stack.h
	gcc -Wall -g -c  Queue.c

Heaptree.o: Heaptree.c Heaptree.h Stack.h
	gcc -Wall -g -c Heaptree.c

clean:
	rm -f *.o
	rm -fr heapsort.dSYM
run:	clean heapsort
	heapsort -d test.txt

ensureSorted: ensureSorted.o
	echo
	gcc -Wall -g ensureSorted.o -o ensureSorted

ensureSorted.o: ensureSorted.c
	gcc -Wall -g -c ensureSorted.c	

test: ensureSorted
	ensureSorted output.txt
	
