// 04811. [G5] 알약.
import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N, cnt;
    static long[][] dp = new long[31][31];
    public static void main(String[] args) throws IOException {
        init();
        while (true) {
            input();
            if (N == 0) {
                bw.flush();
                return;
            }
            bw.write(dp[N][0] + "\n");
        }

    }

    static void init() {
        for (int i = 1; i < 31; i++) { // 반알짜리만 남아있으면 일렬로 나열
            dp[0][i] = 1;
        }

        for (int i = 1; i < 31; i++) {
            for (int j = 0; j < 30; j++) {
                if (j == 0) { // 완전한 알약 하나를 먹으면 반절짜리 알약이 하나 생성
                    dp[i][j] = dp[i-1][j+1];
                } else { // 반절짜리 약을 하나 먹은 상황 + 완전한 알약 하나를 먹은 상황
                    dp[i][j] = dp[i][j-1] + dp[i-1][j+1];
                }
            }
        }
    }


    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        cnt = 0;

    }

}
