# 4299. 태혁이의 사랑은 타이밍 <D3>

c = 11
com = (c * 24 * 60) + (c * 60) + c
tc = int(input())

for t in range(1, tc + 1):
    d, h, m = map(int, input().split())
    tmp = (d * 24 * 60) + (h * 60) + m
    ans = tmp - com
    if ans < 0:
        ans = -1
    print(f'#{t} {ans}')
