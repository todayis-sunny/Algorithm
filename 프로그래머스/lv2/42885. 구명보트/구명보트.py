from collections import deque

def solution(people, limit):
    people = sorted(people)
    ans = 0
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