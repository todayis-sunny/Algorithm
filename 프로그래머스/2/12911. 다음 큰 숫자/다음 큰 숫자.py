def solution(n):
    next = n
    answer = 0
    while True:
        next += 1
        if bin(n)[2:].count('1') == bin(next)[2:].count('1'):
            answer = next
            break
    
    return answer