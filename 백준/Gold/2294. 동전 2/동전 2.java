// 02294. [G5] 동전 2.

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N, K;
    static int[] dp;
    static boolean[] coin;
    static int ans;
    static final int LIMIT = 100_001;
    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        dp = new int[K + 1];
        Arrays.fill(dp, LIMIT);
        dp[0] = 0;
        coin = new boolean[100_001];
    }

    static void solve() throws IOException {
        for (int n = 0; n < N; n++) {
            int value = Integer.parseInt(br.readLine());
            if (coin[value]) {
                continue;
            }
            coin[value] = true;
            for (int v = value; v <= K; v++) {
                dp[v] = Math.min(dp[v], dp[v - value] + 1);
            }
        }
    }

    static void output() throws IOException {
        ans = dp[K] == LIMIT ? -1 : dp[K];
        bw.write(String.valueOf(ans));
        bw.flush();
    }
}
