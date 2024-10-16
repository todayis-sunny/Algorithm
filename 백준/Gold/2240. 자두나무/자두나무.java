// 02240. [G5] 자두나무.
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        if (T <= W) {
            bw.write(String.valueOf(T));
            bw.flush();
            bw.close();
            br.close();
            return;
        }

        int[][] dp = new int[W+1][T+1];
        for (int t= 1; t <= T; t++) {
            int plum = Integer.parseInt(br.readLine());
            int limit = Math.min(W, t);
            for (int w = 0; w <= limit; w++) {
                int tmp = w % 2 != plum % 2? 1 : 0;
                if (w == 0) {
                    dp[0][t] = dp[0][t-1] + tmp;
                } else {
                    dp[w][t] = Math.max(dp[w-1][t-1], dp[w][t-1]) + tmp;
                }
            }

        }
        int answer = 0;
        for (int w = 0; w <= W; w++) {
            answer = Math.max(answer, dp[w][T]);
        }
        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }
}