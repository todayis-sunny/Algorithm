class Solution {
    static final int MOD = 1_000_000_007;
    public int solution(int n) {
        long[] dp = new long[n + 1];
        // 초기화
        dp[2] = 3;
        for (int i = 4; i <= n; i += 2) {
            // 이전꺼에 dp[2]를 붙이는 경우 + 중간 영역을 침범하여 만드는 경우
            dp[i] = dp[i - 2] * dp[2] + 2;
            // dp 값 완성
            for(int j = 2; j <= i - 4; j+= 2) {
                dp[i] += dp[j] * 2;
            }
            // 모듈러
            dp[i] %= MOD;
        }
        return (int) dp[n];
    }
}