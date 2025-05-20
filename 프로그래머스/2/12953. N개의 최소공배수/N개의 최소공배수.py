from math import gcd 

def solution(arr):
    # 가장 작은 수를 가져옴
    ans = arr[0]                                 
    for num in arr:                           
        ans = ans * num // gcd(ans, num)     

    return ans