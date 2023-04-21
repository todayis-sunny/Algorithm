# 10580. 전봇대 <D3>

tc = int(input())

for t in range(1, tc + 1):
    n = int(input())
    arr = []
    cnt = 0

    for _ in range(n):
        ai, bi = map(int, input().split())

        for a, b in arr:
            if ai > a and bi < b:
                cnt += 1
            elif ai < a and bi > b:
                cnt += 1

        arr.append([ai, bi])

    print(f'#{t} {cnt}')