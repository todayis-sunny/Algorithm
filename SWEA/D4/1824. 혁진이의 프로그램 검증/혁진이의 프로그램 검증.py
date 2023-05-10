# 1824. 혁진이의 프로그램 검증 <D4>

from collections import deque


def program(x, y, memory):
    idx = 3
    visited = [[set() for _ in range(c)] for _ in range(r)]
    dq = deque()
    dq.append((x, y, idx, memory))
    while dq:
        x, y, idx, memory = dq.popleft()
        point = arr[x][y]
        random = False
        if point in move:
            idx = move.index(point)
        elif point == '_':
            idx = 3 if memory == 0 else 2
        elif point == '|':
            idx = 1 if memory == 0 else 0
        elif point == '+':
            memory = 0 if memory == 15 else memory + 1
        elif point == '-':
            memory = 15 if memory == 0 else memory - 1
        elif point.isdigit():
            memory = int(point)
        elif point == '.':
            pass
        elif point == '?':
            random = True
        elif point == '@':
            return 'YES'

        # 랜덤 진행
        tmp = [0, 1, 2, 3] if random else [idx]
        for idx in tmp:
            if (idx, memory) not in visited[x][y]:
                nx = (x + dx[idx]) % r
                ny = (y + dy[idx]) % c
                dq.append((nx, ny, idx, memory))
                visited[x][y].add((idx, memory))
    return 'NO'


move = ['^', 'v', '<', '>']
change = ['_', '|']
value = ['+', '-']
dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]
for t in range(1, int(input()) + 1):
    r, c = map(int, input().split())
    arr = [list(map(str, input())) for _ in range(r)]
    print(f'#{t} {program(0, 0, 0)}')
 