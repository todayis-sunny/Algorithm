// 06209. [G2] 제자리 멀리뛰기.

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int escape, N, M;
    static int[] stones;
    static int ans = 0;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        escape = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        stones = new int[N + 1];
        for (int n = 0; n < N; n++) {
            stones[n] = Integer.parseInt(br.readLine());
        }
        stones[N] = escape;
    }

    static void solve() {
        Arrays.sort(stones);
        int left = 1, right = 1_000_000_000;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (parametricSearch(mid)) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        ans = right;
    }

    static void output() throws IOException {
        bw.write(String.valueOf(ans));
        bw.flush();
    }

    static boolean parametricSearch(int value) {
        int prev = 0;
        int remaining = M;
        for (int curr : stones) {
            int dist = curr - prev;
            if (dist < value) {
                remaining--;
            } else {
                prev = curr;
            }
            if (remaining < 0) {
                return false;
            }
        }
        return true;
    }
}
