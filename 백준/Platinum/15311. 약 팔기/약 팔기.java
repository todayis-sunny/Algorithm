// 15311. [P5] 약 팔기.

import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        br.readLine();
    }

    static void solve() {
        sb.append("2000\n");
        for (int i = 0; i < 1000; i++) {
            sb.append("1 ");
        }
        for (int i = 0; i < 1000; i++) {
            sb.append("1000 ");
        }
    }

    static void output() throws IOException {
        bw.write(sb.toString());
        bw.flush();
    }
}
