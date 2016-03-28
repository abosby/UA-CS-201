/*
 * Red-black tree checker, by John C. Lusth
 *
 *    See rbtchecker.c for details
 *
 *    This is the implementation for red-black trees
 *
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include "scanner.h"
#include "rbt.h"
#include "queue.h"

extern void Fatal(char *,...);

static tnode *textToNode(char *);
static void setChild(tnode *,tnode *);
static tnode *newTNode(char *,int,int,tnode *,tnode *,char *,char);

/**************** Public Methods *********************/

/* constructors */

tnode *
readTree(char *filename)
    {
    tnode *root = 0;

    FILE *fp;
    if ((fp = fopen(filename,"r")) == 0)
        Fatal("file %s could not be opened for reading.\n",filename);

    queue *q = newQueue();
    char *t;
    
    t = readToken(fp); //skip the first line number
    t = readToken(fp);
    root = textToNode(t);
    root->parent = root;
    if (root->side != 'X')
        Fatal("root node did not have X designator: %c\n",root->side);
    enqueue(q,root);

    t = readToken(fp);
    while (!feof(fp))
        {
        tnode *tn;
        if (isalpha(*t) || *t == '=')
            {
            tn = textToNode(t);
            enqueue(q,tn);
            //find the parent, it must be on the queue
            while (strcmp(peek(q)->name,tn->parentName) != 0)
                {
                dequeue(q);
                if (isEmpty(q))
                    Fatal("node %s has a misnamed parent: %s\n",
                        tn->name,tn->parentName);
                }
            setChild(peek(q),tn);
            }
        t = readToken(fp);
        }
    return root;
    }

void
checkRed(tnode *t)
    {
    if (t == 0) return;
    if (t->color == RED && t->parent->color == RED)
        Fatal("Node %s is red and has a red parent.\n",t->name);
    checkRed(t->left);
    checkRed(t->right);
    }

int
checkBalance(tnode *t)
    {
    if (t == 0) return 0;
    int LH = checkBalance(t->left);
    int RH = checkBalance(t->right);
    if (LH < RH)
        {
        if (RH / 2 > LH)
            Fatal("Node %s is unbalanced; right height is too large.\n",
                t->name);
        return RH + 1;
        }
    else
        {
        if (LH / 2 > RH)
            Fatal("Node %s is unbalanced; left height is too large.\n",
                t->name);
        return LH + 1;
        }
    }

void
checkOrder(tnode *t)
    {
		if (t != NULL) {
			if (t->side == 'X') {
				if (t->left != NULL) {
					checkOrder(t->left);
				}
				if (t->right != NULL) {
					checkOrder(t->right);
				}
			}
			else if (t->side == 'L') {
				if (strcmp(t->name, t->parent->name) > 0) {
					Fatal("Node %s 'L' is out of order with its parent %s", t->name, t->parent->name);
				}
				if (t->left != NULL) {
					checkOrder(t->left);
				}
				if (t->right != NULL) {
					checkOrder(t->right);
				}
			}
			else if (t->side == 'R') {
				if (strcmp(t->name, t->parent->name) < 0) {
					Fatal("Node %s 'R' is out of order with its parent %s", t->name, t->parent->name);
				}
				if (t->left != NULL) {
					checkOrder(t->left);
				}
				if (t->right != NULL) {
					checkOrder(t->right);
				}
			}
		}
    }


/**************** Private Methods ********************/

/* constructors */

static tnode *
newTNode(char *n,int count,int color,tnode *left,tnode *right,char *pn,char s)
    {
    tnode *tn = malloc(sizeof(tnode));
    if (tn == 0)
        Fatal("out of memory\n");
    if (s != 'X' && s != 'R' && s != 'L')
        Fatal("Node %s has a bad child designator: %c\n",n,s);

    tn->name = n;
    tn->count = count;
    tn->color = color;
    tn->left = left;
    tn->right = right;
    tn->parent = 0;
    tn->parentName = pn;
    tn->side = s;
    return tn;
    }

static tnode *
textToNode(char *t)
    {
    char *word = strtok(t,"()");
    char *parent = strtok(0,"()");
    char *which = strtok(0," \n\t");
    int count = atoi(which);
    int color = BLACK;

    if (word[strlen(word)-1] == '*')
        {
        color = RED;
        word[strlen(word)-1] = '\0';
        }
    if (parent[strlen(parent)-1] == '*')
        parent[strlen(parent)-1] = '\0';

    if (*word == '=') ++word;

    while (*which && isdigit(*which)) ++which;

    return newTNode(word,count,color,0,0,parent,*which);
    }

/* mutators */

static void 
setChild(tnode *p,tnode *c)
    {
    if (c->side == 'L' && p->left == 0)
        p->left = c;
    else if (c->side == 'L')
        Fatal("Node %s has multiple left children.\n",p->name);
    else if (c->side == 'R' && p->right == 0)
        p->right = c;
    else if (c->side == 'R')
        Fatal("Node %s has multiple right children.\n",p->name);
    c->parent = p;
    }
