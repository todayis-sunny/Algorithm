// 10978. [G3] 기숙사 재배정.

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int TC, N;
    static long[] dp = new long[21];

    public static void main(String[] args) throws IOException {
        init();
        input();
        output();
    }

    static void init() {
        dp[2] = 1;
        for (int n = 3; n <= 20; n++) {
            // 교란순열 점화식
            // (1). 새로운 n번째 원소가 n-1개의 원소중 택 1
            // (2). 새로운 원소 n이 택한 원소 m과 서로 교란순열을 이루면 남은 n-2개의 교란순열
            // (3). 새로운 원소 n이 택한 m, 하지만 m은 n을 선택하지 않음 (2)에서 구함. 즉 n-1개의 교란순열
            dp[n] = (n - 1) * (dp[n - 2] + dp[n - 1]);
        }
    }

    static void input() throws IOException {
        TC = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < TC; tc++) {
            N = Integer.parseInt(br.readLine());
            solve();
        }
    }

    static void solve() {
        sb.append(dp[N]).append("\n");
    }

    static void output() throws IOException {
        bw.write(sb.toString());
        bw.flush();
    }
}
