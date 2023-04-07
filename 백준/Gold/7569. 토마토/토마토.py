# 7569. 토마토 [G5]

import sys
from collections import deque

# 입력 - m : 상자의 가로 칸의 수 | n : 상자의 세로 칸의 수 | h : 상자의 수
m, n, h = map(int, sys.stdin.readline().split())

graph = []
for _ in range(h):
    arr = []
    for _ in range(n):
        temp = list(map(int, input().split()))
        arr.append(temp)
    graph.append(arr)

visited = [[[False] * m for _ in range(n)] for _ in range(h)]
queue = deque()
# 윗, 아래, 상, 하, 좌, 우
dx = [-1, 1, 0, 0, 0, 0]
dy = [0, 0, -1, 1, 0, 0]
dz = [0, 0, 0, 0, -1, 1]

answer = 0


def bfs():
    while queue:
        x, y, z = queue.popleft()

        for i in range(6):
            nx = x + dx[i]
            ny = y + dy[i]
            nz = z + dz[i]
            # 공간을 벗어난 경우 무시
            if nx < 0 or ny < 0 or nz < 0 or nx >= h or ny >= n or nz >= m:
                continue
            # 익지 않은 토마토인 경우 큐에 삽입
            if graph[nx][ny][nz] == 0 and visited[nx][ny][nz] == False:
                queue.append((nx, ny, nz))
                graph[nx][ny][nz] = graph[x][y][z] + 1
                visited[nx][ny][nz] = True

# 모두 1이 아닐 경우
for a in range(h):
    for b in range(n):
        for c in range(m):
            if graph[a][b][c] == 1 and visited[a][b][c] == False:
                queue.append((a, b, c))
                visited[a][b][c] = True

bfs()
# 토마토 확인
for a in graph:
    for b in a:
        for c in b:
            if c == 0:
                print(-1)
                exit(0)
        answer = max(answer, max(b))

print(answer - 1)
