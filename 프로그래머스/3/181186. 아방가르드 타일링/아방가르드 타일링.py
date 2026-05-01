MOD = 1_000_000_007

def solution(n):
    dp = [0, 1, 3, 10, 23, 62, 170]
    
    if n < 7:
        return dp[n]
    
    for i in range(7, n+1):
        x = (dp[-1] + 2 * dp[-2] + 6 * dp[-3] + dp[-4] - dp[-6]) % MOD
        dp = dp[1:] + [x]

    return dp[-1]