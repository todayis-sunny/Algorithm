// 13560. [P5] 축구 게임.

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N;
    static int[] win;
    static int ans = 1;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        win = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int n = 0; n < N; n++) {
            win[n] = Integer.parseInt(st.nextToken());
        }
    }

    static void solve() {
        // 오름차순 정렬
        Arrays.sort(win);
        int total = 0;
        for (int sco : win) {
            total += sco;
        }
        // 승리수가 N(N-1)/2가 아닌경우 성립하지 않음
        if (total != (N * (N - 1)) / 2) {
            ans = -1;
            return;
        }
        // i번째 팀에 대해서, 현재 팀보다 못하는 팀을 모두 이긴다고 가정
        int check = 0;
        for (int i = 0; i < N; i++) {
            check += win[i] - i;
            if (check < 0) {
                ans = -1;
                return;
            }
        }
    }

    static void output() throws IOException {
        bw.write(String.valueOf(ans));
        bw.flush();
    }
}
