# 6109. 추억의 2048게임 <D4>


def push(arr):
    tmp = []
    flag = False
    for a in arr:
        if a == 0:
            continue
        if tmp:
            if tmp[-1] == a and not flag:
                tmp[-1] = 2*a
                flag = True
            else:
                tmp.append(a)
                flag = False
        else:
            tmp.append(a)
    for _ in range(N-len(tmp)):
        tmp.append(0)
    return tmp


dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]
move = ['up', 'down', 'left', 'right']
for t in range(1, int(input()) + 1):
    N, S = map(str, input().split())
    N = int(N)
    idx = move.index(S)
    board = [list(map(int, input().split())) for _ in range(N)]

    if S == 'left':
        for i in range(N):
            target = []
            for j in range(N):
                target.append(board[i][j])
            arr = push(target)
            for a in range(N):
                board[i][a] = arr[a]

    elif S == 'right':
        for i in range(N):
            target = []
            for j in range(N-1, -1, -1):
                target.append(board[i][j])

            arr = push(target)
            for a in range(N):
                board[i][-(a+1)] = arr[a]

    elif S == 'up':
        for j in range(N):
            target = []
            for i in range(N):
                target.append(board[i][j])
            arr = push(target)
            for a in range(N):
                board[a][j] = arr[a]

    elif S == 'down':
        for j in range(N):
            target = []
            for i in range(N-1, -1, -1):
                target.append(board[i][j])
            arr = push(target)
            for a in range(N):
                board[-(a+1)][j] = arr[a]

    print(f'#{t}')
    for b in board:
        print(*b)