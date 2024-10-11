// 01520. [G3] 내리막 길.
import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N, M, ans;
    static int[][] arr;
    static int[][] dp;
    static int[] dx = new int[]{-1, 1, 0, 0};
    static int[] dy = new int[]{0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        ans = 0;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        dp = new int[N][M];
        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            for (int m = 0; m < M; m++) {
                arr[n][m] = Integer.parseInt(st.nextToken());
                dp[n][m] = -1;
            }
        }
        bw.write(String.valueOf(dfs(0, 0)));
        bw.flush();
        bw.close();
        br.close();
    }
    static int dfs(int x, int y) {
        if(x == N-1 && y == M-1) {
            return 1;
        }
        if(dp[x][y] != -1) {
            return dp[x][y];
        }
        dp[x][y] = 0;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(nx < 0 || nx >= N || ny < 0 || ny >= M) {
                continue;
            }
            if(arr[nx][ny] >= arr[x][y]) {
                continue;
            }
            dp[x][y] += dfs(nx, ny);
        }
        return dp[x][y];
    }
}
