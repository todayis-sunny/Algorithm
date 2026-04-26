// 02464. [G1] 비밀번호.

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static long A;
    static long small, big;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        A = Long.parseLong(br.readLine());
    }

    static void solve() {
        small = getNearSmall();
        big = getNearBig();
    }

    static void output() throws IOException {
        bw.write(small + " " + big);
        bw.flush();
    }

    static long getNearBig() {
        long a = A;
        int c0 = 0; // 오른쪽 끝의 연속된 0의 개수
        int c1 = 0; // 그 다음 연속된 1의 개수

        // 01 패턴을 찾기 위한 과정
        long temp = a;
        while (((temp & 1) == 0) && (temp != 0)) {
            c0++;
            temp >>= 1;
        }
        while ((temp & 1) == 1) {
            c1++;
            temp >>= 1;
        }

        // 01 패턴이 없으면 (예: 111000...) 더 큰 수가 없을 수 있음
        if (c0 + c1 == 63 || c0 + c1 == 0) return 0;

        int p = c0 + c1; // 뒤에서부터 처음 01이 나오는 0의 위치
        a |= (1L << p);          // 0을 1로 (Set)
        a &= ~((1L << p) - 1);   // p보다 낮은 비트들 모두 0으로 (Clear)
        a |= (1L << (c1 - 1)) - 1; // 남은 1들을 오른쪽 끝으로 몰아넣음

        return a;
    }

    static long getNearSmall() {
        long a = A;
        int c1 = 0; // 오른쪽 끝의 연속된 1의 개수
        int c0 = 0; // 그 다음 연속된 0의 개수

        // 10 패턴을 찾기 위한 과정
        long temp = a;
        while ((temp & 1) == 1) {
            c1++;
            temp >>= 1;
        }
        if (temp == 0) return 0; // 111... 형태면 더 작은 수 없음

        while (((temp & 1) == 0) && (temp != 0)) {
            c0++;
            temp >>= 1;
        }

        int p = c0 + c1; // 뒤에서부터 처음 10이 나오는 1의 위치
        a &= ~(1L << p);         // 1을 0으로 (Clear)
        a &= ~((1L << p) - 1);   // p보다 낮은 비트들 일단 0으로

        // 남은 1들을 p 바로 다음부터 왼쪽으로 몰아넣음
        long mask = (1L << (c1 + 1)) - 1;
        a |= (mask << (c0 - 1));

        return a;
    }

}
