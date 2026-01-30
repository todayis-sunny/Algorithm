def solution(n):
    num = n
    result = 0
    
    while True:
        num += 1
        if bin(n)[2:].count('1') == bin(num)[2:].count('1'):
            result = num
            break
    
    return result