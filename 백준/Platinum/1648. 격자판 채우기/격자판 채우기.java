// 01648. [P3] 격자판 채우기.

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static final int MOD = 9_901;
    static int N, M;
    static int LIMIT;
    static int[][] dp;
    static int ans;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        LIMIT = N * M;
        // dp 초기화
        dp = new int[N * M][(1 << M)];
        for (int i = 0; i < LIMIT; i++) {
            Arrays.fill(dp[i], -1);
        }
    }

    static void solve() {
        ans = dfs(0, 0);
    }

    static void output() throws IOException {
        bw.write(String.valueOf(ans));
        bw.flush();
    }

    static int dfs(int idx, int bit) {
        // N * M 도달한 경우 확인
        if (idx == LIMIT) {
            // bit상태가 0(삐져 나온게 있는지 체크)
            return bit == 0? 1: 0;
        }
        // 메모제이션이 되어 있으면 값 가져오기
        if (dp[idx][bit] != -1) {
            return dp[idx][bit];
        }
        dp[idx][bit] = 0;
        // idx 칸이 이미 채워져 있는 경우
        if ((bit & 1) == 1) {
            dp[idx][bit] += dfs(idx + 1, bit >> 1);
        }
        // idx 칸을 채워야 하는 경우
        else {
            // idx 칸에 2x1 도미노 채우는 경우 (세로)
            // idx 칸 바로 아래칸은 비어 있음
            dp[idx][bit] += dfs(idx + 1, (bit >> 1) | (1 << (M -1)));
            // idx 칸에 1x2 도미노 채우는 경우 (가로)
            // 현재 행에서 마지막 열에 위치한 칸이면 채우지 못 함
            // 또한, idx의 우측 칸이 비어있지 않으면 채우지 못 함
            if (idx % M < (M-1) && (bit & 2) == 0) {
                dp[idx][bit] += dfs(idx + 2, (bit >> 2));
            }
        }
        return dp[idx][bit] %= MOD;
    }
}
