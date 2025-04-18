// 12920. [P4] 평범한 배낭2.

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    static int N, M;
    static int[] dp;
    static List<Item> items = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        dp = new int[M + 1];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken()); // 물건의 무게
            int v = Integer.parseInt(st.nextToken()); // 물건의 가치
            int c = Integer.parseInt(st.nextToken()); // 물건의 개수
            int cnt = 1;
            while (c > 0) {
                // 1, 2, 4, 8, 16.. 2의 거듭제곱으로 빼면서 남은 개수를 더 이상 쪼갤 수 없으면 마무리.
                int use = Math.min(cnt, c); // 사용할 개수
                items.add(new Item(w * use, v * use));
                c -= use;
                cnt <<= 1;
            }
        }
    }

    static void solve() {
        for (Item it : items) {
            for (int m = M; m >= it.weight; m--) {
                dp[m] = Math.max(dp[m], dp[m - it.weight] + it.value);
            }
        }
    }

    static void output() throws IOException {
        bw.write(String.valueOf(dp[M]));
        bw.flush();
        bw.close();
    }

    static class Item {
        int weight;
        int value;

        public Item(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }
    }
}
