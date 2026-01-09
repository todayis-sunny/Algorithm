// 01757. [G4] 달려달려.

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N, M;
    static int[] distance;
    // dp[m][n] = m: 지침 지수, n: n분(현재 시간)
    static int[][] dp; // [1-based][1-based]

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        distance = new int[N + 1];
        dp = new int[M + 1][N + 1];
        for (int n = 1; n <= N; n++) {
            distance[n] = Integer.parseInt(br.readLine());
        }
    }


    static void solve() {
        // 학생들은 쉬기 시작하면 지침지수가 0이 되기 전에는 달릴수 없다.


        for (int n = 1; n <= N; n++) {
            dp[0][n] = dp[0][n - 1];
            int limit = Math.min(M, n);
            for (int m = 1; m <= limit; m++) {
                // 달리는 경우
                dp[m][n] = dp[m - 1][n - 1] + distance[n];
                // 쭉 쉬는 경우 (지침지수)(현재시간 - 지침지수)
                dp[0][n] = Math.max(dp[0][n], dp[m][n - m]);
            }
        }
    }

    static void output() throws IOException {
        bw.write(String.valueOf(dp[0][N]));
        bw.flush();
//        printDebug();
    }

    static void printDebug() {
        System.out.println();
        for (int m = 0; m <= M; m++) {
            System.out.printf("m%d :", m);
            for (int n = 0; n <= N; n++) {
                System.out.printf("%2d ", dp[m][n]);
            }
            System.out.println();
        }
    }
}
