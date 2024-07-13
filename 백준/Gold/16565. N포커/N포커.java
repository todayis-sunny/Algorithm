// 16565. [G2] N포커.
import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static final int r = 10_007;
    static int[][] dp = new int[53][53];
    static int n, ans;

    public static void main(String[] args) throws IOException {
        for (int i = 0; i <= 52; ++i) {
            dp[i][0] = 1;
        }
        for (int i = 1; i <= 52; ++i) {
            for (int j = 1; j <= 52; ++j) {
                dp[i][j] = (dp[i - 1][j] + dp[i - 1][j - 1]) % r;
            }
        }

        n = Integer.parseInt(br.readLine());
        ans = 0;
        for (int i = 1; i <= 13 && n - 4 * i >= 0; ++i) {
            if (i % 2 == 1) {
                ans = (ans + dp[52 - 4 * i][n - 4 * i] * dp[13][i] % r) % r;
            } else {
                ans = (ans - dp[52 - 4 * i][n - 4 * i] * dp[13][i] % r + r) % r;
            }
        }
        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
        br.close();

    }

}