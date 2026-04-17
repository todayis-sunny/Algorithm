// 01484. [G5] 다이어트.

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int G;
    static boolean flag;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        G = Integer.parseInt(br.readLine());
    }

    static void solve() {
        long left = 1L, right = 2L;
        while (right < 100_000) {
            long pL = left * left;
            long pR = right * right;
            if (pR - pL == G) {
                sb.append(right).append("\n");
                flag = true;
            }
            if (pR - pL > G) {
                left++;
            } else {
                right++;
            }
        }
    }

    static void output() throws IOException {
        if (!flag) {
            bw.write("-1");
        } else {
            bw.write(sb.toString());
        }
        bw.flush();
    }
}
