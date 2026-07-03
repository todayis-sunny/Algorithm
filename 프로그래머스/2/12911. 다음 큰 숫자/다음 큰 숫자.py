def solution(n):
    number = n
    result = 0
    
    while True:
        number += 1
        if bin(n)[2:].count('1') == bin(number)[2:].count('1'):
            result = number
            break
    
    return result