// 01727. [G2] 커플 만들기.

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N, M;
    static int[] male, female;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        // 남자, 여자 초기화
        male = new int[N + 1];
        female = new int[M + 1];
        dp = new int[N + 1][M + 1];
        st = new StringTokenizer(br.readLine());
        for (int n = 1; n <= N; n++) {
            male[n] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int m = 1; m <= M; m++) {
            female[m] = Integer.parseInt(st.nextToken());
        }
    }

    static void solve() {
        Arrays.sort(male);
        Arrays.sort(female);

        for (int ma = 1; ma <= N; ma++) {
            for (int wo = 1; wo <= M; wo++) {
                // 남자, 여자가 동일한 인원수인 경우
                dp[ma][wo] = dp[ma - 1][wo - 1] + Math.abs(male[ma] - female[wo]);

                // 남자가 여자보다 더 많은 경우 i번째 남자는 (솔로 or 커플)
                if (ma > wo) {
                    dp[ma][wo] = Math.min(dp[ma - 1][wo], dp[ma][wo]);
                }
                // 여자가 남자보다 더 많은 경우 j번재 여자는 (솔로 or 커플)
                else if (ma < wo){
                    dp[ma][wo] = Math.min(dp[ma][wo - 1], dp[ma][wo]);
                }
            }
        }
    }

    static void output() throws IOException {
        bw.write(String.valueOf(dp[N][M]));
        bw.flush();
    }
}
