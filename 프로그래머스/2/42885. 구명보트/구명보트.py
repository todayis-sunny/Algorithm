from collections import deque

def solution(people, limit):
    ans = 0
    people = sorted(people)
    dq = deque(people)
    while dq:
        esc_L = dq.popleft()
        while dq:
            esc_R = dq.pop()
            if esc_R > (limit - esc_L):
                ans += 1
            else:
                break
        ans += 1
        
    return ans