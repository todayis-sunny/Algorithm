// 02611. [G2] 자동차경주.

import java.io.*;
import java.util.*;

import static java.lang.System.in;
import static java.lang.System.out;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out));
    static StringTokenizer st;
    static int N, M; // N : 지점의 개수, M : 도로의 개수
    static ArrayList<ArrayList<Edge>> adj = new ArrayList<>();
    static int[] dp;
    static int[] route; // [i] = j : j -> i
    static int[] inDegree; // 진입차수 [i] = k : i번째 노드는 k개의 경로로 올 수 있다.


    static class Edge {
        int idx, cost;

        public Edge(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        dp = new int[N + 1];
        route = new int[N + 1];
        inDegree = new int[N + 1];

        for (int m = 0; m <= M; m++) {
            adj.add(new ArrayList<>());
        }


        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());

            adj.get(p).add(new Edge(q, r));
            inDegree[q]++;
        }
    }

    static void solve() {
        dfs(new Edge(1, 0));
//        bfs();
    }

    static void dfs(Edge curr) {
        for(Edge next : adj.get(curr.idx)) {
            // 여러가지 방식으로 방문할 수 있어
            // 결국 DP가 방문에 대한 배열이다
            // 초기에 DP의 값은 0이 들어가 있음
            // 그러면 이거를 최대값 방식으로 갱신다
            // dp를 최대값이 갱신될때 route랑 dp값을 변경
            if (curr.cost + next.cost > dp[next.idx]) {
                dp[next.idx] = curr.cost + next.cost;
                route[next.idx] = curr.idx;
            }
            // 진입차수 0이라는건
            // 현재 정점으로부터 올 수 있는 모든 경로를 탐색했을때,
            // = 최대값이 확정되었을때,
            if (--inDegree[next.idx] == 0) {
                if (next.idx != 1) {
                    next.cost = dp[next.idx];
                    dfs(next);
                }
            }

        }
    }

    static void bfs() {
        Queue<Edge> queue = new LinkedList<>();
        queue.offer(new Edge(1, 0));
        while (!queue.isEmpty()) {
            Edge curr = queue.poll();
            for(Edge next : adj.get(curr.idx)) {
                // 여러가지 방식으로 방문할 수 있어
                // 결국 DP가 방문에 대한 배열이다
                // 초기에 DP의 값은 0이 들어가 있음
                // 그러면 이거를 최대값 방식으로 갱신다
                // dp를 최대값이 갱신될때 route랑 dp값을 변경
                if (dp[curr.idx] + next.cost > dp[next.idx]) {
                    dp[next.idx] = dp[curr.idx] + next.cost;
                    route[next.idx] = curr.idx;
                }
                // 진입차수 0이라는건
                // 현재 정점으로부터 올 수 있는 모든 경로를 탐색했을때,
                // = 최대값이 확정되었을때,
                if (--inDegree[next.idx] == 0) {
                    if (next.idx != 1) {
                        queue.offer(next);

                    }
                }

            }
        }
    }

    // 1번에서 출발하여 1번으로 돌아오면 종료
    // 그 중 최대값 출력 및 경로 출력

    static void output() throws IOException {
        bw.write(dp[1] + "\n");
        // route[i] = j
        // j : i로 올 수 있는 경로다
        // route[a] = b
        // route[b] = c
        // c -> b -> a
        // 1 -> 3 -> 5 -> 1
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        int curr = route[1];
        while(curr != 1) {
            stack.push(curr);
            curr = route[curr];
        }
        stack.push(1);
        while (!stack.isEmpty()) {
            bw.write(stack.pop() + " ");
        }
        bw.flush();
    }
}