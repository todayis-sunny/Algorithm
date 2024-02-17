// 15486. [G5] 퇴사2
import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N+2][2];
        int[] dp = new int[N+2];

        for (int n = 1; n < N+1; n++) {
            st = new StringTokenizer(br.readLine());

            int T = Integer.parseInt(st.nextToken());
            int P = Integer.parseInt(st.nextToken());

            arr[n][0] = T;
            arr[n][1] = P;
        }

        int maximunm = -1;
        for(int i = 1; i <= N + 1; i++){
            if(maximunm < dp[i]) {
                maximunm = dp[i];
            }
            int next = i + arr[i][0];
            if(next < N+2) {
                dp[next] = Math.max(dp[next], maximunm + arr[i][1]);
            }
        }
        bw.write(String.valueOf(dp[N+1]));
        bw.flush();
        bw.close();
        br.close();
    }
}
