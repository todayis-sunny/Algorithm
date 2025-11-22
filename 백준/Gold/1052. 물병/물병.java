// 01052. [G5] 물병.

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N, K;
    static int ans;

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
        int count = 0;
        for (int i = 31; i >= 0; i--) {
            if ((N & (1 << i)) != 0) {
                count++;
            }
            if (count == K) {
                int left = (N & ((1 << i) - 1));
                if (left != 0) {
                    ans = (1 << i) - (N & ((1 << i) - 1));
                }
                break;
            }
        }
    }

    static void output() throws IOException {
        bw.write(String.valueOf(ans));
        bw.flush();
    }
}
