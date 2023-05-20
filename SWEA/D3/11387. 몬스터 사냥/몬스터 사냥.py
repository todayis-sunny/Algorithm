# 11387. 몬스터 사냥 <D3>

for t in range(1, int(input()) + 1):
    D, L, N = map(int, input().split())
    ans = 0
    for n in range(N):
        ans += (D * (1 + n*L/100))
    print(f'#{t} {int(ans)}')
