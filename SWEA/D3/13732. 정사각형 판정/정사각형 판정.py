# 13732. 정사각형 판정 <D3>

from collections import deque

for t in range(1, int(input()) + 1):
    n = int(input())
    arr = [list(map(str, input())) for _ in range(n)]
    info = deque()
    for i in range(n):
        tmp = []
        for j in range(n):
            if arr[i][j] == '#':
                tmp.append(j)
        info.append(tmp)

    # 좌우측 빈 공간 제거
    while info:
        if info[0] and info[-1]:
            break
        if not info[0]:
            info.popleft()
        if not info[-1]:
            info.pop()

    flag = True
    cnt = 1
    # 기준값
    first = info.popleft()
    k = first[0]
    for i in range(len(first)):
        if k != first[i] - i:
            flag = False
            break
    else:
        d = len(first)

    while info and flag:
        check = info.popleft()
        if first == check:
            cnt += 1
        else:
            flag = False
            break

    if flag and d == cnt:
        print(f'#{t} yes')
    else:
        print(f'#{t} no')
