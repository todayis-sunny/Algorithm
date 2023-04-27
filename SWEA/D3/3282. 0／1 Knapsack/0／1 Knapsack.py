# 3282. 0/1 Knapsack <D3>

tc = int(input())

for t in range(1, tc + 1):
    # n : 물건 갯수 | k : 최대 부피
    n, k = map(int, input().split())
    dp = [[0] * (k + 1) for _ in range(n + 1)]
    items = [list(map(int, input().split())) for _ in range(n)]

    for i in range(1, n + 1):
        for j in range(1, k + 1):
            if items[i - 1][0] <= j:
                dp[i][j] = max(dp[i - 1][j], items[i - 1][1] + dp[i - 1][j - items[i - 1][0]])
            else:
                dp[i][j] = dp[i - 1][j]

    print(f'#{t} {dp[n][k]}')
