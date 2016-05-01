OBJs =  Queue.o main.o Djisktra.o

djisktra: $(OBJs)
	gcc -Wall -g $(OBJs) -o djikstra 

main.o: main.c
	gcc -Wall -g -c main.c

Queue.o: Queue.c Queue.h
	gcc -Wall -g -c Queue.c

Djikstra.o: Djisktra.h
	gcc -Wall -g -c Djisktra.c

clean:
	rm -f *.o
	rm -fr djisktra.dSYM

run:	clean djikstra 
	./djiksra.exe -d test.txt
