// 18405. [G5] 경쟁적 전염.

import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N, K, S; // N: 배열의 크기, K: 바이러스 종류, S: 제한시간
    static int[][] testTube; // 시험관
    static final int[] dx = {-1, 1, 0, 0};
    static final int[] dy = {0, 0, -1, 1};
    static boolean[][] visited;
    static Node find;
    static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        // 시험관 정보 입력
        testTube = new int[N][N];
        visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int v = Integer.parseInt(st.nextToken());
                testTube[i][j] = v;
            }
        }
        // 제한시간과 위치 입력
        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken()) + 1;
        // 찾는 위치는 1-based이므로 우리의 조건에 맞게 0-based
        find = new Node(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1);
    }

    static void solve() {
        bfs();
    }

    static void output() throws IOException {
        if (ans == Integer.MAX_VALUE) ans = 0;
        bw.write(String.valueOf(ans));
        bw.flush();
    }

    static void bfs() {
        Queue<Node> queue = new LinkedList<>();
        // 이미 감염되어 있으면 종료
        if (testTube[find.x][find.y] != 0) {
            ans = testTube[find.x][find.y];
            return;
        };
        queue.offer(new Node(find.x, find.y));
        visited[find.x][find.y] = true;


        // 제한시간까지 계산
        while (!queue.isEmpty()) {
            Node curr = queue.poll();
            // 제한시간이 초과되면 종료
            if (curr.time >= S) return;
            // 이미 찾은 경우 값 갱신
            if (testTube[curr.x][curr.y] < ans && testTube[curr.x][curr.y] != 0) {
                S = curr.time + 1;
                ans = testTube[curr.x][curr.y];
            }

            for (int i = 0; i < 4; i++) {
                int nx = curr.x + dx[i];
                int ny = curr.y + dy[i];
                // 범위 밖이면 스킵
                if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
                // 이미 방문했으면 스킵
                if (visited[nx][ny]) continue;
                // 위에 조건을 통과하면 감염
                queue.offer(new Node(nx, ny, curr.time + 1)); // 새로운 탐색
                visited[nx][ny] = true;
            }
        }
    }

    static class Node {
        int x, y;
        int time = 0;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Node(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }
}
