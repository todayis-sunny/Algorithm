// 11729. [G5] 하노이 탑 이동 순서.

import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N; // 첫 번째 장대에 쌓인 원판의 개수
    static StringBuilder sb = new StringBuilder();
    static int K = 0;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
    }

    static void solve() {
        hanoi(1, 3, 2, N);
    }

    static void output() throws IOException {
        bw.write(K + "\n");
        bw.write(sb.toString());
        bw.flush();
    }

    static void hanoi(int from, int to, int other, int n) {
        if (n == 1) {
            K++;
            sb.append(from).append(" ").append(to).append("\n");
            return;
        }
        // 가장 큰 원판을 옮기기 위해 가장 큰원판 N을 제외한 n-1개의 원판을 옮김 from -> other
        hanoi(from, other, to, n - 1);
        // 1개가 남은 가장 큰 원판을 옮김 from -> to
        hanoi(from, to, other, 1);
        // 큰원판 N을 제외하고 남은 n-1개의 원판을 이제 다시 목표 지점으로 옮김 other -> to
        hanoi(other, to, from, n - 1);
    }
}
