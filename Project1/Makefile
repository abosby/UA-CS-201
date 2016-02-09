OBJs =  Stack.o Queue.c Heaptree.o main.o

heapsort: $(OBJs)
	gcc -Wall -g $(OBJs) -o heapsort

heapsort.o: main.c
	gcc -Wall -g -c main.c

Stack.o: Stack.c Stack.h
	gcc -Wall -g -c Stack.c

Queue.o: Queue.c Queue.h Stack.h
	gcc -Wall -g -c  Queue.c

Heaptree.o: Heaptree.c Heaptree.h
	gcc -Wall -g -c Heaptree.c

clean:
	rm -f *.o  

run:	clean heapsort
	heapsort -d test.txt
