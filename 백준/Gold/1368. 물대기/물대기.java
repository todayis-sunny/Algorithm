// 01368. [G2] 물대기.

import java.io.*;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N;
    static int[] well; // 1-based
    static int[][] cost; // 1-based;
    static int[] parent; // 1-based;
    static int ans = 0;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        well = parent = new int[N + 1];
        cost = new int[N + 1][N + 1];
        for (int n = 1; n <= N; n++) {
            cost[0][n] = Integer.parseInt(br.readLine());
        }
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static void solve() {
        for (int i = 0; i <= N; i++) {
            parent[i] = -1;
        }
        mst();
    }

    static void output() throws IOException {
        bw.write(String.valueOf(ans));
        bw.flush();
    }

    static void mst() {
        PriorityQueue<Edge> pq = new PriorityQueue<>(
                Comparator.comparingInt(e -> e.c)
        );
        for (int i = 0; i <= N; i++) {
            pq.offer(new Edge(0, i, cost[0][i]));
        }
        for (int i = 1; i < N; i++) {
            for (int j = i + 1; j <= N; j++) {
                pq.offer(new Edge(i, j, cost[i][j]));
            }
        }
        int count = 0;
        while (!pq.isEmpty() && count < N) {
            Edge edge = pq.poll();
            // 하나의 집합이 될때까지
            if (find(edge.v1) == find(edge.v2)) continue;
            // 집합이 아니라면 union
            union(edge.v1, edge.v2);
            count++;
            ans += edge.c;
        }
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a == b) { // 두 루트가 같은 경우
            return;
        }
        if (parent[a] > parent[b]) { // b의 랭크가 더 높은 경우 (음수 이므로 역)
            int c = a;
            a = b;
            b = c;
        }
        if (parent[a] == parent[b]) { // 위 조건으로 인해 a랭크 >= b랭크
            parent[a]--;
        }
        parent[b] = a;
    }

    static int find(int x) {
        if (parent[x] < 0) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }



    static class Edge {
        int v1, v2, c;

        public Edge(int v1, int v2, int c) {
            this.v1 = v1;
            this.v2 = v2;
            this.c = c;
        }
    }
}
