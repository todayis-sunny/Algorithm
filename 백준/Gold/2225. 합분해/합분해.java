// 02225. [G5] 합분해.
import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static long[][]dp = new long[201][201];
    static final int DIV_NUM = 1_000_000_000;
    public static void main(String[] args) throws IOException {
        for (int n = 0; n <= 200; n++) {
            dp[1][n] = 1;
        }
        for (int k = 1; k <= 200; k++) {
            dp[k][0] = 1;
        }
        for (int k = 1; k <= 200; k++) {
            for (int n = 1; n <= 200; n++) {
                dp[k][n] = (dp[k-1][n] + dp[k][n-1]) % DIV_NUM;
            }
        }
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        long answer = dp[K][N] % DIV_NUM;
        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }

}