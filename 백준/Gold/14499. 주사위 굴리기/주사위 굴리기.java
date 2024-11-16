// 14499. [G4] 주사위 굴리기.
import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N, M, x, y, K;
    static int[][] map;
    static int[] dice = new int[7];
    static int[] dx = {0, 0, 0, -1, 1};
    static int[] dy = {0, 1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            for (int m = 0; m < M; m++) {
                map[n][m] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
    }

    static void solve() throws IOException {
        for (int k = 0; k < K; k++) {
            int d = Integer.parseInt(st.nextToken());
            move(d);
        }
        bw.flush();
    }

    static void move(int d) throws IOException {
        int nx = x + dx[d];
        int ny = y + dy[d];
        if (outOfBound(nx, ny)) {
            return;
        }
        roll(d, nx, ny);
        x = nx;
        y = ny;
    }

    static void roll(int d, int x, int y) throws IOException {
        int tmp = dice[3];
        switch (d) {
            case 1:
                dice[3] = dice[4];
                dice[4] = dice[6];
                dice[6] = dice[2];
                dice[2] = tmp;
                break;
            case 2:
                dice[3] = dice[2];
                dice[2] = dice[6];
                dice[6] = dice[4];
                dice[4] = tmp;
                break;
            case 3:
                dice[3] = dice[5];
                dice[5] = dice[6];
                dice[6] = dice[1];
                dice[1] = tmp;
                break;
            case 4:
                dice[3] = dice[1];
                dice[1] = dice[6];
                dice[6] = dice[5];
                dice[5] = tmp;
                break;
        }
        if (map[x][y] == 0) {
            map[x][y] = dice[6];
        } else {
            dice[6] = map[x][y];
            map[x][y] = 0;
        }
        bw.write(dice[3] + "\n");
    }

    static boolean outOfBound(int x, int y) {
        return x < 0 || x >= N || y < 0 || y >= M;
    }
}
