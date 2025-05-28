def solution(n):
    DIV = 1_234_567
    dp = [1] + [0] * n
    dp[0] = 1
    dp[1] = 1
    for i in range(2, n + 1):
        dp[i] = (dp[i-1] + dp[i-2]) % DIV
    return dp[n]