// 01079. [G2] 마피아.

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N;
    static int EUN_JIN;
    static int[] value;
    static int[][] delta;
    static boolean[] isDead;
    static int ans = 0;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        value = new int[N];
        delta = new int[N][N];
        isDead = new boolean[N];
        st = new StringTokenizer(br.readLine());
        for (int n = 0; n < N; n++) {
            value[n] = Integer.parseInt(st.nextToken());
        }
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                delta[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        EUN_JIN = Integer.parseInt(br.readLine());
    }

    static void solve() {
        dfs(N, 0);
    }

    static void output() throws IOException {
        bw.write(String.valueOf(ans));
        bw.flush();
    }

    static void dfs(int alive, int night) {
        if (alive == 1 || isDead[EUN_JIN]) {
            ans = Math.max(ans, night);
            return;
        }
        // 낮인 경우
        if (alive % 2 == 1) {
            int maxValue = -1;
            int idx = -1;
            for (int i = 0; i < N; i++) {
                if (isDead[i]) continue;
                if (value[i] > maxValue) {
                    idx = i;
                    maxValue = value[i];
                }
            }
            isDead[idx] = true;
            dfs(alive - 1, night);
            isDead[idx] = false;
        }
        // 밤인 경우
        else {
            for (int i = 0; i < N; i++) {
                if (isDead[i]) continue;
                isDead[i] = true;
                for (int j = 0; j < N; j++) {
                    value[j] += delta[i][j];
                }
                dfs(alive - 1, night + 1);
                isDead[i] = false;
                for (int j = 0; j < N; j++) {
                    value[j] -= delta[i][j];
                }
            }
        }
    }
}
