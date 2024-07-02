// 02624. [G4] 동전 바꿔주기.
import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int T, k, p, n;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());
        dp = new int[T+1];
        dp[0] = 1;
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            p = Integer.parseInt(st.nextToken());
            n = Integer.parseInt(st.nextToken());
            int tmp = p*n;
            int limit = n;
            if (tmp > T) {
                tmp = p * (T / p);
                limit = tmp / p;
            }
            for (int t = T; t >= 0; t--) {
                if (dp[t] > 0) {
                    for (int l = limit; l > 0; l--) {
                        int sol = t + p*l;
                        if (sol > T) {
                            continue;
                        }
                        dp[sol] += dp[t];
                    }
                }
            }
        }
//        for (int i = 0; i < dp.length; i++) {
//            System.out.println(dp[i]);
//        }
        bw.write(String.valueOf(dp[T]));
        bw.flush();
        bw.close();
        br.close();
    }

}
