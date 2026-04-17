// 01305. [P4] 광고.

import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int L; // L: 광고판의 크기
    static String ad; // ad: 광고
    static int ans; // ans: 가능한 광고의 길이중 가장 짧은 것의 길이

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        L = Integer.parseInt(br.readLine());
        ad = br.readLine();
    }

    static void solve() {
        int[] table = makeTable(ad);
        ans = L - table[L - 1];
    }

    static void output() throws IOException {
        bw.write(String.valueOf(ans));
        bw.flush();
    }

    static int[] makeTable(String pattern) {
        int patternSize = pattern.length();
        int[] table = new int[patternSize];
        int j = 0;
        for (int i = 1; i < patternSize; i++) {
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
