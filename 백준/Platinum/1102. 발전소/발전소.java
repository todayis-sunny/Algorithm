// 01102. [P5] 발전소.

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N, goal, ans; // N : 발전소 개수, goal : 켜야 하는 목표 개수
    static int initCnt, initBit;
    // cost[i][j] : 발전소 i를 이용해서 발전소 j를 재시작할 때 드는 비용
    // dp[cnt][bit] : cnt개 켜져있을때 bit상태를 만드는 최소 비용
    static int[][] cost, dp;
    static final int INF = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        // N 입력
        N = Integer.parseInt(br.readLine());
        // 각 배열 초기화
        cost = new int[N][N];
        dp = new int[N + 1][1 << N];
        // cost 입력
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // dp 입력
        for (int[] d: dp) {
            Arrays.fill(d, -1);
        }
        // 상태 입력
        initCnt = 0;
        initBit = 0;
        String status = br.readLine();
        for (int n = 0; n < N; n++) {
            if (status.charAt(n) == 'Y') {
                initCnt++;
                initBit |= (1 << n);
            }
        }
        // 목표 입력
        goal = Integer.parseInt(br.readLine());
    }

    static void solve() {
        ans = dfs(initCnt, initBit);
    }

    static void output() throws IOException {
        if (ans == INF) ans = -1;
        bw.write(String.valueOf(ans));
        bw.flush();
    }

    static int dfs(int cnt, int bit) {
        // 목표 갯수에 도달하면 종료
        if (cnt >= goal) {
            return 0;
        }
        // 메모제이션 되어 있으면 리턴
        if (dp[cnt][bit] != -1) {
            return dp[cnt][bit];
        }
        // 큰 수로 초기화
        dp[cnt][bit] = INF;
        // 메모제이션 실행
        for (int i = 0; i < N; i++) {
            if ((bit & (1 << i)) != 0) { // i가 켜져있고
                for (int j = 0; j < N; j++) {
                    if ((bit & (1 << j)) != 0) { // j가 켜져있으면 스킵
                        continue;
                    }
                    // 켜져있지 않은 발전소를 찾아 계산
                    dp[cnt][bit] = Math.min(dp[cnt][bit], dfs(cnt + 1, bit | (1 << j)) + cost[i][j]);
                }
            }
        }
        return dp[cnt][bit];
    }
}
