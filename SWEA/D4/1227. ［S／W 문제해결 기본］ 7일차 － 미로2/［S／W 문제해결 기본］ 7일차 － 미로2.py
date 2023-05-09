# 1227. 미로2 <D4>

from collections import deque


def bfs(x, y):
    queue = deque([])
    queue.append((x,y))
    while queue:
        x, y = queue.popleft()
        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]
            # 범위를 벗 어나면 무시
            if nx < 0 or nx >= 100 or ny < 0 or ny >= 100:
                continue
            # 처음 방문하는 곳이면 큐에 삽입
            if maze[nx][ny] == 0:
                maze[nx][ny] = 2
                queue.append((nx, ny))
            # 도착점에 도달 시 종료
            if maze[nx][ny] == 3:
                return 1
    # 도착점에 미도달 시 종료
    return 0


dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]

for t in range(1, 10 + 1):
    tc = int(input())
    maze = [list(map(int, input())) for _ in range(100)]
    print(f'#{t} {bfs(1, 1)}')
