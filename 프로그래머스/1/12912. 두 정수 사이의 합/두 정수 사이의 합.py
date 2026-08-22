def solution(a, b):
    result = 0
    for i in range(min(a,b), max(a,b)+1):
        result += i
    return result