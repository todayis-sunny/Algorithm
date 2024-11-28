// 11438. [P5] LCA2.

import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuffer sb = new StringBuffer();
    static StringTokenizer st;
    static int limit;
    static int N, M;
    static int[][] parent; // j, i -> j의 2^i번째 조상
    static int[] depth; // 깊이
    static List<List<Integer>> adj = new ArrayList<>(); // 인접 리스트
    static boolean[] visited; // 방문 처리

    public static void main(String[] args) throws IOException {
        input();
        System.out.println(sb.toString());
    }


    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        visited = new boolean[N + 1];
        depth = new int[N + 1];
        limit = (int) log2(N);
        parent = new int[N + 1][limit + 1];
        // 인접 리스트 초기화 0번도 있어서 N+1
        for (int n = 0; n <= N; n++) {
            adj.add(new ArrayList<>());
        }
        // 입력 받기
        for (int n = 0; n < N - 1; n++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            adj.get(x).add(y); // x -> y;
            adj.get(y).add(x); // y -> x;
        }
        dfs(1, 0); // depth 구하기.
        fillParent(); // parent 초기화.
        M = Integer.parseInt(br.readLine());
        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int ans = lca(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            sb.append(ans).append("\n");
        }
    }

    /**
     * 밑이 e가 아닌 2 로그 함수
     *
     * @param diff 진수
     * @return 리턴값
     */
    static double log2(int diff) {
        return Math.log(diff) / Math.log(2);
    }

    static void dfs(int node, int d) {
        depth[node] = d;
        visited[node] = true;
        List<Integer> children = adj.get(node);
        for (Integer child : children) {
            if (!visited[child]) {
                parent[child][0] = node;
                dfs(child, d + 1);
            }
        }
    }

    static void fillParent() {
        for (int i = 1; i <= limit; i++) {
            for (int j = 1; j <= N; j++) {
                parent[j][i] = parent[parent[j][i - 1]][i - 1];
            }
        }
    }

    static int lca(int x, int y) {
        if (depth[x] < depth[y]) { // 깊이는 x가 깉도록 설정
            int z = x;
            x = y;
            y = z;
        }
        int diff = depth[x] - depth[y];
        for (int i = 0; diff > 0; i++, diff = diff >> 1) {
            if ((diff & 1) == 1) {
                x = parent[x][i];
            }
        }
        if (x == y) {
            return x;
        }
        // 조상을 함께 올라감
        for (int i = limit; i >= 0; i--) {
            if (parent[x][i] != parent[y][i]) { // 달라지면 그 조상을 탐색
                x = parent[x][i];
                y = parent[y][i];
            }
        }
        return parent[x][0];
    }

}
