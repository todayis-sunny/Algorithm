# 3142. 영준이와 신비한 뿔의 숲 <D3>

tc = int(input())

for t in range(1, tc + 1):
    n, m = map(int, input().split())
    ans1 = m
    ans2 = 0
    for i in range(m+1):
        if n == ans1 + 2 * i:
            break
        ans1 -= 1
        ans2 += 1
    print(f'#{t} {ans1} {ans2}')