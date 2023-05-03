# 11736. 평범한 숫자 <D3>

tc = int(input())

for t in range(1, tc + 1):
    n = int(input())
    arr = list(map(int, input().split()))
    ans = 0
    for i in range(n-2):
        if arr[i+1] == sorted(arr[i:i+3])[1]:
            ans += 1
    print(f'#{t} {ans}')