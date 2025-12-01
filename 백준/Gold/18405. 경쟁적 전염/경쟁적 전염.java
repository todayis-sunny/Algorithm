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
        testTube[find.x][find.y] = -1;



        // 제한시간까지 계산
        while (!queue.isEmpty()) {
            Node curr = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = curr.x + dx[i];
                int ny = curr.y + dy[i];
                int nt = curr.time + 1;
                // 범위 밖이면 스킵
                if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
                // 이미 방문했으면 스킵
                if (testTube[nx][ny] == -1) continue;
                // 이미 지난시간이라면 스킵
                if (nt >= S) continue;
                // 바이러스를 찾음
                if (testTube[nx][ny] != 0 && testTube[nx][ny] < ans) {
                    S = nt + 1;
                    ans = testTube[nx][ny];
                }

                // 위에 조건을 통과하면 감염
                queue.offer(new Node(nx, ny, nt)); // 새로운 탐색
                testTube[nx][ny] = -1;
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
