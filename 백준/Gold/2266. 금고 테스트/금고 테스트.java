// 02266. [G2] 금고 테스트.

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static final int INF = Integer.MAX_VALUE;
    static int N, K; //
    static int[][] dp = new int[501][501]; // dp[k][n] : n층에서 k개의 금고를 가지고 F층을 알아낼 수 있는 금고 낙하 횟수


    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
    }

    static void solve() {
        // 1층에서 떨어지는 횟수 초기화
        for (int k = 1; k <= K; k++) {
            dp[k][1] = 1;
        }
        // n층에서 금고 1개로 확인할 있는 최소 횟수는 1번
        for (int n = 1; n <= N; n++) {
            dp[1][n] = n;
        }
        // 금고 2개이상 루프
        for (int k = 2; k <= K; k++) {
            // 2층이상 루프
            for (int n = 2; n <= N; n++) {
                dp[k][n] = INF;
                // n층에세 k개의 금고를 가진 상황에서, i층
                for (int i = 1; i <= n; i++) {
                    // 깨진 경우 : [k - 1][i- 1], 안 깨진 경우 : [k][n - i]
                    dp[k][n] = Math.min(dp[k][n], 1 + Math.max(dp[k - 1][i - 1], dp[k][n - i]));
                }
            }
        }
    }

    static void output() throws IOException {
        bw.write(String.valueOf(dp[K][N]));
        bw.flush();
    }
}
