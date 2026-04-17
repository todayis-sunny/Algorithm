// 12869. [G4] 뮤탈리스크.

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N, ans;
    static int[][][] dp;
    static int[][] damage = new int[][]{
            {9, 3, 1}, {9, 1, 3},
            {3, 9, 1}, {3, 1, 9},
            {1, 9, 3}, {1, 3, 9},
    };

    public static void main(String[] args) throws IOException {
        input();
        solve();
        bw.write(String.valueOf(ans));
        bw.flush();
    }

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        dp = new int[61][61][61];
        ans = Integer.MAX_VALUE;
    }

    static void solve() {
        if (N == 1) {
            ans = (int) Math.ceil(Integer.parseInt(st.nextToken()) / 9.0);
        } else {
            int[] scv = new int[3];
            for (int i = 0; i < N; i++) {
                scv[i] = Integer.parseInt(st.nextToken());
            }
            dfs(scv, 0);
        }
    }

    public static void dfs(int[] scv, int cnt) {
        int scv1 = scv[0];
        int scv2 = scv[1];
        int scv3 = scv[2];
        // 정답 후보보다 크거나 같다면 종료
        if (ans <= cnt) {
            return;
        }
        // 공격횟수 최소값보다 현재 공격횟수가 같거나 클 경우 중단
        if (dp[scv1][scv2][scv3] != 0 && dp[scv1][scv2][scv3] <= cnt) {
            return;
        }
        dp[scv1][scv2][scv3] = cnt;

        // 모든 SCV가 파괴된 경우 중단
        if (gameOver(scv1, scv2, scv3)) {
            ans = cnt;
            return;
        }

        // 6가지 공격 패턴으로 SCV들을 공격
        for (int i = 0; i < 6; i++) {
            dfs(new int[] {hitSCV(scv1, damage[i][0]), hitSCV(scv2, damage[i][1]), hitSCV(scv3, damage[i][2]),}, cnt + 1);
        }
    }

    public static int hitSCV(int hp, int att) {
        return Math.max(hp - att, 0);
    }

    public static boolean gameOver(int scv1, int scv2, int scv3) {
        return scv1 == 0 && scv2 == 0 && scv3 == 0;
    }

}
