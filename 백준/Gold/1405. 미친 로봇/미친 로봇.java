// 01405. [G4] 미친 로봇.

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    // 동, 서, 남, 북
    static final int[] dx = {0, 0, 1, -1}, dy = {1, -1, 0, 0};
    static int N;
    static double[] probability = new double[4];
    static boolean[][] visited;
    static double result = 0;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        visited = new boolean[N * 2 + 1][N * 2 + 1]; // (N, N)에서 시작
        for (int i = 0; i < 4; i++) {
            probability[i] = Double.parseDouble(st.nextToken()) / 100;
        }
    }

    static void solve() {
        visited[N][N] = true;
        dfs(0, N, N, 1.0);
    }

    static void output() throws IOException {
        bw.write(String.valueOf(result));
        bw.flush();
    }

    static void dfs(int depth, int x, int y, double value) {
        if (depth == N) {
            result += value;
            return;
        }
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            // 범위를 벗어나는 건 불가능 N에 의해 최대치가 지정되고 시작지점이 정해짐
            // 방문처리되어 있으면 스킵
            if (visited[nx][ny]) continue;
            // 방문처리후 재귀
            visited[nx][ny] = true;
            dfs(depth + 1, nx, ny, value * probability[i]);
            visited[nx][ny] = false;
        }

    }
}
