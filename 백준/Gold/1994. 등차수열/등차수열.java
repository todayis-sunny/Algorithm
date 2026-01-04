// 01994. [G1] 등차수열.

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N;
    static int[] numbers; // 1-based;
    static int[][] dp;
    static int ans = 1;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        numbers = new int[N + 1];
        for (int n = 1; n <= N; n++) {
            numbers[n] = Integer.parseInt(br.readLine());
        }
        dp = new int[N + 1][N + 1];
    }

    static void solve() {
        if (N == 1) return;
        Arrays.sort(numbers);
        // a₁, a₂, a₃, ..., aₙ 일때,
        // n-1 항
        for (int i = 1; i < N; i++) {
            // n항
            for (int j = i + 1; j <= N; j++) {
                dp[i][j] = 2;
                // n-2항
                int prev = numbers[i] - (numbers[j] - numbers[i]); // n-2항 = n-1항 - d(공차)
                int left = 1, right = i - 1;
                while (left <= right) {
                    int mid = (left + right) / 2;
                    if (numbers[mid] <= prev) {
                        left = mid + 1;
                    } else {
                        right = mid - 1;
                    }
                }
                // n-2항이 존재하는 경우
                if (numbers[right] == prev) {
                    dp[i][j] = Math.max(dp[i][j], dp[right][i] + 1);
                }
                ans = Math.max(ans, dp[i][j]);
            }
        }
    }

    static void output() throws IOException {
        bw.write(String.valueOf(ans));
        bw.flush();
    }
}
