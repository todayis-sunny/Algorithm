# 1210. Ladder1 <D4>


# L, R, U | 좌우측 먼저 경로가 있는지 판단하기 위한 배치
dx = [0, 0, -1]
dy = [-1, 1, 0]

for t in range(1, 10 + 1):
    tc = int(input())
    ladder = [list(map(int, input().split())) for _ in range(100)]
    x = 99
    y = ladder[x].index(2)
    while x != 0:

        for i in range(3):
            nx = x + dx[i]
            ny = y + dy[i]
            if 0 <= nx < 100 and 0 <= ny < 100:
                if ladder[nx][ny] == 1:
                    ladder[nx][ny] = 2
                    x, y = nx, ny
                    break
    print(f'#{t} {y}')
