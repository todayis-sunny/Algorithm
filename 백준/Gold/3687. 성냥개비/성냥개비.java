// 03687. [G2] 성냥개비.

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static long[] minDp = new long[101];
    static int TC;
    static int N;
    public static void main(String[] args) throws IOException {
        init();
        input();
        output();
    }
    static void init() {
        Arrays.fill(minDp, Long.MAX_VALUE);
        minDp[2] = 1;
        minDp[3] = 7;
        minDp[4] = 4;
        minDp[5] = 2;
        minDp[6] = 6;
        minDp[7] = 8;
        minDp[8] = 10;
        // 2-based
        String[] add = {"", "", "1", "7", "4", "2", "0", "8"};
        for (int j = 9; j <= 100; j++) {
            for (int k = 2; k <= 7; k++) {
                String number = minDp[j - k] + add[k];
                minDp[j] = Math.min(minDp[j], Long.parseLong(number));
            }
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
        String maxValue = "";
        if (N % 2 == 0) {
            for (int i = 0; i < N / 2; i++) {
                maxValue = maxValue.concat("1");
            }
        } else {
            maxValue = "7";
            for (int i = 0; i < (N / 2) - 1; i++) {
                maxValue = maxValue.concat("1");
            }
        }
        // 값 기입
        sb.append(minDp[N]).append(" ").append(maxValue).append("\n");
    }

    static void output() throws IOException {
        bw.write(sb.toString());
        bw.flush();
    }
}
