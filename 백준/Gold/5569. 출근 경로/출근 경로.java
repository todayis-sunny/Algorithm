// 05569. [G4] 출근 경로
import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int w = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());
        int mod = 100_000;
        // 문제에서 동쪽, 북쪽을 -> 동쪽, 남쪽으로 변경.
        int[][][][] dp = new int[w+1][h+1][2][2]; // x, y, 현재방향(0:동, 1:남), 방향변경
        for (int x = 1; x <= w; x++) {
            dp[x][1][0][0] = 1;
        }
        for (int y = 1; y <= h; y++) {
            dp[1][y][1][0] = 1;
        }

        for (int x = 2; x <= w; x++) {
            for (int y = 2; y <= h; y++) {
                // current : 동, X
                // previous : 동, X + 동, O
                dp[x][y][0][0] = (dp[x-1][y][0][0] + dp[x-1][y][0][1]) % mod;
                // current : 동, O
                // previous : 동, X
                dp[x][y][0][1] = dp[x-1][y][1][0] % mod;
                // current : 남, X
                // previous : 남, X + 남, O
                dp[x][y][1][0] = (dp[x][y-1][1][0] + dp[x][y-1][1][1]) % mod;
                // current : 남, O
                // previous : 남, X
                dp[x][y][1][1] = dp[x][y-1][0][0] % mod;
            }
        }
        int answer = (dp[w][h][0][0] + dp[w][h][0][1] + dp[w][h][1][0] + dp[w][h][1][1]) % mod;
        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }
}