// 11657. [G4] 타임머신.
import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N, M, A, B, C, curr, next, cost;
    static Bus[] routes;
    static long[] dist;
    static final int INF = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        routes = new Bus[M];
        dist = new long[N+1];
        for (int n = 1; n < N+1; n++) {
            dist[n] = INF;
        }

        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            routes[m] = new Bus(A, B, C);
        }
        if (bellmanFord(1)) {
            bw.write("-1\n");
        } else {
            for (int n = 2; n < N+1 ; n++) {
                if (dist[n] == INF) {
                    bw.write("-1\n");
                } else {
                    bw.write(dist[n] + "\n");
                }
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }

    static boolean bellmanFord(int start) {
        // 시작 노드에 대해서 초기화.
        dist[start] = 0;
        // 전체 N번의 라운드를 반복.
        for (int n = 1; n < N+1; n++) {
            // 매 반복마다 "모든 간선"을 확인.
            for (int m = 0; m < M; m++) {
                curr = routes[m].u;
                next = routes[m].v;
                cost = routes[m].cost;
                if (dist[curr] == INF) {
                    continue;
                }
                // 현재 간선을 거쳐서 다른 노드로 이동하는 거리가 더 짧은 경우.
                if (dist[curr] + cost < dist[next]) {
                    dist[next] = dist[curr] + cost;
                    if (n == N) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    static class Bus{
        int u;
        int v;
        int cost;

        public Bus(int u, int v, int cost) {
            this.u = u;
            this.v = v;
            this.cost = cost;
        }
    }
}