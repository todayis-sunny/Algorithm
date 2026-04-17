// 15824. [G2] 너 봄에는 캡사이신이 맛있단다.

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static final int MOD = 1_000_000_007;
    static int N;
    static long ans;
    static long[] arr;
    static long[] pows;

    public static void main(String[] args) throws IOException {
        input();
        Arrays.sort(arr);
        for (int i = 1; i <= N; i++) {
            ans += (pows[i - 1] - pows[N - i]) * arr[i];
            ans %= MOD;
        }
        bw.write(String.valueOf(ans));
        bw.flush();
    }

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        arr = new long[N + 1];
        pows = new long[N + 1];
        pows[0] = 1;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Long.parseLong(st.nextToken());
            pows[i] = (pows[i - 1] * 2) % MOD;
        }
    }
}