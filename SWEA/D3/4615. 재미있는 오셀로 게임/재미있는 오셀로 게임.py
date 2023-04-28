# 4615. 재미있는 오셀로 게임 <D3>

from collections import deque


def othello(x, y, k):
    if k == 1:  # 착수 : 흑돌
        board[y][x] = 'B'
        player = 'B'
        target = 'W'
    else:   # 착수 : 백돌
        board[y][x] = 'W'
        player = 'W'
        target = 'B'
    change = []
    tmp = []
    for dy, dx in direction:
        ny = y + dy
        nx = x + dx
        # 공간을 벗어나는 경우 무시
        if ny < 1 or nx < 1 or ny > n or nx > n:
            continue
        # 타겟이 아닌경우 무시
        if board[ny][nx] != target:
            continue
        # 타겟인 경우 계속 탐색
        else:
            while True:
                tmp.append((ny, nx))
                ny = ny + dy
                nx = nx + dx
                # 공간을 벗어나는 경우 무시
                if ny < 1 or nx < 1 or ny > n or nx > n:
                    tmp.clear()
                    break
                # 빈 곳일 경우 무시
                if board[ny][nx] == ' ':
                    tmp.clear()
                    break
                # 착수한 돌과 같은 경우
                if board[ny][nx] == player:
                    for stone in tmp:
                        if stone not in change:
                            change.append(stone)
                    tmp.clear()
                    break

    for ty, tx in change:
        board[ty][tx] = player


tc = int(input())

# 8 방향 설정 상하좌우 + 1234(대각, 사분면)
direction = [(-1, 0), (1, 0), (0, -1), (0, 1),
             (-1, 1), (-1, -1), (1, -1), (1, 1)]

for t in range(1, tc + 1):
    n, m = map(int, input().split())
    board = [[' '] * (n + 2) for _ in range(n + 2)]
    board[n // 2][n // 2], board[n // 2 + 1][n // 2 + 1] = 'W', 'W'
    board[n // 2][n // 2 + 1], board[n // 2 + 1][n // 2] = 'B', 'B'
    for _ in range(m):
        x, y, k = map(int, input().split())
        othello(x, y, k)

    cnt_w, cnt_b = 0, 0
    for element in board:
        cnt_w += element.count('W')
        cnt_b += element.count('B')

    print(f'#{t} {cnt_b} {cnt_w}')