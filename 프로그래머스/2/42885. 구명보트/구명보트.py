from collections import deque

def solution(people, limit):
    result = 0
    people = sorted(people)
    dq = deque(people)
    while dq:
        L = dq.popleft()
        while dq:
            R = dq.pop()
            if R > (limit - L):
                result += 1
            else:
                break
        result += 1
        
    return result