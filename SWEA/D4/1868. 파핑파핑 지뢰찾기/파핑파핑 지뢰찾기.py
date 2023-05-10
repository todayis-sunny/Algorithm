# 1868. 파핑파핑 지뢰찾기 <D4>

from collections import deque


def mine_cnt(board, n, x, y):
    cnt = 0
    for i in range(8):
        nx = x + dx[i]
        ny = y + dy[i]
        if 0 <= nx < n and 0 <= ny < n:
            if board[nx][ny] == '*':
                cnt += 1
    return cnt


def click(x, y):
    global find
    dq = deque([])
    dq.append((x, y))
    while dq:
        x, y = dq.popleft()
        find += 1
        if board[x][y] == 0:
            for i in range(8):
                nx = x + dx[i]
                ny = y + dy[i]
                if 0 <= nx < n and 0 <= ny < n and (nx, ny) not in dq:
                    if board[nx][ny] not in ['C', '*']:
                        dq.append((nx, ny))
        board[x][y] = 'C'


dx = [-1, -1, -1, 0, 0, 1, 1, 1]
dy = [-1, 0, 1, -1, 1, -1, 0, 1]
for t in range(1, int(input()) + 1):
    n = int(input())
    board = [list(map(str, input())) for _ in range(n)]
    find = 0
    ans = 0
    limit = n ** 2
    flag = False
    for i in range(n):
        for j in range(n):
            if board[i][j] == '.':
                board[i][j] = mine_cnt(board, n, i, j)
            else:
                find += 1

    for i in range(n):
        for j in range(n):
            if board[i][j] == 0:
                click(i, j)
                ans += 1
            if find == limit:
                flag = True
                break
        if flag:
            break
    else:
        ans += (limit - find)

    print(f'#{t} {ans}')
