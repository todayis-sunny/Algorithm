// 09661. [G2] 돌 게임 7.

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static Long N;
    static String number;
    static int win;
    static String member;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        N = Long.parseLong(br.readLine());
    }

    static void solve() {
        if (N % 5 == 0 || N % 5 == 2) {
            member = "CY";
        } else {
            member = "SK";
        }
    }

    static void output() throws IOException {
        bw.write(member);
        bw.flush();
    }
}
