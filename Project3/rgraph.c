/* generate a random graph, by John C. Lusth
 *
 *    Usage: rgraph vertexLimit edgeCount [weightLimit]
 *
 *     * the program randomly generates a given number of edges,
 *         based on edgeCount
 *     * vertex numbers are also randomly generated, vertex numbers are
 *         between 0 and vertexLimit
 *     * if the optional weightLimit is given, then the weight of an edge is
 *         randomly generated between 1 and the limit (inclusive) - otherwise
 *         the weight of an edge is uniquely given by an ever increasing count
 *     * duplicate edges may be generated
 */


#include <stdio.h>
#include <stdlib.h>
#include <stdarg.h>
#include <string.h>
#include <limits.h>

int
main(int argc,char **argv)
    {
    int i,vertexLimit,edgeCount,weightLimit = 0;

    if (argc == 3)
        {
        vertexLimit = atoi(argv[1]);
        edgeCount = atoi(argv[2]);
        }
    else if (argc == 4)
        {
        vertexLimit = atoi(argv[1]);
        edgeCount = atoi(argv[2]);
        weightLimit = atoi(argv[3]);
        }
    else
        {
        fprintf(stderr,"usage: rgraph vertexLimit edgeCount [weightLimit]\n");
        exit(-1);
        }

    srandom(vertexLimit + edgeCount);
    for (i = 0; i < edgeCount; ++i)
        printf("%d %d %d ;\n",
            (int)(random() % vertexLimit),
            (int)(random() % vertexLimit),
            weightLimit == 0? i + 1 : (int) (random() % weightLimit) + 1
            );

    return 0;
    }
