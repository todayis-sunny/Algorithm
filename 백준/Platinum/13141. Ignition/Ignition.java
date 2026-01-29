// 13141. [P5] Ignition.

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static final int INF = Integer.MAX_VALUE;
    static int N, M;
    static double[][] dp;
    static Edge[] edges;
    static double ans = INF;
    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        dp = new double[N][N];
        edges = new Edge[M];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], INF);
        }
        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int dist = Integer.parseInt(st.nextToken());
            dp[a][b] = dp[b][a] = Math.min(dp[a][b], dist);
            edges[m] = new Edge(a, b, dist);
        }
    }

    static void solve() {
        floyd();
        for (int i = 0; i < N; i++) {
            double max = 0.0;
            for (Edge edge : edges) {
                max = Math.max(max, (dp[i][edge.a] + edge.dist + dp[edge.b][i]) / 2);
            }
            ans = Math.min(ans, max);
        }
    }

    static void output() throws IOException {
        bw.write(String.valueOf(ans));
        bw.flush();
    }

    static void floyd() {
        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j]);
                }
            }
        }
        for (int i = 0; i < N; i++) {
            dp[i][i] = 0;
        }
    }

    static class Edge {
        int a, b, dist;

        public Edge(int a, int b, int dist) {
            this.a = a;
            this.b = b;
            this.dist = dist;
        }
    }
}
