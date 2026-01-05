// 17144. [G4] 미세먼지 안녕!.

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static final int[] dx = {-1, 1, 0, 0};
    static final int[] dy = {0, 0, -1, 1};
    static int R, C, T;
    static int[][] room;
    static AirPurifier airPurifier;
    static int ans = 2; // 공기청정기 (-1) 2개 값 빼기


    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        room = new int[R][C];
        for (int r = 0; r < R; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < C; c++) {
                room[r][c] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static void solve() {
        for (int r = 0; r < R; r++) {
            if (room[r][0] == -1) {
                airPurifier = new AirPurifier(new Node(r, 0), new Node(r + 1, 0));
                break;
            }
        }

        while (T-- > 0) {
            spread();
            airPurifier.purifyTopAndBottom();
        }
        calculateTotal();
    }

    static void output() throws IOException {
        bw.write(String.valueOf(ans));
        bw.flush();
    }

    static void calculateTotal() {
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                ans += room[r][c];
            }
        }
    }

    static void debugPrint() {
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                System.out.print(room[r][c] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }


    static void spread() {
        int[][] alpha = new int[R][C];
        // 확산량 계산하기
        for (int x = 0; x < R; x++) {
            for (int y = 0; y < C; y++) {
                // 공기 청정기 or 빈칸인 경우 스킵 or 미세먼지가 5미만이면 스킵
                if (room[x][y] < 5) continue;
                // 미세먼지인 경우 확인
                int cnt = 0;
                boolean[] target = new boolean[4];
                for (int i = 0; i < 4; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];
                    // 범위 밖이면 스킵
                    if (nx < 0 || nx >= R || ny < 0 || ny >= C) continue;
                    // 공기청정기인지 확인
                    if (room[nx][ny] == -1) continue;
                    // 해당 구역 체크
                    target[i] = true;
                    cnt++;
                }
                // 확산되는 구간에 뿌려주기
                int a = room[x][y] / 5;
                alpha[x][y] -= cnt * a;
                for (int i = 0; i < 4; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];
                    // 확산되지 않는 구간이면 스킵
                    if (!target[i]) continue;
                    alpha[nx][ny] += a;
                }
            }
        }
        // 확산 적용하기
        for (int x = 0; x < R; x++) {
            for (int y = 0; y < C; y++) {
                room[x][y] += alpha[x][y];
            }
        }
    }

    static class AirPurifier {
        // 공기청정기는 상단, 하단에 붙어 있는거 고려 X (엣지 케이스로 생각)
        Node top, bottom;

        public AirPurifier(Node top, Node bottom) {
            this.top = top;
            this.bottom = bottom;
        }

        void purifyTopAndBottom() {
            purify(new int[]{0, -1, 0, 1}, new int[]{1, 0, -1, 0}, new Node(top.x, top.y + 1));
            purify(new int[]{0, 1, 0, -1}, new int[]{1, 0, -1, 0}, new Node(bottom.x, bottom.y + 1));
        }


        private void purify(int[] px, int[] py, Node node) {
            Node initNode = new Node(node.x, node.y - 1);
            // 공기청정기 바람의 시작은 무조건 0
            int value = room[node.x][node.y];
            room[node.x][node.y] = 0;
            int idx = 0;
            while (node.x != initNode.x || node.y != initNode.y) {
                int nx = node.x + px[idx];
                int ny = node.y + py[idx];
                if (nx < 0 || nx >= R || ny < 0 || ny >= C) {
                    idx++;
                    continue;
                }
                int temp = value;
                value = room[nx][ny];
                room[nx][ny] = temp;
                node.x = nx;
                node.y = ny;
            }
            // 마지막 공기청정기 다시 초기화
            room[node.x][node.y] = -1;
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
