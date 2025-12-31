// 13910. [G5] 개업.

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static final int INF = Integer.MAX_VALUE;
    static int N, M;
    // woks[i] : i크기의 웍의 개수
    static int[] woks = new int[10_001];
    // dp[i] : i개의 짜장면을 만들기 위한 최소 요리 횟수
    static int[] dp;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        dp = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int m = 0; m < M; m++) {
            woks[Integer.parseInt(st.nextToken())]++;
        }
    }

    static void solve() {
        Arrays.fill(dp, INF);
        dp[0] = 0;
        woks[0] = 1;
        for (int i = 1; i<= N; i++) {
            for (int j = 0; j <= i / 2; j++) {
                // 크기가 같은 웍 2개를 사용할 수 있는 경우
                if (2 * j == i && woks[j] >= 2) {
                    dp[i] = 1;
                }
                // 크기가 다른 웍 2개를 사용할 수 있는 경우
                else if (2 * j != i && woks[j] >= 1 && woks[i - j] >= 1) {
                    dp[i] = 1;
                }
                // 여러번 요리를 해서 만들어야 하는 경우
                else if (dp[j] != INF && dp[i - j] != INF) {
                    dp[i] = Math.min(dp[i], dp[j] + dp[i - j]);
                }
            }
        }
    }

    static void output() throws IOException {
        if (dp[N] == INF) {
            bw.write("-1");
        } else {
            bw.write(String.valueOf(dp[N]));
        }
        bw.flush();
    }
}
