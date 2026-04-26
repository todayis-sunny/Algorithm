// 02629. [G3] 양팔저울.

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int N, M, max;
    static int[] weight, bead;
    static boolean[] possible = new boolean[40_001]; // 가능 여부

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        // 무게 추 입력
        N = Integer.parseInt(br.readLine());
        weight = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int n = 0; n < N; n++) {
            max += weight[n] = Integer.parseInt(st.nextToken());
        }
        // 구슬 무게 입력
        M = Integer.parseInt(br.readLine());
        bead = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int m = 0; m < M; m++) {
            bead[m] = Integer.parseInt(st.nextToken());
        }
    }

    static void solve() {
        possible[0] = true;
        for (int n = 0; n < N; n++) {
            // 추를 더함
            for (int w = max; w >= 0; w--) {
                if (possible[w]) {
                    possible[w + weight[n]] = true;
                }
            }
            // 추를 뺌
            for (int w = 0; w <= max; w++) {
                if (possible[w]) {
                    possible[Math.abs(w - weight[n])] =  true;
                }
            }
        }
        for (int b : bead) {
            if (possible[b]) sb.append("Y");
            else sb.append("N");
            sb.append(" ");
        }
    }

    static void output() throws IOException {
        bw.write(sb.toString());
        bw.flush();
    }
}
