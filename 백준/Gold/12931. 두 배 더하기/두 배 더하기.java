// 12931. [G5] 두 배 더하기.

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N;
    static int[] A, B;
    static int max = 0, ans = 0;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        A = B = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            B[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, B[i]);
        }
    }

    static void solve() {
        ans += log2(max);
        for (int i = 0; i < N; i++) {
            ans += Integer.bitCount(B[i]);
        }
    }

    static void output() throws IOException {
        bw.write(String.valueOf(ans));
        bw.flush();
    }

    static int log2 (int x) {
        if (x == 0) return 0;
        return (int) (Math.log(x) / Math.log(2));
    }
}
