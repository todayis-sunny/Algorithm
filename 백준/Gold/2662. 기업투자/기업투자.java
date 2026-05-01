// 02662. [G2] 기업투자.

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N, M;
    static int[][] cost; // [i][j] = k : i원을 j기업에 투자할 때 얻을 수 있는 이익 k
    static int[][] dp; // [i][j] = k : i투자 금액을 소지하고 j기업까지 투자했을때 얻을 수 있는 최대 이익 k
    static int[] visited;
    static int ans = 0;
    static int[] info;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        // N, M 입력
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 배열 초기화
        cost = new int[N + 1][M + 1];
        dp = new int[N + 1][M + 1];
        visited = new int[N + 1];
        info = new int[N + 1];
        for (int[] d : dp) {
            Arrays.fill(d, -1);
        }
        Arrays.fill(visited, -1);
        // 이익 입력
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            for (int j = 1; j <= M; j++) {
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static void solve() {
        dp[N][0] = 0;
        dfs(N, 1);
    }

    static void output() throws IOException {
        for (int i = 0; i <= N; i++) {
            ans = Math.max(ans, dp[i][M]);
        }
        bw.write(String.valueOf(ans));
        bw.newLine();
        for (int j = 1; j <= M; j++) {
            bw.write(info[j] + " ");
        }
        bw.flush();
    }

    static void dfs(int money, int depth) {
        if (depth > M) {
            if (money != 0) return;
            if (ans >= dp[money][M]) return;
            ans = dp[money][M];
            for (int j = 1; j <= M; j++) {
                info[j] = visited[j];
            }
            return;
        }
        // 다음 투자할 기업에 투자 금액을 선정
        // 현재 보유하고 있는 자산은 money
        // 내가 투자할 금액은 0 ~ money
        for (int i = 0; i <= money; i++) {
            // 현재 기업에 투자할 금액은 i
            // 이전 기업들까지 money원을 남기고 얻는 최대 이익 + 현재 기업에 i원을 투자했을때 얻는 이익
            if (dp[money][depth - 1] + cost[i][depth] <= dp[money - i][depth]) {
                continue;
            }
            dp[money - i][depth] = dp[money][depth - 1] + cost[i][depth];
            visited[depth] = i;
            dfs(money - i, depth + 1);
            visited[depth] = - 1;
        }
    }
}
