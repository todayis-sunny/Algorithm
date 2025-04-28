// 07575. [P5] 바이러스.

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N, K; // N: 프로그램의 개수, K: 최소 길이
    static int[][] codes; // 코드들
    static boolean[] virus; // 감염 여부

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        codes = new int[N][];
        virus = new boolean[N];
        for (int i = 0; i < N; i++) {
            int size = Integer.parseInt(br.readLine());
            codes[i] = new int[size];
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < size; j++) {
                codes[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static void solve() {
        for (int i = 0; i < codes[0].length - K + 1; i++) {
            int[] pattern = Arrays.copyOfRange(codes[0], i, i + K);
            customKmp(pattern);
        }
    }

    static void output() throws IOException {
        for (int n = 1; n < N; n++) {
            if (!virus[n]) {
                bw.write("NO");
                bw.flush();
                return;
            }
        }
        bw.write("YES");
        bw.flush();
    }
    static void customKmp(int[] pattern) {
        int[] table = makeTable(pattern);
        for (int n = 1; n < N; n++) {
            if (virus[n]) continue; // 감염 확인되었으면 패스
            // 정방향 체크
            int j = 0;
            int[] parent = codes[n];
            for (int i = 0; i < parent.length; i++) {
                while (j > 0 && parent[i] != pattern[j]) {
                    j = table[j - 1];
                }
                if (parent[i] == pattern[j]) {
                    if (j == pattern.length - 1) {
                        virus[n] = true;
                        continue;
                    } else {
                        j++;
                    }
                }
            }
            // 역방향 체크
            j = 0;
            int[] revParent = new int[parent.length];
            for (int i = 0; i < parent.length; i++) {
                revParent[i] = parent[parent.length - 1 - i];
            }
            for (int i = 0; i < revParent.length; i++) {
                while (j > 0 && revParent[i] != pattern[j]) {
                    j = table[j - 1];
                }
                if (revParent[i] == pattern[j]) {
                    if (j == pattern.length - 1) {
                        virus[n] = true;
                    } else {
                        j++;
                    }
                }
            }
        }
    }

    static int[] makeTable(int[] pattern) {
        int patternSize = pattern.length;
        int[] table = new int[patternSize];
        int j = 0;
        for (int i = 1; i < patternSize; i++) {
            while (j > 0 && pattern[i] != pattern[j]) {
                j = table[j - 1];
            }
            if (pattern[i] == pattern[j]) {
                table[i] = ++j;
            }
        }
        return table;
    }
}
