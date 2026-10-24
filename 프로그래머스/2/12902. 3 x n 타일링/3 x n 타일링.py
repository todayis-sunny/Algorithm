MOD = 1_000_000_007
def solution(n):
    dp = [0] * (n + 1)

    # 초기화
    dp[2] = 3

    for i in range(4, n + 1, 2):
        # 이전 값에 dp[2]를 붙이는 경우 + 중간 영역을 침범하는 경우
        dp[i] = dp[i - 2] * dp[2] + 2

        for j in range(2, i - 3, 2):
            dp[i] += dp[j] * 2

        # 모듈러
        dp[i] %= MOD

    return dp[n]