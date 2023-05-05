# 16910. 원 안의 점 <D3>

for t in range(1, int(input()) + 1):
    n = int(input())
    ans = 0
    for x in range(-n, n+1):
        for y in range(-n, n+1):
            if x**2 + y**2 <= n**2:
                ans += 1
    print(f'#{t} {ans}')