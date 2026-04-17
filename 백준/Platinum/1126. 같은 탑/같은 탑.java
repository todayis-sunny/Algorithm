// 01126. [P3] 같은 탑.
import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static final int INF = Integer.MIN_VALUE;
    static int N, ans;
    static int[][] dp; // [i][diff] : i번째 블록까지 사용하려고 할때, 현재 높이차가 diff일 때 두 개의 탑중 제일 높은 탑
    static int[] block;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        block = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            block[i] = Integer.parseInt(st.nextToken());
        }
        dp = new int[N][250_001];
        for (int[] d : dp) {
            Arrays.fill(d, -1);
        }
    }

    static void solve() {
        ans = build(0, 0);
    }

    static void output() throws IOException {
        if (ans == 0) {
            bw.write("-1");
        } else {
            bw.write(String.valueOf(ans));
        }
        bw.flush();
    }

    static int build(int i, int diff) {
        if (diff > 250_000) {
            return INF;
        }
        if (i == N) {
            if (diff == 0) {
                return 0;
            }
            return INF;
        }
        if (dp[i][diff] != -1) {
            return dp[i][diff];
        }
        return dp[i][diff] = max(
                build(i + 1, diff),
                build(i + 1, diff + block[i]) + block[i],
                build(i + 1, Math.abs(diff - block[i])) + (diff - block[i] < 0 ? block[i] - diff : 0)
        );
    }

    static int max(int a, int b, int c) {
        return Math.max(Math.max(a, b), c);
    }
}
