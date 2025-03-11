// 01162. [P5] 도로포장.

import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N, M, K; // N: 도시의 수, K: 도로의 수, M: 포장할 도로의 수
    static long[][] dist;
    static List<List<Road>> graph;
    static long ans;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            // 도로 양방향 연결
            graph.get(a).add(new Road(b, c, 0));
            graph.get(b).add(new Road(a, c, 0));
        }
        dist = new long[N + 1][K + 1];
        for (long[] d : dist) {
            Arrays.fill(d, Long.MAX_VALUE);
        }
        dist[1][0] = 0;
    }

    static void solve() {
        dijkstra();
        ans = Long.MAX_VALUE;
        for (int k = 0; k <= K; k++) {
            ans = Math.min(ans, dist[N][k]);
        }
    }

    static void output() throws IOException {
        bw.write(String.valueOf(ans));
        bw.flush();
    }

    static class Road {
        int to;
        long weight;
        int cnt;

        public Road(int to, long weight, int cnt) {
            this.to = to;
            this.weight = weight;
            this.cnt = cnt;
        }

        public long getWeight() {
            return weight;
        }
    }

    static void dijkstra() {
        PriorityQueue<Road> pq = new PriorityQueue<>(Comparator.comparingLong(Road::getWeight));
        pq.offer(new Road(1, 0, 0));
        while (!pq.isEmpty()) {
            Road curr = pq.poll();
            if (curr.weight > dist[curr.to][curr.cnt]) {
                continue;
            }
            for (Road next : graph.get(curr.to)) {
                // 도로를 포장했을 경우 -> next 로드의 weight 값을 합산 X
                if (curr.cnt < K && dist[next.to][curr.cnt + 1] > dist[curr.to][curr.cnt]) {
                    dist[next.to][curr.cnt + 1] = dist[curr.to][curr.cnt];
                    pq.offer(new Road(next.to, dist[next.to][curr.cnt + 1], curr.cnt + 1));
                }
                // 도로를 포장하지 않았을 경우 -> next 로드의 weight 값을 합산 O
                if (dist[next.to][curr.cnt] > dist[curr.to][curr.cnt] + next.weight) {
                    dist[next.to][curr.cnt] = dist[curr.to][curr.cnt] + next.weight;
                    pq.offer(new Road(next.to, dist[next.to][curr.cnt], curr.cnt));
                }
            }
        }
    }
}
