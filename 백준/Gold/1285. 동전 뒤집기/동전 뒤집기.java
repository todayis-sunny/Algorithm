// 01285. [G1] 동전 뒤집기.

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N, ans;
    static int[] rows;

    public static void main(String[] args) throws IOException {
        input();
        for (int i = 0; i < (1 << N); i++) {
            int sum = 0;
            for (int j = 0; j < N; j++) {
                int b = Integer.bitCount(rows[j] ^ i);
                sum += Math.min(b, N - b);
            }
            ans = Math.min(ans, sum);
        }
        bw.write(String.valueOf(ans));
        bw.flush();
    }

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        rows = new int[N];
        ans = N * N;
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < N; j++) {
                if (input.charAt(j) == 'T') {
                    rows[i] += (1 << j);
                }
            }
        }
    }
}
