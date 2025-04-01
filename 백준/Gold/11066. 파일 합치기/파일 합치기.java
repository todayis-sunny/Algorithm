// 11066. [G3] 파일 합치기.

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int TC, K;
    static int[][] dp; // dp[i][j]: i ~ j 까지 파일을 합치는데 필요한 최소비용(누적합)
    static int[] file; // 파일들

    public static void main(String[] args) throws IOException {
        input();
        output();
    }

    static void input() throws IOException {
        TC = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < TC; tc++) {
            K = Integer.parseInt(br.readLine());
            file = new int[K];
            dp = new int[K][K];
            st = new StringTokenizer(br.readLine());
            for (int k = 0; k < K; k++) {
                file[k] = Integer.parseInt(st.nextToken());
            }
            for (int i = 0; i < K - 1; i++) {
                // 각 행의 첫번째 값은 i ~ (i+1)의 합으로 초기화
                dp[i][i + 1] = file[i] + file[i + 1];
                for (int j = i + 2; j < K; j++) {
                    // i ~ j 누적합
                    dp[i][j] = dp[i][j - 1] + file[j];
                }
            }
            for (int n = 2; n < K; n++) {
                for (int i = 0; i < K - n; i++) {
                    int minCost = Integer.MAX_VALUE;
                    int j = i + n;
                    for (int x = i; x < j; x++) {
                        minCost = Math.min(minCost, dp[i][x] + dp[x + 1][j]);
                    }
                    dp[i][j] += minCost;
                }
            }
            sb.append(dp[0][K - 1]).append('\n');
        }
    }
    
    static void output() throws IOException {
        bw.write(sb.toString());
        bw.flush();
    }
}
