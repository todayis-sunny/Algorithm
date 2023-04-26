# 1220. Magnetic <D3>

tc = 10
for t in range(1, tc + 1):
    n = int(input())
    # 1 : N극 자성체 | 2 : S극 자성체 | 상 : N극 | 하 : S극
    arr = [list(map(int, input().split())) for _ in range(n)]
    ans = 0
    for j in range(n):
        tmp = []
        for i in range(n):
            chk = arr[i][j]
            if chk == 0:
                continue
            elif chk == 1:
                if tmp and tmp[-1] == 2:
                    ans += 1
                    tmp.clear()
                tmp.append(chk)
            elif tmp:
                tmp.append(chk)

        if tmp.count(1) >= 1 and tmp.count(2) >= 1:
            ans += 1

    print(f'#{t} {ans}')
