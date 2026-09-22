from collections import deque

def solution(people, limit):
    result = 0
    people = sorted(people)
    dq = deque(people)
    while dq:
        escL = dq.popleft()
        while dq:
            escR = dq.pop()
            if escR > (limit - escL):
                result += 1
            else:
                break
        result += 1
        
    return result