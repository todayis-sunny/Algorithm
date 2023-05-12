# 1219. 길찾기 <D4>

from collections import deque


def bfs(x, y):
    dq = deque()
    for g in graph[x]:
        dq.append(g)
    while dq:
        gg = dq.popleft()
        if gg == y:
            break
        for g in graph[gg]:
            dq.append(g)
    else:
        return 0
    return 1


for t in range(1, 10 + 1):
    tc, n = map(int, input().split())
    tmp = list(map(int, input().split()))
    graph = [[] for _ in range(100)]
    for i in range(0, len(tmp), 2):
        graph[tmp[i]].append(tmp[i+1])
    print(f'#{t} {bfs(0, 99)}')