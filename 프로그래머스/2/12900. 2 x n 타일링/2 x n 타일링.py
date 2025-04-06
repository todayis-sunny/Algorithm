def solution(n):
    dp = [0] * n
    dp[0], dp[1] = 1, 2
    for i in range(2, n):
        dp[i] = (dp[i-1] + dp[i-2]) % 1_000_000_007

    return dp[n-1]