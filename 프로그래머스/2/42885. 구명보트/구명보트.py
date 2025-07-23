from collections import deque

def solution(people, limit):
    ans = 0
    people = sorted(people)
    dq = deque(people)
    while dq:
        escL = dq.popleft()
        while dq:
            escR = dq.pop()
            if escR > (limit - escL):
                ans += 1
            else:
                break
        ans += 1
        
    return ans