// 01786. [P5] 찾기.

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static String T, P; // T: parent, P: pattern
    static int ans; // T 중간에 P가 몇 번 나타나는지
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        T = br.readLine();
        P = br.readLine();
    }

    static void solve() {
        kmp(T, P);
    }

    static void output() throws IOException {
        bw.write(ans + "\n");
        bw.write(sb.toString());
        bw.flush();
    }

    static void kmp(String parent, String pattern) {
        int patternSize = pattern.length();
        int[] table = makeTable(pattern);
        int j = 0;
        for (int i = 0; i < parent.length(); i++) {
            while (j > 0 && parent.charAt(i) != pattern.charAt(j)) {
                j = table[j - 1];
            }
            if (parent.charAt(i) == pattern.charAt(j)) {
                if (j == patternSize - 1) {
                    ans++;
                    sb.append(i - patternSize + 2).append(" ");
                    j = table[j];
                } else {
                    j++;
                }
            }
        }
    }

    static int[] makeTable(String pattern) {
        int size = pattern.length();
        int[] table = new int[size];
        int j = 0;
        for (int i = 1; i < size; i++) {
            while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
                j = table[j - 1];
            }
            if (pattern.charAt(i) == pattern.charAt(j)) {
                table[i] = ++j;
            }
        }
        return table;
    }
}
