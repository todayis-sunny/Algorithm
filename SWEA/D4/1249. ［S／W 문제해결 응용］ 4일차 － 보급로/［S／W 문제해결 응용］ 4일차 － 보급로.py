# 1249. 보급로 <D4>

from collections import deque


def bfs():
    dq = deque()
    keys = [[1e9] * n for _ in range(n)]
    keys[0][0] = 0
    dq.append((0, 0))
    while dq:
        x, y = dq.popleft()
        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]
            # 범위에 해당하는 경우
            if 0 <= nx < n and 0 <= ny < n:
                if keys[x][y] + maps[nx][ny] < keys[nx][ny]:
                    keys[nx][ny] = keys[x][y] + maps[nx][ny]
                    dq.append((nx, ny))

    return keys[-1][-1]


dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]

tc = int(input())

for t in range(1, tc + 1):
    n = int(input())
    maps = [list(map(int, input())) for _ in range(n)]

    print(f'#{t} {bfs()}')
