// 02169. [G2] 로봇 조종하기.

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int[][] arr, dp;

    static int N, M;


    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        dp = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp[0][0] = arr[0][0];
        for (int i = 1; i < M; i++) {
            dp[0][i] = dp[0][i - 1] + arr[0][i];
        }

        int[][] temp = new int[2][M];
        for (int i = 1; i < N; i++) {
            // left & up
            temp[0][0] = dp[i - 1][0] + arr[i][0];
            for (int j = 1; j < M; j++) {
                temp[0][j] = Math.max(temp[0][j - 1], dp[i - 1][j]) + arr[i][j];
            }

            // right & up
            temp[1][M - 1] = dp[i - 1][M - 1] + arr[i][M - 1];
            for (int j = M - 2; j >= 0; j--) {
                temp[1][j] = Math.max(temp[1][j + 1], dp[i - 1][j]) + arr[i][j];
            }

            for (int j = 0; j < M; j++) {
                dp[i][j] = Math.max(temp[0][j], temp[1][j]);
            }

        }
        bw.write(String.valueOf(dp[N - 1][M - 1]));
        bw.flush();
        br.close();
        bw.close();

    }
}
