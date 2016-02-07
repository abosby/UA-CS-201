OBJs = Node.o Queue.o Stack.o Heaptree.o main.o

heapsort: $(OBJs)
	gcc -Wall -g $(OBJs) -o heapsort

heapsort.o: main.c
	gcc -Wall -g -c main.c

Stack.o: Stack.c Stack.h
	gcc -Wall -g -c Stack.c

Queue.o: Queue.c Queue.h
	gcc -Wall -g -c Queue.c

Node.o: Node.c Node.h
	gcc -Wall -g -c Node.c

Heaptree.o: Heaptree.c Heaptree.h
	gcc -Wall -g -c Heaptree.c

clean:
	rm -f *.o  

run:
	heapsort -d test.txt
