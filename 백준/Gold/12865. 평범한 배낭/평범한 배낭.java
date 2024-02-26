// 12865. [G5] 평범한 배낭.

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[][] dp = new int[N + 1][K + 1];
        for (int n = 1; n <= N; n++) {
            st = new StringTokenizer(br.readLine());
            int W = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());
            for (int k = 0; k <= K; k++) {
                if (W > k) {
                    dp[n][k] = dp[n - 1][k];
                } else {
                    dp[n][k] = Math.max(dp[n - 1][k], dp[n - 1][k - W] + V);
                }
            }
        }
        bw.write(String.valueOf(dp[N][K]));
        bw.flush();
        bw.close();
        br.close();
    }

}