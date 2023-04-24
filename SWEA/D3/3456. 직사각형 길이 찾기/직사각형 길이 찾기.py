# 3456. 직사각형 길이 찾기 <D3>

tc = int(input())

for t in range(1, tc + 1):
    arr = sorted(list(map(int, input().split())))
    ans = arr[0] + arr[2] - arr[1]
    print(f'#{t} {ans}')
