// 17090. [G3] 미로 탈출하기.
import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N, M, ans;
    static char[][] maze;
    static int[][] dp; // 1 : 가능 | 0 : 초기값 | -1 : 불가능 | -2 : 현재 루프
    public static void main(String[] args) throws IOException {
        ans = 0;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        maze = new char[N][M];
        dp = new int[N][M];

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


        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
        br.close();
    }

    static int check(int x, int y) {
        if(x < 0 || x >= N || y < 0 || y >= M || dp[x][y] == 1) {
            ans++;
            return 1;
        }
        if(dp[x][y] == -1 || dp[x][y] == -2) {
            return -1;
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
