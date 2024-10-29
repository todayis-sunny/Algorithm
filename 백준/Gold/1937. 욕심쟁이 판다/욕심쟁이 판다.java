// 01937. [G3] 욕심쟁이 판다.
import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[][] forest;
    static int[][] dp;
    static int N, answer;


    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        forest = new int[N][N];
        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 0; col < N; col++) {
                forest[row][col] = Integer.parseInt(st.nextToken());
            }
        }
        dp = new int[N][N];
        answer = 0;
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                answer = Math.max(dfs(row, col), answer);
            }
        }
        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }
    public static int dfs(int x, int y) {
        if (dp[x][y] != 0) {
            return dp[x][y];
        }
        dp[x][y] = 1;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
                continue;
            }
            if (forest[nx][ny] <= forest[x][y]) {
                continue;
            }
            dp[x][y] = Math.max(dp[x][y], dfs(nx, ny) + 1);
        }
        return dp[x][y];
    }
}