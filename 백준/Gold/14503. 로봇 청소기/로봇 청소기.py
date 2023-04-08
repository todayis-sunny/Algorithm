# 14503. 로봇 청소기 [G5]

import sys
input = sys.stdin.readline

n, m = map(int, input().split())
r, c, d = map(int, input().split())
dir = [(-1, 0), (0, 1), (1, 0), (0, -1)]
arr = [list(map(int, input().split())) for _ in range(n)]
ans = 0

def robot(x, y, d):
    global ans
    if not arr[x][y]:
        arr[x][y] = 2
        ans += 1

    for _ in range(4):
        nd = (d + 3) % 4
        nx, ny = x + dir[nd][0], y + dir[nd][1]
        if not arr[nx][ny]:
            robot(nx, ny, nd)
            return
        d = nd

    nd = (d + 2) % 4
    nx, ny = x + dir[nd][0], y + dir[nd][1]
    if arr[nx][ny] == 1:
        print(ans)
        exit(0)
    robot(nx, ny, d)


robot(r, c, d)