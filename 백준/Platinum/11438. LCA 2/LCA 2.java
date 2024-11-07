// 11438. [P5] LCA2.
import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N, M;
    static int[][] pT; // i, j -? i의 2^j번째 조상
    static int[] depth; // 깊이
    static int[] parent; // 조상
    static ArrayList<ArrayList<Integer>> adj; // 인접 리스트
    static boolean[] visited; // 방문 처리

    public static void main(String[] args) throws IOException {
        input();
        bw.flush();
    }


    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        visited = new boolean[N + 1];
        depth = new int[N + 1];
        pT = new int[N + 1][18];
        adj = new ArrayList<>();
        // 인접 리스트 초기화 0번도 있어서 N+1
        for (int n = 0; n <= N; n++) {
            adj.add(new ArrayList<>());
        }
        // 입력 받기
        for (int n = 0; n < N-1; n++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            adj.get(x).add(y); // x -> y;
            adj.get(y).add(x); // y -> x;
        }
        bfs(1); // depth 구하기.
        for (int j = 1; (1 << j) < N; j++) {
            for (int i = 1; i <= N; i++) {
                if (pT[i][j - 1] == 0) {
                    continue;
                }
                pT[i][j] = pT[pT[i][j - 1]][j - 1];
            }
        }

        M = Integer.parseInt(br.readLine());
        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int ans = lca(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            bw.write(ans + "\n");
        }
    }

    /**
     * 밑이 e가 아닌 2 로그 함수
     * @param diff 진수
     * @return 리턴값
     */
    static double log2(int diff) {
        return Math.log(diff) / Math.log(2);
    }

    static void bfs(int x) {
        visited[x] = true;

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            for (int i = 0; i < adj.get(curr).size(); i++) {
                int next = adj.get(curr).get(i); // 부모 curr의 i번째 자식
                if (visited[next]) { // 방문 했으면 무시
                    continue;
                }
                visited[next] = true; // 방문 처리
                depth[next] = depth[curr] + 1; // 부모의 깊이 + 1
                pT[next][0] = curr; // 조상 저장.
                queue.offer(next); // 자식을 넣어줌
            }
        }
    }

    static int lca(int x, int y) {
        if (depth[x] < depth[y]) { // 깊이는 x가 깉도록 설정
            int z = x;
            x = y;
            y = z;
        }

        while (true) {
            int diff = depth[x] - depth[y];
            if (diff == 0) {
                break;
            }
            int j = (int) log2(diff);
            x = pT[x][j];
        }
        if (x == y) {
            return x;
        }
        // 조상을 함께 올라감
        for (int i = 17; i >= 0; i--) {
            if (pT[x][i] != pT[y][i]) { // 달라지면 그 조상을 탐색
                x = pT[x][i];
                y = pT[y][i];
            }
        }
        return pT[x][0];
    }

}
