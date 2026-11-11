def solution(arr, divisor):
    result = []
    for a in arr:
        if a % divisor == 0:
            result.append(a)
    if result:
        return sorted(result)
    
    return [-1]