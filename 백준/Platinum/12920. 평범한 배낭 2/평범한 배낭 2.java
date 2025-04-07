// 12920. [P4] 평범한 배낭2.

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    // N: 민호의 집에 있는 물건의 종류의 수, M: 민호가 들 수 있는 가방의 최대 무게.
    static int N, M;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        input();
        output();
    }

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        dp = new int[M + 1];
        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken()); // 무게 입력
            int v = Integer.parseInt(st.nextToken()); // 가치 입력
            int c = Integer.parseInt(st.nextToken()); // 개수 입력
            int cnt = 1;
            while (c > 0) {
                // 1, 2, 4, 8, 16.. 2의 거듭제곱으로 빼면서 남은 개수를 더 이상 쪼갤 수 없으면 마무리.
                cnt = Math.min(cnt, c);
                int wc = w * cnt; // 무게와 개수의 곱 -> 하나의 물건의 무게로 인지
                int vc = v * cnt; // 가치와 개수의 곱 -> 하나의 물건의 가치로 인지
                // top-down 방식 -> 중복된 사용 금지
                for (int m = M; m >= wc; m--) {
                    dp[m] = Math.max(dp[m], dp[m - wc] + vc);
                }
                c -= cnt;
                cnt <<= 1;
            }
        }
    }

    static void output() throws IOException {
        bw.write(String.valueOf(dp[M]));
        bw.flush();
    }
}
