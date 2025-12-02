// 10986. [G3] 나머지 합.

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N, M;
    static long result;

    static int[] count;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        // mod의 개수
        count = new int[M];
        // 1-based이므로 초기 0의 개수를 1로 설정(index 0에 원소 0 존재)
        count[0] = 1;
        long mod = 0;
        st = new StringTokenizer(br.readLine());
        for (int n = 1; n <= N; n++) {
            mod += Integer.parseInt(st.nextToken());
            count[(int) (mod %= M)]++;
        }
    }

    static void solve() {
        // Combi(cnt, 2)
        for (int mod = 0; mod < M; mod++) {
            result += ((long) count[mod] * (count[mod] - 1)) / 2;
        }
    }

    static void output() throws IOException {
        bw.write(String.valueOf(result));
        bw.flush();
    }
}
