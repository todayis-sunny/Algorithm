// 11400. [P4] 단절선.
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int V, E;
    static int cnt = 1;
    static int[] order;
    static List<Node> ans;
    static List<List<Integer>> adj;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        order = new int[V + 1];
        ans = new ArrayList<>();
        adj = new ArrayList<>();
        for (int i = 0; i <= V; i++) {
            adj.add(new ArrayList<>());
        }
        // 양방향 인접 리스트
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adj.get(a).add(b);
            adj.get(b).add(a);
        }

    }

    static void solve() {
        for (int i = 1; i<= V; i++) {
            if (order[i] == 0) {
                dfs(i, 0, adj);
            }
        }
        ans.sort((o1, o2) -> (o1.x == o2.x) ? o1.y - o2.y : o1.x - o2.x);
    }

    static void output() throws IOException {
        bw.write(ans.size() + "\n");
        for (Node node : ans) {
            bw.write(node.x + " " + node.y + "\n");
        }
        bw.flush();
    }

    static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static int dfs(int vertex, int parent, List<List<Integer>> adj) {
        order[vertex] = cnt++;
        int ret = order[vertex];

        // 자식 검사
        for (int curr : adj.get(vertex)) {
            // 내가 온 길은 제외
            if (curr == parent) {
                continue;
            }

            if (order[curr] == 0) { // 자식 정점이 방문되지 않은 경우
                // 자식 정점 중 방문 순서가 가장 빠른 값
                // 이때, 특정 자식 정점이 여러 개의 정점을 타고 올라가서 1번 정점까지 갈 수도 있다는 점에 유의
                int low = dfs(curr, vertex, adj);
                // 가장 작은 방문 순서가 vertex의 방문 순서보다 크거나 같을 경우
                // 해당 edge는 단절선
                if (low > order[vertex]) {
                    if (curr > vertex) {
                        ans.add(new Node(vertex, curr));
                    } else {
                        ans.add(new Node(curr, vertex));
                    }
                }
                ret = Math.min(ret, low);
            } else {
                ret = Math.min(ret, order[curr]);
            }
        }
        return ret;
    }
}
