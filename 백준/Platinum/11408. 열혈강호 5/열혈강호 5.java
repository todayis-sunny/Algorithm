// 11408. [P3] 열혈강호 5.

import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    static final int INF = Integer.MAX_VALUE; // 충분히 큰 값

    static int N, M; // N: 직원의 수, M: 일의 개수
    static int S, T; // Source와 Sink

    static List<List<Edge>> adj = new ArrayList<>(); // 인접 리스트
    static int[] dist; // 최단 경로 비용
    static int[] parentEdge; // 최단 경로 역추적을 위한 부모 간선 인덱스
    static int[] parentNode; // 최단 경로 역추적을 위한 부모 노드

    static int maxFlow = 0; // 최대 수
    static long minCost = 0; // 비용 합계는 long을 사용해야 안전함

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void addEdge(int from, int to, int cap, int cost) {
        // 정방향 간선 추가
        adj.get(from).add(new Edge(to, cap, cost, adj.get(to).size()));
        // 역방향 간선 추가 (용량 0, 비용 -cost)
        adj.get(to).add(new Edge(from, 0, -cost, adj.get(from).size() - 1));
    }

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        S = 0;         // Source 노드
        T = N + M + 1; // Sink 노드 (노드 총 개수: N + M + 2)

        // 인접 리스트 초기화 (노드 0 ~ T)
        for (int i = 0; i <= T; i++) {
            adj.add(new ArrayList<>());
        }

        // 1. Source -> 직원 (E: 1 ~ N) 간선
        for (int e = 1; e <= N; e++) {
            addEdge(S, e, 1, 0); // 용량 1 (직원당 일 1개), 비용 0
        }

        // 2. 일 -> Sink (J: N+1 ~ N+M) 간선
        for (int j = 1; j <= M; j++) {
            addEdge(N + j, T, 1, 0); // 용량 1 (일 1개), 비용 0
        }

        // 3. 직원 -> 일 간선 (입력)
        for (int e = 1; e <= N; e++) {
            st = new StringTokenizer(br.readLine());
            int count = Integer.parseInt(st.nextToken());
            for (int cnt = 0; cnt < count; cnt++) {
                int j = Integer.parseInt(st.nextToken()); // 일 번호 (1 ~ M)
                int c = Integer.parseInt(st.nextToken()); // 월급 (비용)
                // 직원은 1~N, 일은 N+1~N+M 노드 번호 사용
                addEdge(e, N + j, 1, c); // 용량 1, 비용 c
            }
        }
    }

    // SPFA (Shortest Path Faster Algorithm)를 이용한 최소 비용 경로 탐색
    static boolean spfa() {
        dist = new int[T + 1];
        parentEdge = new int[T + 1];
        parentNode = new int[T + 1];
        Arrays.fill(dist, INF);
        boolean[] inQueue = new boolean[T + 1];

        Queue<Integer> q = new LinkedList<>();
        dist[S] = 0;
        q.offer(S);
        inQueue[S] = true;

        while (!q.isEmpty()) {
            int u = q.poll();
            inQueue[u] = false;

            for (int i = 0; i < adj.get(u).size(); i++) {
                Edge e = adj.get(u).get(i);
                int v = e.to;

                // 잔여 용량이 있고, 더 저렴한 경로를 찾은 경우
                if (e.capacity > 0 && dist[v] > dist[u] + e.cost) {
                    dist[v] = dist[u] + e.cost;
                    parentNode[v] = u;
                    parentEdge[v] = i; // 부모 간선의 인접 리스트 인덱스 기록

                    if (!inQueue[v]) {
                        q.offer(v);
                        inQueue[v] = true;
                    }
                }
            }
        }
        // Sink까지 도달할 수 있는 경로가 있어야 함
        return dist[T] != INF;
    }

    static void solve() {
        while (spfa()) {
            int flow = INF;

            // 1. 유량 (flow) 결정
            // Sink에서 Source까지 경로를 역추적하며 최소 잔여 용량 (flow)을 찾음
            for (int v = T; v != S; v = parentNode[v]) {
                int u = parentNode[v];
                int edgeIdx = parentEdge[v];
                flow = Math.min(flow, adj.get(u).get(edgeIdx).capacity);
            }

            // 2. 유량 흘려보내기 및 비용 갱신
            maxFlow += flow;
            minCost += (long) flow * dist[T]; // dist[T]는 Sink까지의 최소 비용

            // 3. 간선 용량 갱신
            for (int v = T; v != S; v = parentNode[v]) {
                int u = parentNode[v];
                int edgeIdx = parentEdge[v];

                // 정방향 간선 용량 감소
                adj.get(u).get(edgeIdx).capacity -= flow;

                // 역방향 간선 용량 증가 (잔여 용량 복원)
                int revIdx = adj.get(u).get(edgeIdx).rev;
                adj.get(v).get(revIdx).capacity += flow;
            }
        }
    }

    static void output() throws IOException {
        bw.write(maxFlow + "\n");
        bw.write(String.valueOf(minCost));
        bw.flush();
    }

    // 간선 클래스 정의
    static class Edge {
        int to;        // 도착 노드
        int capacity;  // 잔여 용량
        int cost;      // 비용
        int rev;       // 역방향 간선 인덱스 (인접 리스트 내부에서)

        public Edge(int to, int cap, int cost, int rev) {
            this.to = to;
            this.capacity = cap;
            this.cost = cost;
            this.rev = rev;
        }
    }
}
