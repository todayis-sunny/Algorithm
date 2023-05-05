# 14413. 격자판 칠하기 <D3>

for t in range(1, int(input()) + 1):
    n, m = map(int, input().split())
    arr = [list(map(str, input())) for _ in range(n)]
    flag = True
    # 검은색 먼저 칠하기
    for i in range(n):
        for j in range(m):
            if (i + j) % 2 == 0:
                if arr[i][j] == '.':
                    flag = False
                    break
            else:
                if arr[i][j] == '#':
                    flag = False
                    break
        if not flag:
            break

    if not flag:
        flag = True
        # 흰색 먼저 칠하기
        for i in range(n):
            for j in range(m):
                if (i + j) % 2 == 0:
                    if arr[i][j] == '#':
                        flag = False
                        break
                else:
                    if arr[i][j] == '.':
                        flag = False
                        break
            if not flag:
                break

    if flag:
        print(f'#{t} possible')
    else:
        print(f'#{t} impossible')
