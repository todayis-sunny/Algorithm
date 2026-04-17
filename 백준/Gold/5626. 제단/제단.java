// 05626. [G1] 제단.

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static final int MOD = 1_000_000_007;
    static int N;
    static int[] height;
    static int[][] dp; // [i][j] = k : i번째 열 높이가 j일 때, 가능한 제단의 경우의 수

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        height = new int[N];
        dp = new int[N][(N / 2) + 2];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            height[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void solve() {
        // 1. 아예 불가능한 경우 처리
        if (height[0] > 0 || height[N - 1] > 0) {
            return;
        }
        // 2. 양 끝은 반드시 0이어야 함.
        height[0] = height[N - 1] = 0;

        // 3. dp 초기값 설정
        dp[0][0] = 1;

        // 4. dp 점화식 계산
        for (int i = 1; i < N; i++) {
            // i번째 열에서 물리적으로 도달 가능한 최대 높이
            int limit = Math.min(i, N - 1 - i);

            // a. 높이가 정해지지 않은 경우
            if (height[i] == -1) {
                dp[i][0] = (dp[i - 1][0] + dp[i - 1][1]) % MOD;
                for (int j = 1; j <= limit; j++) {
                    dp[i][j] = ((dp[i - 1][j - 1] + dp[i - 1][j]) % MOD + dp[i - 1][j + 1]) % MOD;
                }
            }
            // b. 높이가 정해진 경우
            else {
                if (height[i] > limit) return;
                // 높이가 0으로 정해진 경우
                if (height[i] == 0) {
                    dp[i][0] = (dp[i - 1][0] + dp[i - 1][1]) % MOD;
                }
                // 높이가 0으로 정해지지 않은 경우
                else {
                    dp[i][height[i]] = ((dp[i - 1][height[i] - 1] + dp[i - 1][height[i]]) % MOD + dp[i - 1][height[i] + 1]) % MOD;
                }
            }
        }
    }

    static void output() throws IOException {
        bw.write(String.valueOf(dp[N - 1][0]));
        bw.flush();
    }
}
