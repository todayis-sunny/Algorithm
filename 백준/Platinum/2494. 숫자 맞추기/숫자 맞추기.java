// 02494. [P5] 숫자 맞추기.

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int N;
    static String origin, wanted;
    static int[][] dp; // [i][r] : i번째 숫자까지 맞추기 시작, r번(왼쪽, 숫자가 증가하는) 회전했을때, 최소값
    static int[][] trace; // [i][r]


    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        dp = new int[N + 1][10];
        trace = new int[N + 1][10];
        origin = br.readLine();
        wanted = br.readLine();
    }

    static void solve() {
        // 숫자나사0 부터 숫자나사N-1 까지 검토
        for (int i = N - 1; i >= 0; i--) {
            // 누적된 회전수
            for (int r = 0; r < 10; r++) {
                int o = Character.getNumericValue(origin.charAt(i));
                int w = Character.getNumericValue(wanted.charAt(i));
                // 왼쪽, 증가방향으로의 회전수
                // w - (o + r) : 목표 숫자 - 현재 숫자 상태
                int diff = (20 + w - (o + r)) % 10;
                // 왼쪽, 증가방향으로 회전
                int dL = dp[i + 1][(diff + r) % 10] + diff;
                // 오른쪽, 감소방향으로 회전
                int dR = dp[i + 1][r] + (10 - diff);
                if (dL < dR) {
                    dp[i][r] = dL;
                    trace[i][r] = diff;
                } else {
                    dp[i][r] = dR;
                    trace[i][r] = diff - 10;
                }
            }
        }
    }

    static void output() throws IOException {
        bw.write(dp[0][0] + "\n");
        int r = 0;
        for (int i = 0; i < N; i++) {
            int t = trace[i][r]; // i번째 나사, 누적 회전 r일 때 했던 '최적의 선택'을 가져옴
            sb.append(i + 1).append(" ").append(t).append("\n");
            if (t > 0) { // 왼쪽, 증가하는 회전일때 아래 나사에도 영향이 감
                r = (r + t) % 10;
            }
        }
        bw.write(sb.toString());
        bw.flush();
    }
}
