# 1861. 정사각형 방 <D4>
from collections import deque


def move(x, y, diff):
    dq = deque([])
    dq.append((x, y))
    cnt = 0
    while dq:
        x, y = dq.popleft()
        cnt += 1
        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]
            if 0 <= nx < n and 0 <= ny < n:
                if room[nx][ny] == room[x][y] + diff:
                    dq.append((nx, ny))
    return cnt


dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]
for t in range(1, int(input()) + 1):
    n = int(input())
    room = [list(map(int, input().split())) for _ in range(n)]
    number = 0
    maximum = 0

    for i in range(n):
        for j in range(n):
            for diff in (-1, 1):
                tmp = move(i, j, diff)
                if tmp > maximum:
                    maximum = tmp
                    number = room[i][j]
                elif tmp == maximum:
                    if room[i][j] < number:
                        number = room[i][j]

    print(f'#{t} {number} {maximum}')
