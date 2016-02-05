OBJs = Queue.o Stack.o main.o

heapsort: $(OBJs)
	gcc -Wall -g $(OBJs) -o heapsort

heapsort.o: main.c
	gcc -Wall -g -c main.c

Stack.o: Stack.h
	gcc -Wall -g -c Stack.h

Queue.o: Queue.h
	gcc -Wall -g -c Queue.h

#clean:
#	rm -f *.o  

run:
	echo
	heapsort -d test.txt
