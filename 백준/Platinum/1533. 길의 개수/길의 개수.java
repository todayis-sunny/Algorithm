// 01533. [P3] 길의 개수.

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static final int MOD = 1_000_003;
    static int N, S, E, T; // 교차점 개수, 시작점 위치, 끝점 위치, 늦는 시간
    static long[][] adj, ans;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        adj = new long[5 * N + 1][5 * N + 1];
        ans = new long[5 * N + 1][5 * N + 1];
        for (int i = 1; i <= 5 * N; i++) {
            ans[i][i] = 1;
        }
        for (int i = 1; i <= N; i++) {
            String input = br.readLine();
            for (int j = 0; j < input.length(); j++) {
                int num = Character.getNumericValue(input.charAt(j));
                if (num >= 1) {
                    adj[i * 5][(j + 1) * 5 - (num - 1)] = 1;
                }
            }
            for (int j = 1; j <= 4; j++) {
                adj[(i - 1) * 5 + j][(i - 1) * 5 + j + 1] = 1;
            }
        }
    }

    static void solve() {
        while (T > 0) {
            if (T % 2 == 1) {
                ans = multiply(adj, ans);
            }
            T /= 2;
            adj = multiply(adj, adj);
        }
    }

    static void output() throws IOException {
        bw.write(String.valueOf(ans[5 * S][5 * E]));
        bw.flush();
    }

    static long[][] multiply(long[][] a, long[][] b) {
        long[][] res = new long[5 * N + 1][5 * N + 1];
        for (int i = 1; i <= 5 * N; i++) {
            for (int j = 1; j <= 5 * N; j++) {
                for (int k = 1; k <= 5 * N; k++) {
                    res[i][j] += (a[i][k] * b[k][j]);
                    res[i][j] %= MOD;
                }
            }
        }
        return res;
    }
}
