# 10200. 구독자 전쟁 <D3>

tc = int(input())

for t in range(1, tc + 1):
    n, a, b = map(int, input().split())
    ans1 = min(a, b)
    ans2 = a + b - n
    if ans2 < 0:
        ans2 = 0
    print(f'#{t} {ans1} {ans2}')