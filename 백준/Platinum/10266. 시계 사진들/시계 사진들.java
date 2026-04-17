// 10266. [P4] 시계 사진들.

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N; // N: 바늘의 수
    static final int SIZE = 360_000;
    static boolean[] clock1, clock2;
    static boolean ans = false;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        clock1 = new boolean[SIZE * 2];
        clock2 = new boolean[SIZE];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            clock1[num] = true;
            clock1[num + SIZE] = true;
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            clock2[num] = true;
        }
    }

    static void solve() {
        kmp();
    }

    static void output() throws IOException {
        if (ans) {
            bw.write("possible");
        } else {
            bw.write("impossible");
        }
        bw.flush();
    }

    static void kmp() {
        int[] table = makeTable(clock2);
        int idx = 0;
        for (int i = 0; i < SIZE * 2; i++) {
            while (idx > 0 && clock1[i] != clock2[idx]) {
                idx = table[idx - 1];
            }
            if (clock1[i] == clock2[idx]) {
                if (idx == SIZE - 1) {
                    ans = true;
                    return;
                }
                idx++;
            }
        }
    }

    static int[] makeTable(boolean[] clock) {
        int idx = 0;
        int[] table = new int[SIZE];
        for (int i = 1; i < SIZE; i++) {
            while (idx > 0 && clock[i] != clock[idx]) {
                idx = table[idx - 1];
            }
            if (clock[i] == clock[idx]) {
                table[i] = ++idx;
            }
        }
        return table;
    }
}
