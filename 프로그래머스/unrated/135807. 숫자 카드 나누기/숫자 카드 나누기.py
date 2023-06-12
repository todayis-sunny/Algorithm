from math import gcd

def get_gcd(arr):
    g = arr[0]
    for i in range(1, len(arr)):
        g = gcd(g, arr[i])
    return g

def solution(arrayA, arrayB):
    res = 0
    A, B = get_gcd(arrayA), get_gcd(arrayB)
    
    for b in arrayB:
        if b % A == 0:
            break
    else:
        res = max(A, res)
        
    for a in arrayA:
        if a % B == 0:
            break
    else:
        res = max(B, res)
    
    return res