// 01421. [S1] 나무꾼 이다솜.

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    // N: 나무의 개수, C: 나무 자르는 비용, W: 나무 한 단위 가격
    static int N, C, W;
    static int[] tree; // 나무의 길이
    static long ans = 0;
    static int key = 0; // 기준 값
    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        // 문제 조건 입력
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        // 나무의 길이 입력
        tree = new int[N];
        for (int n = 0; n < N; n++) {
            tree[n] = Integer.parseInt(br.readLine());
            key = Math.max(key, tree[n]);
        }
    }

    static void solve() {
        while (key > 0) {
            long money = 0; // 버는 비용

            for (int n = 0; n < N; n++) {
                int cut = 0; // 자른 횟수
                int unit = 0; // 단위 개수
                double value = (double) tree[n] / key;
                if (tree[n] % key == 0) { // 나누어 떨이지는 경우
                    cut += (int) value - 1;
                } else {
                    cut += (int) value;
                }
                unit += tree[n] / key;
                int cost = (unit * key * W) - (cut * C);
                if (cost < 0) continue;
                money += cost;
            }

            ans = Math.max(ans, money);
            key--;
        }
    }

    static void output() throws IOException {
        bw.write(String.valueOf(ans));
        bw.flush();
    }
}
