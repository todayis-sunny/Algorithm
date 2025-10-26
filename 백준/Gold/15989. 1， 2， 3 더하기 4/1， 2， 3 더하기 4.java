// 15989. [G5] 1, 2, 3 더하기 4.

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int TC;
    static int[] dp = new int[10_001];
    static int[] number;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        TC = Integer.parseInt(br.readLine());
        number = new int[TC];
        for (int tc = 0; tc < TC; tc++) {
            int num = Integer.parseInt(br.readLine());
            number[tc] = num;
        }
    }

    static void solve() {
        // n을 1로만 표현하는 방법
        Arrays.fill(dp, 1);
        // n을 1, 2와 1, 2, 3으로 표현하는 방법
        for (int k = 2; k <= 3; k++) {
            for (int n = k; n <= 10_000; n++) {
                dp[n] += dp[n - k];
            }
        }
    }

    static void output() throws IOException {
        for (int tc = 0; tc < TC; tc++) {
            bw.write(dp[number[tc]] + "\n");
        }
        bw.flush();
    }
}
