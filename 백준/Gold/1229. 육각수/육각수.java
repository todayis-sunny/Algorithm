// 01229. [G4] 육각수.

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static List<Integer> uniqueList = new ArrayList<>();
    static int[] dp;
    static int N;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        dp = new int[N + 1];
    }

    static void solve() {
        // 육각수 1로 초기화
        for (int unique = 1, w = 5; unique <= N; unique += w, w += 4) {
            dp[unique] = 1;
            uniqueList.add(unique);
        }
        // N까지 탐색
        // n은 수
        for (int n = 1; n <= N; n++) {
            if (dp[n] == 1) {

                continue;
            }
            dp[n] = Integer.MAX_VALUE;
            for (int unique : uniqueList) {
                if (n < unique) break;
                dp[n] = Math.min(dp[n], dp[n - unique] + 1);
            }

        }
    }

    static void output() throws IOException {
        bw.write(String.valueOf(dp[N]));
        bw.flush();
    }
}
