/*
 * Red-black tree checker, by John C. Lusth
 *
 *    See rbtchecker.c for details
 *
 *    This is the public interface for red-black trees
 *
 */

typedef struct tnode        /* this is a red-black tree node */
    {
    char *name;
    struct tnode *parent;
    struct tnode *left;
    struct tnode *right;
    int count;
    int color;
    char side;
    char *parentName;
    } tnode;

/************ Public Interface **************/

/* constructors */

extern tnode *readTree(char *);

/* integrity checkers */

extern void checkRed(tnode *);
extern int checkBalance(tnode *);
extern void checkOrder(tnode *);

/* color constants */

#define RED 0
#define BLACK 1
