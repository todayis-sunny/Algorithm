// 01759. [G5] 암호 만들기.

import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static final Set<Character> voSet = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));
    static int L, C;
    static char[] data, code;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        data = new char[C];
        code = new char[L];
        st = new StringTokenizer(br.readLine());
        for (int c = 0; c < C; c++) {
            data[c] = st.nextToken().charAt(0);
        }
    }

    static void solve() {
        Arrays.sort(data);
        dfs(0, 0);
    }

    static void output() throws IOException {
        bw.write(sb.toString());
        bw.flush();
    }

    static void dfs(int depth, int start) {
        if (depth == L) {
            if (isValid()) {
                for (int l = 0; l < L; l++) {
                    sb.append(code[l]);
                }
                sb.append("\n");
            }
            return;
        }
        for (int i = start; i < C; i++) {
            code[depth] = data[i];
            dfs(depth + 1, i + 1);
        }
    }

    static boolean isValid() {
        int co = 0; // 자음
        int vo = 0; // 모음
        for (int i = 0; i < L; i++) {
            if (voSet.contains(code[i])) {
                vo++;
            } else {
                co++;
            }
        }
        return co >= 2 && vo >= 1;
    }
}
