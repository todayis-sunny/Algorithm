def solution(n, count):
    MOD = 1_000_000_007

    dp = [[0] * (n +1) for _ in range(n + 1)]
    dp[1][1] = 1
    # dp 배열 채우기
    for k in range(2, n + 1):
        for c in range(1, k + 1):
            # 2k개의 빌딩이 있고 c개의 빌딩이 앞에 보일때의 경우의 수 : dp[k][c]
            dp[k][c] = (dp[k-1][c-1] + dp[k-1][c] * 2 * (k-1)) % MOD
    
        
    return dp[n][count]

# 실수 빼먹은 조건 : (같은 높이를 가지는 빌딩 사이에는 그보다 높은 빌딩이 존재하지 않습니다.)
# dp[k][c] = (dp[k-1][c-1] * (2*k-1)) + dp[k-1][c] * ((2*k- 2) + ((2*k - 2) * (2*k - 3)) // 2)