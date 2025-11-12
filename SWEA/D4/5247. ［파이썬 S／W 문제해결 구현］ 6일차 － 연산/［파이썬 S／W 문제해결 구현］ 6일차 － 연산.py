from collections import deque

def bfs(count, number, goal, visited):
    queue = deque([(count, number)])
    while queue:
        cnt, num = queue.popleft()
        if num == goal:
            return cnt
        for k in [num + 1, num - 1, num * 2, num - 10]:
            if k > 1_000_000: continue
            if k in visited: continue
            queue.append((cnt + 1, k))
            visited.add(k)
    return -1

for tc in range(1, int(input()) + 1):
    visited = set()
    N, M = map(int, input().split())
    ans = bfs(0, N, M, visited)
    print(f"#{tc} {ans}")
