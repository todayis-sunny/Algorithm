# 05427. [G5] 불

from collections import deque

dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]


def fire(dq, array):

    while dq:
        x, y = dq.popleft()
        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]
            if 0 <= nx < h and 0 <= ny < w and array[nx][ny] == 0:
                array[nx][ny] = array[x][y] + 1
                dq.append((nx, ny))


def escape(dq, array):

    while dq:
        x, y = dq.popleft()
        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]
            if 0 <= nx < h and 0 <= ny < w:
                if array[nx][ny] == 0: # 가보지 않았으면서 불 번짐이 없는곳.
                    if array[x][y] + 1 < fireArr[nx][ny] or fireArr[nx][ny] == 0:
                        array[nx][ny] = array[x][y] + 1
                        dq.append((nx, ny))


def check(fArr, eArr): # 테두리 검사
    temp = int(1e9)
    for R in range(w): # 가로
        if eArr[0][R] > 0:
            if eArr[0][R] < fArr[0][R] or fArr[0][R] == 0:
                temp = min(temp, eArr[0][R])
        if eArr[-1][R] > 0:
            if eArr[-1][R] < fArr[-1][R] or fArr[-1][R] == 0:
                temp = min(temp, eArr[-1][R])

    for D in range(h): # 세로
        if eArr[D][0] > 0:
            if eArr[D][0] < fArr[D][0] or fArr[D][0] == 0:
                temp = min(temp, eArr[D][0])
        if eArr[D][-1] > 0:
            if eArr[D][-1] < fArr[D][-1] or fArr[D][-1] == 0:
                temp = min(temp, eArr[D][-1])

    if temp < int(1e8):
        return temp
    return -1


TC = int(input())

for _ in range(TC):
    w, h = map(int, input().split())

    arr = [list(map(str, input())) for _ in range(h)]
    fireArr = [[0] * w for _ in range(h)]
    escapeArr = [[0] * w for _ in range(h)]

    fireDq = deque()
    escapeDq = deque()

    fireStart = []
    escapeStart = []

    for i in range(h):
        for j in range(w):
            if arr[i][j] == '#':
                fireArr[i][j] = -1
                escapeArr[i][j] = -1
            elif arr[i][j] == '*':
                fireArr[i][j] = 1
                fireDq.append((i, j))
            elif arr[i][j] == '@':
                escapeArr[i][j] = 1
                escapeDq.append((i, j))

    fire(fireDq, fireArr)
    escape(escapeDq, escapeArr)

    answer = check(fireArr, escapeArr)
    if answer == -1:
        print("IMPOSSIBLE")
    else:
        print(answer)

