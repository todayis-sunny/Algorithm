// 02611. [G2] 자동차경주.

import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N, M;
    static List<List<Edge>> adj = new ArrayList<>();
    static int[] dp;
    static int[] route; // 역추적
    static int[] inDegree; // 진입 차수

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        // N, M 초기화
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        // 인접 리스트 및 dp와 역추적 경로 초기화
        for (int n = 0; n <= N; n++) {
            adj.add(new ArrayList<>());
        }
        dp = new int[N + 1];
        route = new int[N + 1];
        inDegree = new int[N + 1];
        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            adj.get(from).add(new Edge(to, value));
            inDegree[to]++;
        }
    }

    static void solve() {
        bfs();
    }

    static void output() throws IOException {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        int node = route[1];
        while (node != 1) {
            stack.push(node);
            node = route[node];
        }
        stack.push(1);
        bw.write(dp[1] + "\n");
        while (!stack.isEmpty()) {
            bw.write(stack.pop() + " ");
        }
        bw.flush();
    }

    static void bfs() {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);

        while (!queue.isEmpty()) {
            int curr = queue.poll();
            for (Edge edge : adj.get(curr)) {
                // 갱신해볼 가치가 있는 경우
                if (dp[curr] + edge.value > dp[edge.to]) {
                    dp[edge.to] = dp[curr] + edge.value;
                    route[edge.to] = curr;
                };
                // 아직 들어올 경로가 더 남아 있는 경우
                if (--inDegree[edge.to] > 0) continue;
                // 원 점으로 온 경우는 스킵
                if (edge.to == 1) continue;
                queue.offer(edge.to);
            }
        }
    }

    static class Edge {
        int to, value;

        public Edge(int to, int value) {
            this.to = to;
            this.value = value;
        }
    }
}
