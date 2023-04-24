# 6485. 삼성시의 버스 노선 <D3>

tc = int(input())
for t in range(1, tc + 1):
    n = int(input())
    arr = [list(map(int, input().split())) for _ in range(n)]
    p = int(input())
    stop = [int(input()) for _ in range(p)]

    tmp = [0] * 5001
    for a, b in arr:
        for i in range(a, b + 1):
            tmp[i] += 1

    ans = []
    for j in stop:
        ans.append(tmp[j])
    print(f'#{t}', *ans, sep=' ')