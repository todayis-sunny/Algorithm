// 02316. [P3] 도시 왕복하기 2.

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N, P, ans = 0; // N: 도시의 개수, P 도로의 개수
    static int[][] capacity, flow; // 용량, 유량  (n번 도시 : in: 2n - 2 -> out: 2n - 1)
    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        capacity = new int[N * 2][N * 2];
        flow = new int[N * 2][N * 2];
        // in -> out 1로 설정
        for (int n = 1; n <= N; n++) {
            capacity[2 * n - 2][2 * n - 1] = 1;
        }
        // INF로 설정
        for (int p = 0; p < P; p++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int ui = 2 * u - 2;
            int uo = 2 * u - 1;
            int vi = 2 * v - 2;
            int vo = 2 * v - 1;
            capacity[uo][vi] = capacity[vo][ui] = INF;
        }
    }

    static void solve() {
        ans = edmondsKarp(1, 2);
    }

    static void output() throws IOException {
        bw.write(String.valueOf(ans));
        bw.flush();
    }

    static int edmondsKarp(int source, int sink) {
        int totalFlow = 0;
        while (true) {
            int[] route = new int[N * 2];
            Queue<Integer> queue = new LinkedList<>();
            route[source] = source;
            queue.offer(source);

            while (!queue.isEmpty()) {
                int curr = queue.poll();
                for (int next = 2; next <= 2 * N - 1; next++) { // 0: source-in, 1: source-out 을 제외한 경로 탐색
                    if (capacity[curr][next] - flow[curr][next] > 0 && route[next] == 0) {
                        queue.offer(next);
                        route[next] = curr;
                        if (next == sink) break;
                    }
                }

            }
            if (route[sink] == 0) break;

            int minFlow = INF;
            for (int v = sink; v != source; v = route[v]) {
                int currFlow = capacity[route[v]][v] - flow[route[v]][v];
                minFlow = Math.min(minFlow, currFlow);
            }
            for (int v = sink; v != source; v = route[v]) {
                flow[route[v]][v] += minFlow;
                flow[v][route[v]] -= minFlow;
            }
            totalFlow += minFlow;
        }
        return totalFlow;
    }
}
