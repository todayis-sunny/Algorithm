// 09466. [G3] 텀 프로젝트.

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int TC, N, ans;
    static final int UNKNOW = 0, FINDING = 1, MATCHED = 2, SOLO = -1;
    static int[] wanted; // 1-based
    static int[] state;
    public static void main(String[] args) throws IOException {
        input();
        output();
    }

    static void input() throws IOException {
        TC = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < TC; tc++) {
            N = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            wanted = new int[N + 1];
            state = new int[N + 1];
            for (int n = 1; n <= N; n++) {
                wanted[n] = Integer.parseInt(st.nextToken());
            }
            ans = 0;
            solve();
        }
    }

    static void solve() {
        for (int n = 1; n <= N; n++) {
            if (state[n] == UNKNOW) dfs(n);
        }
        for (int n = 1; n <= N; n++) {
            if (state[n] == SOLO) ans++;
        }
        sb.append(ans).append("\n");

    }

    static void output() throws IOException {
        bw.write(sb.toString());
        bw.flush();
    }
    static void dfs(int student) {
        // 학생이 팀을 탐색중이면 루프 종료
        if (state[student] == FINDING) {
            order(student, MATCHED);
            return;
        }
        // 희망하는 학생이 이미 매칭된 팀이 있거나, 솔로라면 루프 종료
        if (state[student] == MATCHED || state[student] == SOLO) {
            return;
        }
        state[student] = FINDING;
        dfs(wanted[student]);
        if (state[student] != MATCHED) {
            state[student] = SOLO;
        }
    }

    static void order(int student, int status) {
        if (state[student] == status) return;
        state[student] = status;
        order(wanted[student], status);
    }
}
