// 01261. [G4] 알고스팟.
import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N, M;
    static int[][] visited;
    static char[][] board;
    static Queue<Node> queue;
    static int[] dx = new int[] {-1, 1, 0, 0};
    static int[] dy = new int[] {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        visited = new int[N][M];
        board = new char[N][M];
        for (int[] v : visited) {
            Arrays.fill(v, -1);
        }
        for (int n = 0; n < N; n++) {
            String input = br.readLine();
            for (int m = 0; m < M; m++) {
                board[n][m] = input.charAt(m);
            }
        }
        bfs();
        bw.write(String.valueOf(visited[N-1][M-1]));
        bw.flush();
        bw.close();
        br.close();
    }

    static void bfs() {
        queue = new LinkedList<>();
        queue.add(new Node(0, 0));
        visited[0][0] = 0;
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];
                if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
                    continue;
                }
                int nw = board[nx][ny] == '1' ? 1 : 0;
                if (visited[nx][ny] != -1 && visited[nx][ny] <= visited[node.x][node.y] + nw) {
                    continue;
                }
                visited[nx][ny] = visited[node.x][node.y] + nw;
                queue.offer(new Node(nx, ny));
            }
        }
    }

    static class Node{
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}
