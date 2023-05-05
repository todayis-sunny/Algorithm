# 1491. 원재의 벽 꾸미기 <D3>

for t in range(1, int(input()) + 1):
    n, a, b = map(int, input().split())
    ans = 1e9
    for r in range(1, n + 1):
        c = 1
        while r * c <= n:
            tmp = a * abs(r - c) + b * (n - (r * c))
            ans = min(ans, tmp)
            c += 1
    print(f'#{t} {ans}')