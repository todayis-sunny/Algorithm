// 22944. [G3] 죽음의 비.

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static final int[] dx = {-1, 1, 0, 0};
    static final int[] dy = {0, 0, -1, 1};
    static final char CH_START = 'S', CH_END = 'E', CH_UMBRELLAS = 'U', CH_EMPTY = '.';
    static int N, H, D; // N: 한변의 길이, H: 현재 체력, D: 우산의 내구도
    static char[][] grid;
    static int[][] dist; // [i][j] = d : i지점에서 j지점까지의 거리 d
    static Node[] umbrellas;
    static int ans = Integer.MAX_VALUE;
    static int k = 2;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        grid = new char[N][N];
        dist = new int[12][12];
        umbrellas = new Node[12];
        for (int r = 0; r < N; r++) {
            String input = br.readLine();
            for (int c = 0; c < N; c++) {
                grid[r][c] = input.charAt(c);
                if (grid[r][c] == CH_START) {
                    umbrellas[0] = new Node(r, c);
                } else if (grid[r][c] == CH_END) {
                    umbrellas[1] = new Node(r, c);
                } else if (grid[r][c] == CH_UMBRELLAS) {
                    umbrellas[k++] = new Node(r, c);
                }
            }
        }
    }

    static void solve() {
        for (int id = 0; id < k; id++) {
            bfs(id);
        }
        dfs(0, 1, 0, H, false);
    }

    static void output() throws IOException {
        if (ans == Integer.MAX_VALUE) {
            bw.write("-1");
        } else {
            bw.write(String.valueOf(ans));
        }
        bw.flush();
    }

    static void bfs(int id) {
        int[][] visited = new int[N][N];
        Queue<Node> queue = new LinkedList<>();
        queue.offer(umbrellas[id]);
        visited[umbrellas[id].x][umbrellas[id].y] = 1;
        while (!queue.isEmpty()) {
            Node curr = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = curr.x + dx[i];
                int ny = curr.y + dy[i];
                // 범위 밖인 경우 스킵
                if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
                    continue;
                }
                // 이미 방문한 경우 스킵
                if (visited[nx][ny] != 0) {
                    continue;
                }
                visited[nx][ny] = visited[curr.x][curr.y] + 1;
                queue.offer(new Node(nx, ny));
            }
        }
        for (int j = 0; j < k; j++) {
            dist[id][j] = visited[umbrellas[j].x][umbrellas[j].y] - 1;
        }
    }

    static void dfs(int prev, int bit, int count, int hp, boolean umb) {
        // E 포인트에 도달한 경우
        if (prev == 1) {
            ans = Math.min(ans, count);
            return;
        }
        // ans보다 초과되는 경우
        if (count > ans) {
            return;
        }
        // 우산 또는 목표지까지 가기
        for (int next = 1; next < k; next++) {
            // 갈수 없는 경우
            // 이미 방문 한 경우
            if ((bit & (1 << next)) != 0) continue;
            // hp가 안되는 경우
            int plus = umb ? D : 0;
            if (hp + plus < dist[prev][next]) continue;
            int nextHp = hp;
            int nextCount = count + dist[prev][next];
            // 우산의 내구도가 다 떨어지고 체력이 닳는 경우
            if (plus < dist[prev][next]) {
                nextHp -= dist[prev][next] - plus;
            }
            // 갈수 있는 경우
            dfs(next, bit | (1 << next), nextCount, nextHp, true);
        }
    }


    static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
