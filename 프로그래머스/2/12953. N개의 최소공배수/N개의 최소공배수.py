from math import gcd 

def solution(arr):
                               
    ans = arr[0]                                 
    for num in arr:                           
        ans = ans * num // gcd(ans, num)     

    return ans