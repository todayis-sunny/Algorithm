// 12887. [G5] 경로 게임.

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N;
    static int[] dx = {-1, 1, 0};
    static int[] dy = {0, 0, 1};
    static final char WHITE = '.';
    static final char BLACK = '#';
    static char[][] maze;
    static int ans = 0;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        maze = new char[2][N];
        for (int i = 0; i < 2; i++) {
            String input = br.readLine();
            for (int j = 0; j < N; j++) {
                maze[i][j] = input.charAt(j);
                if (maze[i][j] == WHITE) {
                    ans++;
                }
            }
        }
    }

    static void solve() {
        ans -= Math.min(bfs(0), bfs(1));
    }

    static void output() throws IOException {
        bw.write(ans + "");
        bw.flush();
    }

    static int bfs(int row) {
        Queue<Node> q = new LinkedList<>();
        boolean[][] visited = new boolean[2][N];
        // 출발지 큐에 삽입
        if (maze[row][0] == BLACK) {
            return Integer.MAX_VALUE;
        }
        q.add(new Node(row, 0, 1));
        visited[row][0] = true;

        // 시뮬레이션
        while (!q.isEmpty()) {
            Node node = q.poll();
            int x = node.x;
            int y = node.y;
            int d = node.d;

            if (y == N - 1) {
                return d;
            }

            for (int i = 0; i < 3; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                int nd = d + 1;

                if (nx < 0 || nx >= 2 || ny < 0 || ny >= N) {
                    continue;
                }
                if (visited[nx][ny]) {
                    continue;
                }
                if (maze[nx][ny] == BLACK) {
                    continue;
                }
                q.add(new Node(nx, ny, nd));
                visited[nx][ny] = true;
            }
        }
        return -1;
    }

    static class Node {
        int x, y, d;

        Node(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }
}
