from collections import deque

def solution(n, roads, sources, destination):
    IMP = -1
    memo = [IMP] * (n+1)
    arr = [[] for _ in range(n+1)]
    
    for a, b in roads:
        arr[a].append(b)
        arr[b].append(a)
    
    memo[destination] = 0
    
    dq = deque()
    for x in arr[destination]:
        dq.append((x, 1))
        memo[x] = 1
    while dq:
        x, t = dq.popleft()
        for y in arr[x]:
            if memo[y] == IMP:
                dq.append((y, t+1))
                memo[y] = t+1
        
    answer = []
    for s in sources:
        answer.append(memo[s])
    return answer