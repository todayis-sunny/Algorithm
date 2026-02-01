// 13250. [G4] 주사위 게임.

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N;
    static double[] dp = new double[1_000_007];

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
    }

    static void solve() {
        dp[0] = -1;
        for (int i = 0; i <= N; i++) {
            dp[i] += 1;
            for (int j = 1; j <= 6; j++) {
                dp[i + j] += dp[i] / 6;
            }
        }
    }

    static void output() throws IOException {
        bw.write(String.valueOf(dp[N]));
        bw.flush();
    }
}
