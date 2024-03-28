// 01753. [G4] 최단경로.
import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static PriorityQueue<Node> pq;
    static int[] dp;
    static int V, E, K, u, v, w;
    static final int INF = 1_000_000_000;
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(br.readLine()) - 1;
        pq = new PriorityQueue<>(Comparator.comparing(Node::getCost));
        ArrayList<ArrayList<Node>> list = new ArrayList<>();
        dp = new int[V];
        Arrays.fill(dp, INF);
        dp[K] = 0;
        for (int i = 0; i < V; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken()) - 1;
            v = Integer.parseInt(st.nextToken()) - 1;
            w = Integer.parseInt(st.nextToken());
            list.get(u).add(new Node(v, w));
        }

        pq.offer(new Node(K, dp[K]));
        while (!pq.isEmpty()) {
            Node prev = pq.poll();
            for (Node next : list.get(prev.idx)) {
                if (dp[next.idx] > dp[prev.idx] + next.cost) {
                    dp[next.idx] = dp[prev.idx] + next.cost;
                    pq.offer(new Node(next.idx, dp[next.idx]));
                }
            }
        }

        for (int i = 0; i < V; i++) {
            bw.write((dp[i] == INF ? "INF" : dp[i]) + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    static class Node{
        int idx;
        int cost;

        Node(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }

        public int getCost() {
            return cost;
        }
    }
}
