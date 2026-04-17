// 02758. [G4] 로또.

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int TC;
    static int N, M;
    static long[][] dp = new long[11][2_001]; // dp[i][j] : j까지의 숫자 중 i개를 뽑을수 있는 경우의 수

    public static void main(String[] args) throws IOException {
        init();
        input();
        output();
    }

    static void init() {
        Arrays.fill(dp[0], 1);
        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 2_000; j++) {
                // (j - 1)이하의 i개 수를 만드는 방법 + (j / 2)이하의 (i - 1)개 수를 만드는 방법
                dp[i][j] = dp[i][j - 1] + dp[i - 1][j / 2];
            }
        }
    }

    static void input() throws IOException {
        TC = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < TC; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            solve();
        }
    }

    static void solve() {
        sb.append(dp[N][M]).append("\n");
    }

    static void output() throws IOException {
        bw.write(sb.toString());
        bw.flush();
    }

}
