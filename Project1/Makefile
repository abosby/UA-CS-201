<<<<<<< HEAD
OBJs = main.c
=======
OBJs =  Heaptree.o Stack.o Queue.o main.o
>>>>>>> aa3cf8ad2fa2e73c989cfc9afb25d99700e90d21

heapsort: $(OBJs)
	gcc -Wall -g $(OBJs) -o heapsort

#heapsort.o: main.c
#	gcc -Wall -g -c main.c

<<<<<<< HEAD
#clean:
#	rm -f *.o

run:
	echo
	./heapsort -d test.txt
=======
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

>>>>>>> aa3cf8ad2fa2e73c989cfc9afb25d99700e90d21
