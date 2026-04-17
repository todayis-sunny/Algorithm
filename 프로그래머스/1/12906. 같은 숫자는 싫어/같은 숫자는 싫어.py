def solution(arr):
    result = []
    
    for i in arr:
        if result[-1:] == [i]: continue
        result.append(i)
        
    return result