// 01577. [G5] 도로의 개수.

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N, M, K;
    static long[][] dp;
    static boolean[][][] impossible; // [o][n][m] : o(0 가로, 1 세로) (n, m)지점으로 가는 루트 공사중 여부

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        dp = new long[N + 1][M + 1];
        impossible = new boolean[2][N + 1][M + 1];
        K = Integer.parseInt(br.readLine());
        for (int k = 0; k < K; k++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int o = (Math.abs(a - c) << 1) + Math.abs(b - d) - 1;
            impossible[o][Math.max(a, c)][Math.max(b, d)] = true;
        }
    }

    static void solve() {
        // 1. 사전 작업
        dp[0][0] = 1;
        // 세로
        for (int n = 1; n <= N; n++) {
            if (impossible[1][n][0]) break;
            dp[n][0] = 1;
        }
        // 가로
        for (int m = 1; m <= M; m++) {
            if (impossible[0][0][m]) break;
            dp[0][m] = 1;
        }
        // 2. 메인 루프
        for (int n = 1; n <= N; n++) {
            for (int m = 1; m <= M; m++) {
                long row = impossible[1][n][m] ? 0 : 1;
                long col = impossible[0][n][m] ? 0 : 1;
                dp[n][m] = dp[n - 1][m] * row + dp[n][m - 1] * col;
            }
        }
    }

    static void output() throws IOException {
        bw.write(String.valueOf(dp[N][M]));
        bw.flush();
    }
}
