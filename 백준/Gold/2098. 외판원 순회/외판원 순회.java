// 02098. 외판원 순회.
import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static final int INF = 20_000_000;
    static int N;
    static int[][] values, memory;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        memory = new int [N][1 << N];
        values = new int[N][N];
        for (int x = 0; x < N; x++) {
            st = new StringTokenizer(br.readLine());
            for (int y = 0; y < N; y++) {
                values[x][y] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0; i < N; i++) {
            Arrays.fill(memory[i], INF);
        }

        bw.write(String.valueOf(tsp(0, 1)));
        bw.flush();
        bw.close();
        br.close();
    }

    static int tsp(int prev, int bits) {
        if (bits == (1 << N) - 1) {
            if (values[prev][0] == 0) {
                return INF;
            }
            return values[prev][0];
        }
        if (memory[prev][bits] == INF) {
            int mn = Integer.MAX_VALUE;
            for (int next = 0; next < N; next++) {
                if ((bits & (1 << next)) == 0 && prev != next) {
                    int cost = values[prev][next] == 0 ? INF : values[prev][next];
                    mn = Math.min(mn, tsp(next, bits | (1 << next)) + cost);
                }
            }
            memory[prev][bits] = mn;
        }

        return memory[prev][bits] ;
    }

}