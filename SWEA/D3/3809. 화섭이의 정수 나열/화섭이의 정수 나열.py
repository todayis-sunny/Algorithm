# 3809. 화섭이의 정수 나열 <D3>

tc = int(input())

for t in range(1, tc + 1):
    n = int(input())
    nums = ''
    while True:
        nums += ''.join(map(str, input().split()))
        if len(nums) == n:
            break
    ans = 0
    while str(ans) in nums:
        ans += 1
    print(f'#{t} {ans}')