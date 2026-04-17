// 02916. [G4] 자와 각도기.

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static final int LIMIT = 360;
    static int N, K;
    static boolean[] angle = new boolean[LIMIT];
    static int[] element, shout;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        element = new int[N];
        shout = new int[K];
        st = new StringTokenizer(br.readLine());
        for (int n = 0; n < N; n++) {
            element[n] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int k = 0; k < K; k++) {
            shout[k] = Integer.parseInt(st.nextToken());
        }
    }

    static void solve() {
        angle[0] = true;
        for (int n = 0; n < N; n++) {
            int ag = 0;
            do {
                angle[ag] = true;
                for (int k = 0; k < LIMIT; k++) {
                    // 해당 각도가 없으면 스킵
                    if (!angle[k]) continue;
                    // 해당 각도에 합
                    angle[(k + ag) % 360] = true;
                    // 해당 각도의 차
                    angle[(Math.abs(k - ag))] = true;
                }
                // 각도에 해당 단위각을 더함
                ag = (element[n] + ag) % 360;
            } while (ag != 0);
        }
    }

    static void output() throws IOException {
        for (int k = 0; k < K; k++) {
            if (angle[shout[k]]) bw.write("YES\n");
            else bw.write("NO\n");
        }
        bw.flush();
    }
}
