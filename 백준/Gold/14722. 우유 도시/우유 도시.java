// 14722. [G4] 우유 도시.

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static final int MIN = Integer.MIN_VALUE;
    static int N;
    static int[][] milk; // 1-based, 우유 도시 정보
    static int[][][] dp; // 1-based, [3 : 최근 마신 우유][x : 행 위치][y : 열 위치] : 마신 우유 수
    static int ans = 0;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        milk = new int[N + 1][N + 1];
        dp = new int[3][N + 1][N + 1];
        for (int x = 1; x <= N; x++) {
            st = new StringTokenizer(br.readLine());
            for (int y = 1; y <= N; y++) {
                milk[x][y] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static void solve() {
        // 불가능 수치를 넣어줌
        for (int i = 0; i <= N; i++) {
            for (int m = 0; m < 3; m++) {
                dp[m][i][0] = dp[m][0][i] = MIN;
            }
        }
        // 가능한 수치를 초기화 함으로써 갱신시작에 표본
        dp[2][0][1] = dp[2][1][0] = 0; // 바나나를 먹었다고 가정 (0, 1)과 (1, 0) -> (1, 1)에서 갱신

        // 하나의 지점을 상과 좌에서 뽑아오기
        for (int x = 1; x <= N; x++) {
            for (int y = 1; y <= N; y++) {
                // 이전 상태 전이
                for (int m = 0; m < 3; m++) {
                    // 안 마시기
                    dp[m][x][y] = Math.max(dp[m][x - 1][y], dp[m][x][y - 1]);
                }
                // 마시기
                int m = milk[x][y];
                int p = (m + 2) % 3;
                dp[m][x][y] = Math.max(dp[m][x][y], dp[p][x][y] + 1);
            }
        }
        // 최종 지점 확인
        for (int i = 0; i < 3; i++) {
            ans = Math.max(ans, dp[i][N][N]);
        }
    }

    static void output() throws IOException {
        bw.write(String.valueOf(ans));
        bw.flush();
    }
}
