// 17836. [G5] 공주님을 구해라!.
import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static final int INF = Integer.MAX_VALUE;

    static int N, M, T;
    static int[][] board;
    static int[] dx = new int[]{-1, 1, 0, 0};
    static int[] dy = new int[]{0, 0, -1, 1};
    static Node gram;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            for (int m = 0; m < M; m++) {
                int tmp = Integer.parseInt(st.nextToken());
                if (tmp == 2) {
                    gram = new Node(n, m);
                    tmp = 0;
                }
                board[n][m] = -tmp;
            }
        }
        bfs(0, 0);


        int noGram = board[N-1][M-1] == 0? INF : board[N-1][M-1] - 1;
        int tmp = board[gram.x][gram.y] - 1 + (N -1 - gram.x) + (M - 1 - gram.y);
        int yesGram = board[gram.x][gram.y] == 0? INF: tmp;
        int time = Math.min(noGram, yesGram);
        if(time <= T) {
            bw.write(String.valueOf(time));
        } else {
            bw.write("Fail");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    static void bfs(int x, int y) {
        Queue<Node> q = new LinkedList<>();
        board[x][y] = 1;
        q.offer(new Node(0, 0));
        while (!q.isEmpty()) {
            Node node = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];
                if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
                    continue;
                }
                if (board[nx][ny] != 0) {
                    continue;
                }
                if (board[node.x][node.y] >= T + 1) {
                    continue;
                }
                board[nx][ny] = board[node.x][node.y] + 1;
                q.offer(new Node(nx, ny));
            }
        }
    }

    static class Node{
        int x, y;
        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
