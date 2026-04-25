// 02887. [P5] 행성 터널.

import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N;
    static int[] parent;
    static Planet[] planets;
    static int ans = 0;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        planets = new Planet[N];
        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            planets[n] = new Planet(n, x, y, z);
        }
        // 부모 초기화
        parent = new int[N];
        for (int i = 1; i < N; i++) {
            parent[i] = i;
        }
    }

    static void solve() {
        List<Edge> edges = new ArrayList<>();
        // x에 대해
        Arrays.sort(planets, Comparator.comparingInt(p -> p.x));
        for (int i = 0; i < N - 1; i++) {
            Planet p1 = planets[i];
            Planet p2 = planets[i + 1];
            int distance = p2.x - p1.x;
            edges.add(new Edge(p1.id, p2.id, distance));
        }
        // y에 대해
        Arrays.sort(planets, Comparator.comparingInt(p -> p.y));
        for (int i = 0; i < N - 1; i++) {
            Planet p1 = planets[i];
            Planet p2 = planets[i + 1];
            int distance = p2.y - p1.y;
            edges.add(new Edge(p1.id, p2.id, distance));
        }
        // z에 대해
        Arrays.sort(planets, Comparator.comparingInt(p -> p.z));
        for (int i = 0; i < N - 1; i++) {
            Planet p1 = planets[i];
            Planet p2 = planets[i + 1];
            int distance = p2.z - p1.z;
            edges.add(new Edge(p1.id, p2.id, distance));
        }
        edges.sort(Comparator.comparingInt(Edge::getValue));
        int edgeCount = 0;
        for (Edge edge : edges) {
            if (edgeCount == N -1) break;

            int from = find(edge.from);
            int to = find(edge.to);

            if (from != to) {
                ans += edge.value;
                union(edge.from, edge.to);
                edgeCount++;
            }
        }
    }

    static void output() throws IOException {
        bw.write(String.valueOf(ans));
        bw.flush();
    }

    static int find(int x) {
        if (x == parent[x]) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a > b) {
            int c = a;
            a = b;
            b = c;
        }
        parent[a] = b;
    }

    static class Planet {
        int id;
        int x, y, z;

        public Planet(int id, int x, int y, int z) {
            this.id = id;
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

    static class Edge {
        int from, to;
        int value;

        public Edge(int from, int to, int value) {
            this.from = from;
            this.to = to;
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}
