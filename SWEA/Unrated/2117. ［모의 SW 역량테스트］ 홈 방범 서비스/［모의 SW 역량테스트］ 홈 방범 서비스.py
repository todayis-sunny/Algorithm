# 02117. 홈 방범 서비스.
from collections import deque
dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]

def cleaner(xx, yy):
    dq = deque()
    dq.append((xx, yy))
    visited[xx][yy] = False
    while dq:
        x, y = dq.popleft()
        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]
            if nx < 0 or nx >= N or ny < 0 or ny >= N:
                continue
            if not visited[nx][ny]:
                continue
            visited[nx][ny] = False
            dq.append((nx, ny))


def bfs(xx, yy):
    global answer, maximum
    dq = deque()
    dq.append((xx, yy, 0))
    cnt = [0] * 23
    visited[xx][yy] = True
    if village[xx][yy] == 1:
        cnt[0] = 1
    while dq:
        (x, y, k) = dq.popleft()
        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]
            nk = k + 1
            if ny < 0 or ny >= N or nx < 0 or nx >= N:
                continue
            if visited[nx][ny]:
                continue
            if village[nx][ny] == 1:
                cnt[nk] += 1
            visited[nx][ny] = True
            if nk <= 21:
                dq.append((nx, ny, nk))
    total = 0

    for i in range(len(cnt)):
        total += cnt[i]
        profit = (total * M) - cost[i]
        if profit >= 0 and total > answer:
            answer = total


cost = []
for k in range(1, 24):
    cost.append(k ** 2 + (k - 1) ** 2)

for tc in range(1, int(input()) + 1):
    N, M = map(int, input().split())
    village = [list(map(int, input().split())) for _ in range(N)]
    visited = [N * [False] for _ in range(N)]
    answer = 0
    for x in range(N):
        for y in range(N):
            bfs(x, y)
            cleaner(x, y)

    print(f"#{tc} {answer}")
