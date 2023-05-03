# 6692. 다솔이의 월급 상자 <D3>

tc = int(input())

for t in range(1, tc + 1):
    n = int(input())
    ans = 0.0
    for _ in range(n):
        p, x = map(float, input().split())
        ans += p*x
    print(f'#{t} {ans:.6f}')