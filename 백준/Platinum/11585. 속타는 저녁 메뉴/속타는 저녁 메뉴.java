// 11585. [P5] 속타는 저녁 메뉴.

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N; // N: 원형 룰렛의 칸 수
    static char[] parent, pattern;
    static int cnt = 0; // 횟수
    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        parent = new char[2 * N];
        pattern = new char[N];
        st = new StringTokenizer(br.readLine());
        for (int n = 0; n < N; n++) {
            char c = st.nextToken().charAt(0);
            parent[n] = parent[n + N] = c;
        }
        st = new StringTokenizer(br.readLine());
        for (int n = 0; n < N; n++) {
            pattern[n] = st.nextToken().charAt(0);
        }
    }

    static void solve() {
        kmp(parent, pattern);
    }

    static void output() throws IOException {
        int g = gcd(N, cnt);
        bw.write((cnt / g) + "/" + (N / g));
        bw.flush();
    }

    static void kmp(char[] parent, char[] pattern) {
        int[] table = makeTable(pattern);
        int j = 0;
        for (int i = 0; i < 2 * N - 1; i++) { // 첫 문자열과 마지막 문자 중복 방지
            while (j > 0 && parent[i] != pattern[j]) {
                j = table[j - 1];
            }
            if (parent[i] == pattern[j]) {
                if (j == N - 1) {
                    cnt++;
                    j = table[j];
                } else {
                    j++;
                }
            }
        }
    }

    static int[] makeTable(char[] pattern) {
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

    static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
}
