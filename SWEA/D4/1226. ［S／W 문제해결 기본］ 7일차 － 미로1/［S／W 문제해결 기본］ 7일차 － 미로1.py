# 1226. 미로1 <D3>

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
            if nx < 0 or nx >= 16 or ny < 0 or ny >= 16:
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
    maze = [list(map(int, input())) for _ in range(16)]
    print(f'#{t} {bfs(1, 1)}')
    