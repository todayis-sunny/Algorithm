// 03197. [P5] 백조의 호수.
import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int R, C;
    static int[] dx = new int[]{-1, 1, 0, 0};
    static int[] dy = new int[]{0, 0, -1, 1};
    static Queue<Node> swanQ, iceQ;
    static char[][] lake; // 호수, 녹다
    static boolean[][] check;
    static Node swan1, swan2;

    static int ans;
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        lake = new char[R][C];
        check = new boolean[R][C];
        swanQ = new LinkedList<>();
        iceQ = new LinkedList<>();
        for (int r = 0; r < R; r++) {
            String input = br.readLine();
            for (int c = 0; c < C; c++) {
                char ch = input.charAt(c);
                if (ch == 'L') {
                    if (swan1 == null) {
                        swan1 = new Node(r, c);
                    } else {
                        swan2 = new Node(r, c);
                    }
                    ch = '.';
                }
                if (ch == '.') {
                    iceQ.offer(new Node(r, c));
                }
                lake[r][c] = ch;

            }
        }
        swanQ.offer(swan1);
        check[swan1.x][swan1.y] = true;
        ans = 0;
        while (!move()) {
            melting();
            ans++;
        }
        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
        br.close();
    }
    /**
     * 백조 이동하기
     */
    static boolean move() {
        Queue<Node> queue = new LinkedList<>();
        while (!swanQ.isEmpty()) {
            Node swan = swanQ.poll();
            if (swan.x == swan2.x && swan.y == swan2.y) {
                return true;
            }
            for (int d = 0; d < 4; d++) {
                int nx = swan.x + dx[d];
                int ny = swan.y + dy[d];

                if (nx < 0 || nx >= R || ny < 0 || ny >= C || check[nx][ny]) {
                    continue;
                }
                check[nx][ny] = true;
                if(lake[nx][ny] == '.') {
                    swanQ.offer(new Node(nx, ny));
                } else if (lake[nx][ny] == 'X') {
                    queue.offer(new Node(nx, ny));
                }
            }
        }
        swanQ = queue;
        return false;
    }

    /**
     * 얼음 녹이기
     */
    static void melting() {
        int size = iceQ.size();
        for (int i = 0; i < size; i++) {
            Node ice = iceQ.poll();
            for (int d = 0; d < 4; d++) {
                int nx = ice.x + dx[d];
                int ny = ice.y + dy[d];

                if (nx < 0 || nx >= R || ny < 0 || ny >= C) {
                    continue;
                }
                if (lake[nx][ny] == 'X') {
                    lake[nx][ny] = '.';
                    iceQ.offer(new Node(nx, ny));
                }
            }
        }
    }



    static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}
