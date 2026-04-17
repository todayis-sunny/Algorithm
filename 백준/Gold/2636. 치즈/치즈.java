// 02636. [G4] 치즈.

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int x, y, cheese;
    static int res, cnt;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        bw.write(cnt + "\n");
        bw.write(String.valueOf(res));
        bw.flush();
    }

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        map = new int[x][y];
        for (int i = 0; i < x; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < y; j++) {
                int num = Integer.parseInt(st.nextToken());
                if (num == 1) {
                    cheese++;
                }
                map[i][j] = num;
            }
        }

    }

    static void solve() {
        res = 0;
        cnt = 0;
        while (cheese > 0) {
            if (cheese > 0) {
                res = cheese;
            }
            cnt += melt();
            if (cheese == 0) {
                break;
            }
        }
    }

    static int melt() {
        Queue<Node> q = new LinkedList<>();
        boolean[][] check = new boolean[x][y];

        q.offer(new Node(0, 0));
        check[0][0] = true;

        while (!q.isEmpty()) {
            Node node = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];

                if (nx < 0 || nx >= x || ny < 0 || ny >= y || check[nx][ny]) {
                    continue;
                }
                if (map[nx][ny] != -1) {
                    check[nx][ny] = true;

                    if (map[nx][ny] == 1) {
                        map[nx][ny] -= 1;
                        cheese--;
                        continue;
                    }
                    q.offer(new Node(nx, ny));
                }
            }
        }

        return 1;
    }

    static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
