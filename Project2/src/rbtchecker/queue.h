/*
 * Red-black tree checker, by John C. Lusth
 *
 *    See rbtchecker.c for details
 *
 *    This is the public interface for the queue that is used to
 *    rebuild the red-black tree.
 */

typedef struct qnode
    {
    struct qnode *next;
    struct tnode *value;           /* tnode is a red-black tree node */
    } qnode;

typedef struct queue
    {
    qnode *head;
    qnode *tail;
    } queue;

/************ Public Interface **************/

/* constructors */

queue *newQueue(void);

/* standard queue methods */

tnode *peek(queue *q);
void enqueue(queue *q,tnode *value);
tnode *dequeue(queue *q);
int isEmpty(queue *q);
