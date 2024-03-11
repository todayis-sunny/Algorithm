// 01477. [G4] 휴게소 세우기.

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N, M, L;
    static int[] space, init, trans, count;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        space = new int[N + 2];
        init = new int[N + 1];
        trans = new int[N + 1];
        count = new int[N + 1];
        space[N + 1] = L;

        st = new StringTokenizer(br.readLine());

        for (int n = 1; n <= N; n++) {
            space[n] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(space);
        int max = Integer.MIN_VALUE;
        int idx = -1;
        for (int i = 0; i <= N; i++) {
            init[i] = trans[i] = space[i + 1] - space[i];
            if (init[i] > max) {
                max = init[i];
                idx = i;
            }
        }
        for (int m = 0; m < M; m++) {
            trans[idx] = (int) Math.ceil(init[idx] / (double) (++count[idx] + 1));
            max = 0;
            for (int i = 0; i <= N; i++) {
                if (trans[i] > max) {
                    max = trans[i];
                    idx = i;
                }
            }
        }
        int answer = 0;
        for (int i = 0; i <= N; i++) {
            answer = Math.max(answer, trans[i]);
        }
        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }
    
}