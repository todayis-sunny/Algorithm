// 28083. [P5] 마왕의 성.
import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N, M;
    static int[][] height, rank; // 땅의 높이, 얻을 수 있는 세금, 랭크(유니온)
    static long[][] tax, ans;
    static Node[][] parent;
    static boolean[][] visited;
    static Map<Integer, List<Node>> treeMap;
    static int[] dx = new int[]{-1, 1, 0, 0};
    static int[] dy = new int[]{0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        // 초기화
        treeMap = new TreeMap<>();
        height = new int[N][M];
        tax = new long[N][M];
        ans = new long[N][M];
        rank = new int[N][M];
        parent = new Node[N][M];
        visited = new boolean[N][M];
        // 높이 초기화, treeMap 채우기
        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            for (int m = 0; m < M; m++) {
                height[n][m] = Integer.parseInt(st.nextToken());
                treeMap.putIfAbsent(height[n][m], new ArrayList<>());
                treeMap.get(height[n][m]).add(new Node(n, m));
            }
        }
        // 세금 초기화
        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            for (int m = 0; m < M; m++) {
                tax[n][m] = Integer.parseInt(st.nextToken());
            }
        }
        // parent 초기화
        for (int n = 0; n < N; n++) {
            for (int m = 0; m < M; m++) {
                parent[n][m] = new Node(n, m);
            }
        }
    }

    static void solve() throws IOException {
        // 높이 오름차순 탐색
        for (Map.Entry<Integer, List<Node>> entry : treeMap.entrySet()) {
            int currH = entry.getKey();
            List<Node> currList = entry.getValue();
            // 현재 높이의 모든 좌표에 대해 탐색
            for (Node node : currList) {
                int x = node.x;
                int y = node.y;
                visited[x][y] = true;

                for (int i = 0; i < 4; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];
                    if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
                        continue;
                    }
                    if (height[nx][ny] > currH) {
                        continue;
                    }
                    unionParent(node, new Node(nx, ny));
                }
            }
            // union이 된 결과를 기반으로 ans배열 초기화
            for (Node node : currList) {
                Node parent = findParent(node);
                ans[node.x][node.y] = tax[parent.x][parent.y];
            }
        }
        // 출력
        for (int n = 0; n < N; n++) {
            for (int m = 0; m < M; m++) {
                bw.write(ans[n][m] + " ");
            }
            bw.newLine();
        }
        bw.flush();
    }

    static Node findParent(Node node) {
        // 루트 노드가 아니라면, 루트 노드를 찾을 때까지 재귀적으로 호출.
        if (node == parent[node.x][node.y]) {
            return parent[node.x][node.y];
        }
        return parent[node.x][node.y] = findParent(parent[node.x][node.y]);
    }

    static void unionParent(Node node1, Node node2) {
        node1 = findParent(node1);
        node2 = findParent(node2);
        if (node1 == node2) {
            return;
        }
        if (rank[node1.x][node1.y] < rank[node2.x][node2.y]) {
            swap(node1, node2);
        }
        parent[node2.x][node2.y] = node1;

        if (rank[node1.x][node1.y] == rank[node2.x][node2.y]) {
            rank[node1.x][node1.y]++;
        }
        tax[node1.x][node1.y] += tax[node2.x][node2.y];
    }

    static void swap(Node node1, Node node2) {
        Node temp = node1;
        node1 = node2;
        node2 = temp;
    }

    static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
