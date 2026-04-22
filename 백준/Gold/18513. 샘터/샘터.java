// 18513. [G4] 샘터.

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static final int MIN = -100_000_000, MAX = 100_000_000;
    static int N, K;
    static int[] lake;
    static int[] count;
    static long ans;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        lake = new int[N];
        count = new int[100_001];
        st = new StringTokenizer(br.readLine());
        for (int n = 0; n < N; n++) {
            int i = Integer.parseInt(st.nextToken());
            lake[n] = i;
        }
    }

    static void solve() {
        // 정렬
        Arrays.sort(lake);
        // 처음
        for (int i = lake[0] - K; i < lake[0]; i++) {
            count[lake[0] - i]++;
        }
        // 중간
        if (N > 1) {
            int prev = lake[0];
            for (int i = 1; i < N; i++) {
                int curr = lake[i];
                int diff = curr - prev - 1;
                diff = Math.min(200_000, diff);
                int k = 1;
                while (diff > 0) {
                    if (diff == 1) {
                        count[k] += 1;
                    } else {
                        count[k] += 2;
                    }
                    diff -= 2;
                    k++;
                }
                prev = curr;
            }
        }
        // 마지막
        for (int i = lake[N - 1] + K; i > lake[N - 1]; i--) {
            count[i - lake[N - 1]]++;
        }
        // 연산
        int k = K;
        for (int i = 1; i <= 100_000; i++) {
            int c = Math.min(count[i], k);
            ans += (long) i * c;
            k -= c;
            if (k == 0) return;
        }
    }

    static void output() throws IOException {
        bw.write(String.valueOf(ans));
        bw.flush();
    }
}
