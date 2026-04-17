// 01014. [P4] 컨닝.

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static final char POSSIBLE = '.', IMPOSSIBLE = 'x';
    static int TC, N, M, LIMIT;
    static boolean[][] seat;
    static int[][] dp;


    public static void main(String[] args) throws IOException {
        input();
        output();
    }

    static void input() throws IOException {
        TC = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < TC; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            // 최대치 초기화
            LIMIT = N * M;
            // 자리 초기화
            seat = new boolean[N][M];
            // dp 초기화
            dp = new int[LIMIT][(1 << (M + 1))];
            for (int[] d: dp) {
                Arrays.fill(d, -1);
            }
            for (int n = 0; n < N; n++) {
                String input = br.readLine();
                for (int m = 0; m < M; m++) {
                    if (input.charAt(m) == POSSIBLE) {
                        seat[n][m] = true;
                    }
                }
            }
            solve();
        }
    }

    static void solve() {
        int ans = dfs(0, 0);
        sb.append(ans).append("\n");
    }

    static void output() throws IOException {
        bw.write(sb.toString());
        bw.flush();
    }

    static int dfs(int idx, int bit) {
        // 좌석 배치가 넘어가면 종료
        if (idx >= LIMIT) {
            return 0;
        }
        // 메모제이션 되어 있으면 값 가져오기
        if (dp[idx][bit] != -1) {
            return dp[idx][bit];
        }
        dp[idx][bit] = 0;
        int row = idx / M, col = idx % M;
        // 앉을 수 없는 좌석인 경우
        if (!seat[row][col]) {
            dp[idx][bit] += dfs(idx + 1, bit >> 1);
        } else { // 앉을 수 있는 좌석인 경우
            int s1 = 0, s2 = 0;
            if (checkSeat(col, bit)) {
                s1 = 1 + dfs(idx + 1, (bit >> 1) | (1 << (M)));
            }
            s2 = dfs(idx + 1, (bit >> 1));

            dp[idx][bit] += Math.max(s1, s2);
        }

        return dp[idx][bit];
    }

    static boolean checkSeat(int col, int bit) {
        // 중간인 경우 : 좌상, 우상, 좌 확인
        int[] adj = new int[]{0, 2, M};
        // 좌측에 붙어 있는 경우 : 우상 확인
        if (col == 0) {
            adj = new int[]{2};
        }
        // 우측에 붙어 있는 경우 : 좌상, 좌 확인
        else if (col == M -1) {
            adj = new int[]{0, M};
        }

        for (int a : adj) {
            if ((bit & (1 << a)) != 0) return false;
        }
        return true;
    }
}
