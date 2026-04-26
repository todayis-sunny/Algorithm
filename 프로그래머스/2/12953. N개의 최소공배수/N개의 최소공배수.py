from math import gcd 

def solution(arr):
    # 가장 작은 수를 가져옴
    result = arr[0]                                 
    for num in arr:                           
        result = result * num // gcd(result, num)     

    return result