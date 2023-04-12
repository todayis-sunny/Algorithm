# 1209. Sum <D3>


def maxSum(arr):
    ans = 0
    tmp1 = 0
    tmp2 = 0
    for i in range(len(arr)):
        ans = max(ans, sum(arr[i]))
        tmp1 += arr[i][i]
        tmp2 += arr[i][-(i+1)]
    ans = max(ans, tmp1, tmp2)

    arr = list(zip(*arr))
    for i in range(len(arr)):
        ans = max(ans, sum(arr[i]))

    return ans


for t in range(1, 11):
    n = int(input())
    tmp = [list(map(int, input().split())) for _ in range(100)]
    print(f'#{t} {maxSum(tmp)}')