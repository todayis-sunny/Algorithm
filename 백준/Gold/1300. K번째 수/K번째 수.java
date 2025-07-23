// 01300. [G1] K번째 수
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static long N, k, ans;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        N = Long.parseLong(br.readLine());
        k = Long.parseLong(br.readLine());
    }

    static void solve() {
        long left = 1;
        long right = k;
        // lower-bound
        while (left <= right) {
            long mid = (left + right) / 2;
            long cnt = 0;
            for (long i = 1; i <= N; i++) {
                long tmp = Math.min(mid / i, N);
                if (tmp == 0) {
                    break;
                }
                cnt += tmp;
            }
            if (cnt >= k) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        ans = left;
    }

    static void output() throws IOException {
        bw.write(String.valueOf(ans));
        bw.flush();
    }
}
