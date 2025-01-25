def solution(n, money):
    dp = [0]*(n+1)
    dp[0] = 1

    for coin in money:
        for x in range(coin, n+1):
            dp[x] += dp[x-coin]
            dp[x] %= 1_000_000_007

    return dp[-1]