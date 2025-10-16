// 11025. [P5] 요세푸스 문제 3.

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N, K;
    static int last;
    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
    }

    static void solve() {
        last = 0;
        for (int n = 1; n <= N; n++) {
            last = (K + last) % n;
        }
    }

    static void output() throws IOException {
        bw.write(String.valueOf(last + 1));
        bw.flush();
    }
}
