from collections import deque

def solution(stones, k):
    result = 1e9
    dq = deque()
    for idx, stone in enumerate(stones):
        while dq and dq[-1][1] < stone:
            dq.pop()
        dq.append((idx,stone))
            
        if dq[0][0] <= idx - k:
            dq.popleft()
        
        if idx >= k-1:
            result = min(result, dq[0][1])
            
    return result