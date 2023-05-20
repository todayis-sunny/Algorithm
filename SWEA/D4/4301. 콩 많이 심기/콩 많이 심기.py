# 4301. 콩 많이 심기


dx = [-2, 2, 0, 0]
dy = [0, 0, -2, 2]
for t in range(1, int(input()) + 1):
    N, M = map(int, input().split())
    board = [[True] * M for _ in range(N)]

    ans = 0
    for x in range(N):
        for y in range(M):
            if not board[x][y]:
                continue
            else:
                ans += 1
                for i in range(4):
                    nx = x + dx[i]
                    ny = y + dy[i]
                    if 0 <= nx < N and 0 <= ny < M:
                        board[nx][ny] = False
    print(f'#{t} {ans}')