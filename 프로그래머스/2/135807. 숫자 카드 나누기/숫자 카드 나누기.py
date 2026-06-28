from math import gcd

def get_gcd(arr):
    g = arr[0]
    for i in range(1, len(arr)):
        g = gcd(g, arr[i])
    return g

def solution(arrayA, arrayB):
    result = 0
    A, B = get_gcd(arrayA), get_gcd(arrayB)
    
    for b in arrayB:
        if b % A == 0:
            break
    else:
        result = max(A, result)
        
    for a in arrayA:
        if a % B == 0:
            break
    else:
        result = max(B, result)
    
    return result