/*
 * Red-black tree checker, by John C. Lusth
 *
 *    Reads in a specially formatted textual display of a red-black tree
 *    (see http://beastie.cs.ua.edu/cs201/assign2.html)
 *
 *    Performs the following checks on the resulting red-black tree:
 *        Checks for a red node with a red parent
 *        Checks that the height of the left child's
 *            subtree does not exceed twice that of the right's
 *            (and vice versa)
 *        Checks that BST ordering is maintained
 *            (not implemented, left as an exercise)
 *
 *    WARNING: this code is minimally tested and is not guaranteed to be
 *    correct. In other words, don't come complaining to me if this code
 *    says your red-black tree is correct and it isn't.
 */

#include <stdio.h>
#include <stdlib.h>
#include <stdarg.h>
#include "rbt.h"

void Fatal(char *,...);

int
main(int argc,char **argv)
    {
    tnode *rbt;
    if (argc != 2)
        Fatal("usage: rbtchecker FILENAME\n");

    rbt = readTree(argv[1]);

    printf("Checking for red parent with red child...\n");
    checkRed(rbt);
    printf("Red-red check passed.\n");
    printf("Checking for unbalanced nodes...\n");
    (void) checkBalance(rbt);
    printf("Balance check passed.\n");
    printf("Checking for BST ordering...\n");
    checkOrder(rbt);
    printf("BST ordering check passed.\n");
    return 0;
    }

void
Fatal(char *fmt, ...)
    {
    va_list ap;

    fprintf(stderr,"An error occured: ");
    va_start(ap, fmt);
    vfprintf(stderr, fmt, ap);
    va_end(ap);

    exit(-1);
    }

