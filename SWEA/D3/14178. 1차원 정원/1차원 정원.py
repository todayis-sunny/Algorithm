# 14178. 1차원 정원 <D3>

tc = int(input())

for t in range(1, tc+1):
    n, d = map(int, input().split())
    if n % (d * 2 + 1) == 0:
        ans = n // (d * 2 + 1)
    else:
        ans = n // (d * 2 + 1) + 1
    print(f'#{t} {ans}')
