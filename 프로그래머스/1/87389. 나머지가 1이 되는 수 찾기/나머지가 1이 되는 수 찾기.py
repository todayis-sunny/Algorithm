def solution(n):
    result = 1
    
    while n % result != 1:
        result += 1
    return result