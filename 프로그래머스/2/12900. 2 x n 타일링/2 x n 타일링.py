def solution(n):
    MOD = 1_000_000_007
    dp = [0] * n
    dp[0], dp[1] = 1, 2
    for i in range(2, n):
        dp[i] = (dp[i-1] + dp[i-2]) % MOD

    return dp[n-1]