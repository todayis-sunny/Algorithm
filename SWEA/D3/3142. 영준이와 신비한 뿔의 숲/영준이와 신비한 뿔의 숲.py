# 3142. 영준이와 신비한 뿔의 숲 <D3>

tc = int(input())

for t in range(1, tc + 1):
    n, m = map(int, input().split())
    ans1 = 2*m - n
    ans2 = m - ans1
    print(f'#{t} {ans1} {ans2}')
