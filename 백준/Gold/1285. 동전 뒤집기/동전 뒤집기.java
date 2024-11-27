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
            ans = Math.min(ans, calculateValue(i));
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

    static void bitMasking(int row) {
        for (int i = 0; i < N; i++) {
            if ((row & (1 << i)) != 0) {
                rows[i] = (1 << N) - rows[i] - 1;
            }
        }
    }

    static int calculateValue(int row) {
        int value = 0;
        bitMasking(row);
        for (int i = 0; i < N; i++) {
            int cols = 0;
            for (int j = 0; j < N; j++) {
                if ((rows[j] & (1 << i)) != 0) {
                    cols++;
                }
            }
            value += Math.min(cols, N - cols);
        }
        bitMasking(row);
        return value;
    }
}
