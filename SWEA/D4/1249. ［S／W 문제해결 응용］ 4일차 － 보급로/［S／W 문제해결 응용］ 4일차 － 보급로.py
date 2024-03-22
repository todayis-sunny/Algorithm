# 1249. [diff_4] 보급로.
from collections import deque

dx = [1, -1, 0, 0]
dy = [0, 0, -1, 1]


def bfs():
    queue = deque()
    queue.append((0, 0))
    visited[0][0] = 0
    while queue:
        (x, y) = queue.popleft()
        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]
            if not (0 <= nx < N and 0 <= ny < N):
                continue
            if visited[nx][ny] <= visited[x][y] + arr[nx][ny]:
                continue
            queue.append((nx, ny))
            visited[nx][ny] = visited[x][y] + arr[nx][ny]


for tc in range(1, int(input()) + 1):
    N = int(input())
    arr = [list(map(int, input())) for _ in range(N)]
    visited = [[1e9] * N for _ in range(N)]
    bfs()
    print(f"#{tc} {visited[N-1][N-1]}")
