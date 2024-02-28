// 15990. [S2] 1, 2, 3 더하기 5.
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static long[][] dp = new long[100001][4];
    static final int DIV_NUM = 1_000_000_009;
    public static void main(String[] args) throws IOException {
        dp[1][1] = 1;
        dp[2][2] = 1;
        dp[3][1] = 1;
        dp[3][2] = 1;
        dp[3][3] = 1;
        for (int i = 4; i <= 100000; i++) {
            dp[i][1] = (dp[i-1][2] + dp[i-1][3]) % DIV_NUM;
            dp[i][2] = (dp[i-2][1] + dp[i-2][3]) % DIV_NUM;
            dp[i][3] = (dp[i-3][1] + dp[i-3][2]) % DIV_NUM;
        }

        int TC = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < TC; tc++) {
            int n = Integer.parseInt(br.readLine());
            long answer = (dp[n][1] + dp[n][2] + dp[n][3]) % DIV_NUM;
            bw.write(String.valueOf(answer));
            bw.newLine();
        }
        bw.flush();
        bw.close();
        br.close();
    }
}