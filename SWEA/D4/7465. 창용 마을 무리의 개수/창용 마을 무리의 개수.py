# 7465. [diff_4] 창용 마을 무리의 개수
from collections import deque


def bfs():
    global answer
    dq = deque()
    for target in range(1, N+1):
        if not visited[target]:
            answer += 1
            dq.append(target)
            visited[target] = True
            while dq:
                node = dq.popleft()
                for nd in arr[node]:
                    if not visited[nd]:
                        visited[nd] = True
                        dq.append(nd)


for tc in range(1, int(input()) + 1):
    N, M = map(int, input().split())
    arr = [[] for _ in range(N+1)]
    visited = [False] * (N+1)
    answer = 0
    for _ in range(M):
        a, b = map(int, input().split())
        arr[a].append(b)
        arr[b].append(a)
    bfs()
    print(f"#{tc} {answer}")
