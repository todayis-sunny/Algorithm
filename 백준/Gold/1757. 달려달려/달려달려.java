// 01757. [G4] 달려달려.

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N, M;
    static int[] distance;
    // dp[t][m][n] = t: 휴식 여부 (0 : 휴식중, 1: 달리는중), m: 지침 지수, n: n분(현재 시간)
    static int[][][] dp; // [2][2-based][1-based]

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
        dp = new int[2][M + 2][N + 1];
        for (int n = 1; n <= N; n++) {
            distance[n] = Integer.parseInt(br.readLine());
        }
    }


    static void solve() {
        // 학생들은 쉬기 시작하면 지침지수가 0이 되기 전에는 달릴수 없다.


        for (int n = 1; n <= N; n++) {
            int limit = Math.min(M + 1, n);
            for (int m = 0; m <= limit; m++) {
                // 쉬기
                if (m < limit) {
                    dp[0][m][n] = Math.max(dp[0][m + 1][n - 1], dp[1][m + 1][n - 1]); // 휴식 -> 휴식, 달리기 -> 휴식
                }
                if (m == 0) {
                    dp[0][m][n] = Math.max(dp[0][m][n], dp[0][m][n - 1]);
                }
                // 달리기
                if (m == 1) { // 쉬다가 달리기 가능
                    dp[1][m][n] = dp[0][m - 1][n - 1] + distance[n];
                } else if (m >= 2) { // 달리는 중에만 달리기 가능
                    dp[1][m][n] = dp[1][m - 1][n - 1] + distance[n];
                }
            }
        }
    }

    static void output() throws IOException {
        bw.write(String.valueOf(dp[0][0][N]));
        bw.flush();
//        printDebug();
    }

    static void printDebug() {
        System.out.println();
        for (int m = 0; m <= M + 1; m++) {
            System.out.printf("m%d :", m);
            for (int n = 0; n <= N; n++) {
                System.out.printf("%2d ", dp[0][m][n]);
            }
            System.out.println();
        }
        System.out.println();
        for (int m = 0; m <= M + 1; m++) {
            System.out.printf("m%d :", m);
            for (int n = 0; n <= N; n++) {
                System.out.printf("%2d ", dp[1][m][n]);
            }
            System.out.println();
        }
    }
}
