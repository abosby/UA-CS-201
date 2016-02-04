OBJs = main.o

heapsort: $(OBJs)
	gcc -Wall -g $(OBJs) -o heapsort

heapsort.o: main.c
	gcc -Wall -g -c main.c

#clean:
#	rm -f *.o

run:
	echo
	heapsort -d test.txt
