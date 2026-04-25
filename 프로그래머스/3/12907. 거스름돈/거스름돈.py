MOD = 1_000_000_007

def solution(n, money):
    dp = [0] * (n + 1)
    dp[0] = 1
    
    for coin in money:
        for x in range(coin, n + 1):
            dp[x] += dp[x - coin]
            dp[x] %= MOD

    return dp[-1]