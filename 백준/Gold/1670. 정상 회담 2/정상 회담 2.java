// 01670. [G3] 정상 회담 2.

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static final long MOD = 987_654_321;
    static long[] dp = new long[5_000];
    static int N;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
    }

    static void solve() {
        dp[0] = 1L;
        int limit = N / 2;
        for (int i = 1; i <= limit; i++) {
            for (int j = 0; j <= i - 1; j++) {
                dp[i] += dp[j] * dp[i - 1 - j] % MOD;
                dp[i] %= MOD;
            }
        }
    }

    static void output() throws IOException {
        bw.write(String.valueOf(dp[N / 2]));
        bw.flush();
    }
}
