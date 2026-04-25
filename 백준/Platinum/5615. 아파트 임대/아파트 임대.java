// 05615. [P1] 아파트 임대.

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigInteger;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N; // 입력 개수
    static long[] kValues; // 입력받은 k 값들을 저장할 배열
    static int ans = 0;    // 최종 정답

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        kValues = new long[N];
        for (int i = 0; i < N; i++) {
            kValues[i] = Long.parseLong(br.readLine());
        }
    }

    static void solve() {
        for (long value : kValues) {
            long k = value * 2 + 1;
            if (new BigInteger(Long.toString(k)).isProbablePrime(10)) {
                ans++;
            }
        }
    }

    static void output() throws IOException {
        bw.write(String.valueOf(ans));
        bw.flush();
    }
}
