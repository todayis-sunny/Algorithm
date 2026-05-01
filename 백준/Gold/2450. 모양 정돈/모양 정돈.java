// 02450. [G2] 모양 정돈.

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N;
    static int[] original;
    static int[] count = new int[4]; // 1-based;
    static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        original = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int n = 0; n < N; n++) {
            original[n] = Integer.parseInt(st.nextToken());
            count[original[n]]++;
        }
    }

    static void solve() {
        for (int x = 1; x <= 3; x++) {
            for (int y = 1; y <= 3; y++) {
                if (y == x) continue;
                for (int z = 1; z <= 3; z++) {
                    if (z == x || z == y) continue;
                    ans = Math.min(ans, getCombination(new int[]{x, y, z}));
                }
            }
        }
    }

    static void output() throws IOException {
        bw.write(String.valueOf(ans));
        bw.flush();
    }

    static int getCombination(int[] seq) {
        int cnt = 0;
        int[] shape = new int[4];
        int[][] job = new int[4][4];
        for (int i = 1; i <= 3; i++) {
            shape[i] = count[i];
        }
        int j = 0;
        for (int i = 0; i < N; i++) {
            while (shape[seq[j]] == 0) {
                j++;
            }
            job[original[i]][seq[j]]++;
            shape[seq[j]]--;
        }
        for (int x = 1; x <= 2; x++) {
            for (int y = x + 1; y <= 3; y++) {
                int tmp = Math.min(job[x][y], job[y][x]);
                cnt += tmp;
                job[x][y] -= tmp;
                job[y][x] -= tmp;
            }
        }
        int tmp = 0;

        for (int x = 1; x <= 3; x++) {
            for (int y = 1; y <= 3; y++) {
                if (y == x) continue;
                tmp += job[x][y];
            }
        }
        cnt += (tmp / 3) * 2;
        return cnt;
    }
}
