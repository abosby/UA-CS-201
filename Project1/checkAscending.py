import sys

def main():
    if len(sys.argv) != 2:
        print("USAGE: python checkAscending <filename>")
        return

    a = []

    with open(sys.argv[1], 'r') as f:
        for l in f:
            for x in l.split():
                a.append(int(x))

    print(all(a[i] <= a[i+1] for i in xrange(len(a)-1)))

main()
