// 01600. [G3] 말이 되고픈 원숭이.
import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int K, W, H, ans;
    static int[][][] visited;
    static boolean[][] board;

    static int[] dx = new int[] {-1, 1, 0, 0, -2, -2, -1, -1, 1, 1, 2, 2};
    static int[] dy = new int[] {0, 0, -1, 1, -1, 1, -2, 2, -2, 2, -1, 1};

    public static void main(String[] args) throws IOException {
        K = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        visited = new int[K+1][H][W];
        board = new boolean[H][W];
        for (int h = 0; h < H; h++) {
            st = new StringTokenizer(br.readLine());
            for (int w = 0; w < W; w++) {
                boolean bool = "0".equals(st.nextToken());
                board[h][w] = bool;
            }
        }
        bfs();
        ans = Integer.MAX_VALUE;
        for (int k = 0; k <= K; k++) {
           if(visited[k][H-1][W-1] > 0) {
               ans = Math.min(ans, visited[k][H-1][W-1]);
           }
        }
        ans = ans == Integer.MAX_VALUE? -1 : ans -1;
        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
        br.close();
    }

    public static void bfs() {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(0, 0, 0));
        visited[0][0][0] = 1;
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            for (int i = 0; i < 12; i++) {
                if(node.k >= K && i >= 4) { // 능력 초과
                    break;
                }
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];
                int nk = i >= 4 ? node.k + 1 : node.k;
                if(nx < 0 || nx >= H || ny < 0 || ny >= W) { // 범위 밖인 경우,
                    continue;
                }
                if(!board[nx][ny]) { // 벽인 경우,
                    continue;
                }
                if(visited[nk][nx][ny] != 0 && visited[nk][nx][ny] <= visited[node.k][node.x][node.y] + 1) { // 이미 더 좋은 성능으로 접근한 경우가 있는 경우,
                    continue;
                }
                visited[nk][nx][ny] = visited[node.k][node.x][node.y] + 1;
                queue.offer(new Node(nk, nx, ny));
            }
        }
    }

    public static class Node{
        int k;
        int x;
        int y;
        Node(int k, int x, int y){
            this.k = k;
            this.x = x;
            this.y = y;
        }
    }
}