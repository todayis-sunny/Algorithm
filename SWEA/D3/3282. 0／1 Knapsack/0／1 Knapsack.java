// 03282. [diff_3] 0/1 Knapsack.
import java.io.*;
import java.util.StringTokenizer;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        int TC = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= TC; tc++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            int[][] arr = new int[N][2];
            int[][] dp = new int[N+1][K+1];
            for (int n = 1; n <= N; n++) {
                st = new StringTokenizer(br.readLine());
                int V = Integer.parseInt(st.nextToken());
                int C = Integer.parseInt(st.nextToken());
                for (int k = 1; k <= K; k++) {
                    if (V <= k) {
                        dp[n][k] = Math.max(dp[n-1][k-V] + C, dp[n-1][k]);
                    } else {
                        dp[n][k] = dp[n-1][k];
                    }

                }
            }
            bw.write("#" + tc + " " + dp[N][K] + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}