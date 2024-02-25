// 14442. [G3] 벽 부수고 이동하기 2
import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N, M, K;
    static int[][] board, memo;
    static int[][][] visited;
    static Queue<Node> queue;
    static int[] dx = new int[]{-1, 1, 0, 0};
    static int[] dy = new int[]{0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        memo = new int[N][M];
        visited = new int[K+1][N][M];
        for (int n = 0; n < N; n++) {
            String input = br.readLine();
            for (int m = 0; m < M; m++) {
                board[n][m] = Character.getNumericValue(input.charAt(m));
            }
        }
        bfs(0, 0, 0);
        int answer = Integer.MAX_VALUE;
        if (N == 1 && M == 1) {
            answer = 1;
        } else {
            for (int k = 0; k <= K; k++) {
                if (visited[k][N-1][M-1] != 0) {
                    answer = Math.min(visited[k][N-1][M-1], answer);
                }
            }
            if (answer == Integer.MAX_VALUE) {
                answer = -1;
            }
        }
        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }

    public static void bfs(int k, int x, int y) {
        Node node = new Node(k, x, y);
        queue = new LinkedList<>();
        visited[node.k][node.x][node.y] = 1;
        queue.offer(node);
        while (!queue.isEmpty()) {
            Node nd = queue.poll();
            if (nd.x == N-1 && nd.y == M-1) {
                return;
            }
            for (int i = 0; i < 4; i++) {
                int nx = nd.x + dx[i];
                int ny = nd.y + dy[i];
                // 범위 밖은 무시.
                if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
                    continue;
                }
                // 벽을 만날 때, 벽 부시는 횟수를 이미 소진했다면 무시.
                if (board[nx][ny] == 1 && nd.k == K) {
                    continue;
                }
                // 이미 방문한 패턴이라면 무시.
                if (visited[nd.k][nx][ny] != 0) {
                    continue;
                }
                // 벽을 만난다면 k 증가.
                if (board[nx][ny] == 1) {
                    if(visited[nd.k+1][nx][ny] == 0) {
                        visited[nd.k+1][nx][ny] = visited[nd.k][nd.x][nd.y] + 1;
                        queue.offer(new Node(nd.k+1, nx, ny ));
                    }
                } else {
                    if(visited[nd.k][nx][ny] == 0) {
                        visited[nd.k][nx][ny] = visited[nd.k][nd.x][nd.y] + 1;
                        queue.offer(new Node(nd.k, nx, ny));
                    }
                }
            }
        }
    }

    public static class Node {
        public int k, x, y;

        Node(int k, int x, int y) {
            this.k = k;
            this.x = x;
            this.y = y;
        }
    }
}