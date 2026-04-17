// 11062. [G3] 카드 게임.

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int TC;
    static int N;
    static int[] card; // [i] = k : i번째 카드의 점수 k
    static int[][] dp; // [left][right] = score : 가장 좌측에 남은 카드가 left인덱스 가장 우측에 남은 카드가 right일 때, 얻을 수 있는 (최대/최수) 점수

    public static void main(String[] args) throws IOException {
        input();

        output();
    }

    static void input() throws IOException {
        TC = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < TC; tc++) {
            N = Integer.parseInt(br.readLine());
            card = new int[N];
            dp = new int[N][N];
            st = new StringTokenizer(br.readLine());
            for (int n = 0; n < N; n++) {
                card[n] = Integer.parseInt(st.nextToken());
            }
            solve();
        }
    }

    static void solve() {
        dfs(0, N - 1, true);
        sb.append(dp[0][N - 1]).append("\n");
    }

    static void output() throws IOException {
        bw.write(sb.toString());
        bw.flush();
    }

    static int dfs(int left, int right, boolean myTurn) {
        // 게임이 종료됨.
        if (left > right) return 0;
        // 값이 있는 경우 그대로 반환
        if (dp[left][right] != 0) return dp[left][right];
        // 내 차례
        if (myTurn) {
            return dp[left][right] = Math.max(card[left] + dfs(left + 1, right, false), card[right] + dfs(left , right - 1, false));
        }
        // 상대 차례
        else {
            return dp[left][right] = Math.min(dfs(left + 1, right, true), dfs(left, right - 1, true));
        }

    }
}
