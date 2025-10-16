// 02023. [G5] 신기한 소수.

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
    }

    static void solve() {
        dfs(N, 0);
    }

    static void output() throws IOException {
        bw.write(sb.toString());
        bw.flush();
    }

    static boolean isPrime(int number) {
        if (number < 2) return false;
        for (int i = 2; i * i <= number; i++) {
            if (number % i == 0) return false;
        }
        return true;
    }

    static void dfs(int length, int number) {
        if (length == 0) {
            sb.append(number).append("\n");
            return;
        }
        for (int i = 1; i < 10; i++ ){
            int target = 10 * number + i;
            if (length > 0 && isPrime(target)) {
                dfs(length - 1, target);
            }
        }
    }
}
