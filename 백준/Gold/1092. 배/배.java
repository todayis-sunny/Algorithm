// 01092. [G5] 배.

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N, M;
    static int[] cranes, boxs;
    static int ans = 0;
    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        cranes = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int n = 0; n < N; n++) {
            cranes[n] = Integer.parseInt(st.nextToken());
        }
        M = Integer.parseInt(br.readLine());
        boxs = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int m = 0; m < M; m++) {
            boxs[m] = Integer.parseInt(st.nextToken());
        }
    }

    static void solve() {
        Arrays.sort(cranes);
        Arrays.sort(boxs);
        if (boxs[M - 1] > cranes[N - 1]) {
            ans = -1;
            return;
        }
        int remain = M;
        while (remain > 0) {
            craneLoop:
            for (int i = N - 1; i >= 0; i--) {
                if (remain == 0) break;
                for (int j = M - 1; j >= 0; j--) {
                    if (boxs[j] != 0 && boxs[j] <= cranes[i]) {
                        boxs[j] = 0;
                        remain--;
                        continue craneLoop;
                    }
                }
            }
            ans++;
        }
    }

    static void output() throws IOException {
        bw.write(String.valueOf(ans));
        bw.flush();
    }
}
