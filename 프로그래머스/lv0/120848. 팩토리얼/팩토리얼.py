from math import factorial as fac
def solution(n):
    k = 0
    while fac(k) <= n:
        k += 1
    k -= 1
    return k