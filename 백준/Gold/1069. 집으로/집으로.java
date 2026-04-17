// 01069. [G3] 집으로.

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int X, Y, D, T; // 현재 위치: (X, Y), T초에 D만큼 이동
    static double dist; // 거리
    static double ans; // 정답

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
    }

    static void solve() {
        dist = Math.sqrt(Math.pow(X, 2) + Math.pow(Y, 2));
        ans = dist;
        if (D <= T) { // 점프 효율이 안 나오는 경우
            ans = dist;
        } else if (D <= dist) {
            int jump = (int) (dist / D);
            ans = Math.min(ans, (T * jump) + (dist - D * jump));
            ans = Math.min(ans, T * (jump + 1));
        } else {
            ans = Math.min(ans, T + (D - dist));
            ans = Math.min(ans, T * 2);
        }
    }

    static void output() throws IOException {
        bw.write(String.valueOf(ans));
        bw.flush();
    }
}
