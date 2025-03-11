// 01328. [P5] 고층 빌딩.

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N, L, R; // N: 빌딩의 개수, L: 가장 좌측에서 봤을 때 보이는 빌딩의 수, R: 가장 우측에서 봤을 때 보이는 빌딩의 수
    static final int MOD = 1_000_000_007;
    static long[][][] dp; // [N][L][R]

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        dp = new long[N + 1][N + 1][N + 1];
    }

    static void solve() {
        dp[1][1][1] = 1;
        for (int n = 2; n <= N; n++) {
            for (int l = 1; l <= n; l++) {
                for (int r = 1; r <= n; r++) {
                    // 가장 좌측에 높이 1인 빌딩 추가
                    // 가장 우측에 높이 1인 빌딩 추가
                    // 그 외에 높이 1인 빌딩 추가 (빌딩이 n+1개 일때, 높이 1인 빌딩이 보이지 않게 하려면 n-1개의 위치)
                    dp[n][l][r] = (dp[n - 1][l - 1][r] + dp[n - 1][l][r - 1] + dp[n - 1][l][r] * (n - 2)) % MOD;
                }
            }
        }
    }

    static void output() throws IOException {
        bw.write(String.valueOf(dp[N][L][R]));
        bw.flush();
    }
}
