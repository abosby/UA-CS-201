COBJS = rbtchecker.o queue.o rbt.o scanner.o

rbtchecker : $(COBJS)
		gcc -Wall -g -o rbtchecker $(COBJS)

rbtchecker.o :	rbtchecker.c queue.h rbt.h
		gcc -Wall -g -c rbtchecker.c

queue.o :	queue.c queue.h rbt.h
		gcc -Wall -g -c queue.c

rbt.o :	rbt.c rbt.h
		gcc -Wall -g -c rbt.c

scanner.o	: scanner.c scanner.h
		gcc -Wall -g -c scanner.c

tar	:
		tar cvfz rbtchecker.tgz Makefile rbtchecker.c queue.[ch] rbt.[ch] scanner.[ch] data

clean	:
		rm -f $(COBJS) rbtchecker trees
