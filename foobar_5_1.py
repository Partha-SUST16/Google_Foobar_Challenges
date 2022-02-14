from math import factorial
from collections import Counter
from fractions import gcd

def solution(w, h, s):
    grid = 0
    for partition in cyclePartition(w):
        for partition2 in cyclePartition(h):
            m = cycleCount(partition,w)*cycleCount(partition2,h)
            grid += m*(s**sum([sum([gcd(i, j) for i in partition]) for j in partition2]))
    return str(grid//(factorial(w)*factorial(h)))

def cyclePartition(n,i=1):
    yield [n]
    for i in range(i,n//2+1):
        for p in cyclePartition(n-i,i):
            yield [i]+p
def cycleCount(c,n):
    cc = factorial(n)
    for a,b in Counter(c).items():
        cc//=(a**b)*factorial(b)
    return cc