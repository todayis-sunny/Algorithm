// 17090. [G3] 미로 탈출하기.
import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N, M, ans;
    static char[][] maze;
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        maze = new char[N][M];
        dp = new int[N][M];
        for (int n = 0; n < N; n++) {
            Arrays.fill(dp[n], -1);
        }

        for (int n = 0; n < N; n++) {
            String input = br.readLine();
            for (int m = 0; m < M; m++) {
                maze[n][m] = input.charAt(m);
            }
        }

        for (int n = 0; n < N; n++) {
            for (int m = 0; m < M; m++) {
                check(n, m);
            }
        }
        ans = 0;
        for (int n = 0; n < N; n++) {
            for (int m = 0; m < M; m++) {
                if(dp[n][m] == 1) {
                    ans++;
                }
            }
        }
        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
        br.close();
    }

    static int check(int x, int y) {
        if(x < 0 || x >= N || y < 0 || y >= M) {
            return 1;
        }
        if(dp[x][y] == 1) {
            return 1;
        }
        if(dp[x][y] == 0 || dp[x][y] == -2) {
            return 0;
        }
        dp[x][y] = -2;
        char key = maze[x][y];

        if (key == 'U') {
            return dp[x][y] = check(x-1, y);
        } else if (key == 'D') {
            return dp[x][y] = check(x+1, y);
        } else if (key == 'L') {
            return dp[x][y] = check(x, y-1);
        } else {
            return dp[x][y] = check(x, y+1);
        }
    }
}
