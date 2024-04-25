// 17069. [G4] 파이프 옮기기 2.
import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N;
    static int[][] arr;
    static long[][][] dp;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        // 점화식을 위한 위에 의미없는 공간 만들기.
        arr = new int[N+1][N];
        dp = new long[3][N+1][N];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp[0][1][1] = 1;
        // 0 : 가로 | 1 : 대각 | 2 : 세로
        for (int x = 1; x <= N; x++) {
            for (int y = 1; y < N; y++) {
                for (int d = 0; d <= 2; d++) {
                    if (check(x, y, d)) {
                        if(d == 0) {
                            dp[0][x][y] += dp[0][x][y-1] + dp[1][x][y-1];
                        } else if (d == 1) {
                            dp[1][x][y] += dp[0][x-1][y-1] + dp[1][x-1][y-1] + dp[2][x-1][y-1];
                        } else {
                            dp[2][x][y] += dp[1][x-1][y] + dp[2][x-1][y];
                        }
                    }
                }
            }
        }

        bw.write(String.valueOf(dp[0][N][N-1] + dp[1][N][N-1] + dp[2][N][N-1]));
        bw.flush();
        br.close();
        bw.close();
    }

    static boolean check(int x, int y, int dir) {
        if (dir % 2 == 0) {
            return arr[x][y] != 1;
        } else {
            return arr[x][y] != 1 && arr[x - 1][y] != 1 && arr[x][y - 1] != 1;
        }
    }
}