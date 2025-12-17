// 01415. [G1] 사탕.

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static final int LIMIT = 500_000;
    static int N;
    static long[] dp = new long[LIMIT + 1];
    static boolean[] isPrime = new boolean[LIMIT + 1];
    static HashMap<Integer, Integer> candyMap = new HashMap<>();
    static int zero = 1;
    static long ans = 0;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        for (int n = 0; n < N; n++) {
            int candy = Integer.parseInt(br.readLine());
            if (candy == 0) {
                zero++;
                continue;
            }
            candyMap.put(candy, candyMap.getOrDefault(candy, 0) + 1);
        }
    }

    static void solve() {
        eratosthenes();
        dp[0] = 1;
        candyMap.forEach((price, count) -> {
            for (int i = LIMIT; i >= 0; i--) {
                for (int j = 1; j <= count; j++) {
                    // 새로운 가격 (기존 i원 + 신규 (price)원 * (cnt)개)
                    int value = i + price * j;
                    // 가격이 초과하면 탈출
                    if (value > LIMIT) break;
                    // 연산
                    dp[value] += dp[i];
                }
            }
        });
        for (int i = LIMIT; i >= 1; i--) {
            if (!isPrime[i]) continue;
            ans += dp[i];
        }
    }

    static void output() throws IOException {
        bw.write(String.valueOf(ans * zero));
        bw.flush();
    }

    static void eratosthenes() {
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;
        for (int i = 2; i * i <= LIMIT; i++) {
            if (!isPrime[i]) continue;
            for (int j = i * i; j <= LIMIT; j += i) {
                isPrime[j] = false;
            }
        }
    }
}
