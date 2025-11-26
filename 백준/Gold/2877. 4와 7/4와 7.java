// 02877. [G5] 4와 7.

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int K;
    static String binary;
    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        K = Integer.parseInt(br.readLine());
    }

    static void solve() {
        binary = Integer.toBinaryString(K + 1);
        for (int i = 1; i < binary.length(); i++) {
            if (binary.charAt(i) == '1') {
                sb.append("7");
            } else {
                sb.append("4");
            }
        }
    }

    static void output() throws IOException {
        bw.write(sb.toString());
        bw.flush();
    }
}
