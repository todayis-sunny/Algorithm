// 01245. [G5] 농장 관리.
import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N, M, ans;
    static int[][] farms;
    static boolean[][] visited;
    static int[] dx = new int[] {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dy = new int[] {-1, 0, 1, -1, 1, -1, 0, 1};
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        ans = 0;
        farms = new int[N][M];
        visited = new boolean[N][M];
        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            for (int m = 0; m < M; m++) {
                int input = Integer.parseInt(st.nextToken());
                farms[n][m] = input;
            }
        }

        for (int x = 0; x < N; x++) {
            for (int y = 0; y < M; y++) {
                if (!visited[x][y]) {
                    ans += bfs(x, y);
                }
            }
        }
        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
        br.close();
    }


    static int bfs(int x, int y) {
        int cnt = 1;
        int high = farms[x][y];
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(x, y));
        visited[x][y] = true;

        while (!q.isEmpty()) {
            Node node = q.poll();
            for (int i = 0; i < 8; i++) {
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];
                if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
                    continue;
                }
                if(farms[nx][ny] == high && !visited[nx][ny]) {
                    q.offer(new Node(nx, ny));
                    visited[nx][ny] = true;
                } else if (farms[nx][ny] > high) {
                    cnt = 0;
                }
            }
        }
        return cnt;
    }

    static class Node{
        int x, y;
        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}