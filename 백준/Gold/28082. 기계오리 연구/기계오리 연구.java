// 28082. [G1] 기계오리 연구.

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int N, K, answer;
    static final int MAX_POWER = 500 * 100;
    static int[] battery, power;

    public static void main(String[] args) throws IOException {
        answer = 0;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        battery = new int[N];
        power = new int[MAX_POWER + 1];

        for (int n = 0; n < N; n++) {
            int curr = Integer.parseInt(st.nextToken());
            for (int p = MAX_POWER - curr; p > 0; p--) {
                if (power[p] == 0) {
                    continue;
                }
                if (power[p] == K) {
                    continue;
                }
                if (power[p + curr] != 0 && power[p + curr] <= power[p] + 1) {
                    continue;
                }
                power[p + curr] = power[p] + 1;
            }
            power[curr] = 1;
        }

        for (int p = 0; p <= MAX_POWER; p++) {
            if (power[p] > 0) {
                sb.append(p + " ");
                answer++;
            }
        }
        bw.write(answer + "\n");
        bw.write(sb.toString());
        bw.flush();
    }
}
