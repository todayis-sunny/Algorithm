// 17089. [G5] 세 친구.

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static final int INF = Integer.MAX_VALUE;
    static int N, M;
    static int[][] friends;
    static List<List<Integer>> lists = new ArrayList<>();
    // [i][j] : i와 j는 친구 관계(1, O), (0, X), [i][0] : i의 친구 수,
    static int ans = INF;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        friends = new int[N + 1][N + 1];
        for (int i = 0; i <= N; i++) {
            lists.add(new ArrayList<>());
        }
        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (a > b) {
                int c = a;
                a = b;
                b = c;
            }
            friends[a][b] = 1;
            friends[a][0]++;
            friends[b][0]++;
            lists.get(a).add(b);
        }
    }

    static void solve() {
        // a친구를 선택 (제일 작은 번호)
        for (int a = 1; a <= N; a++) {
            if (lists.get(a).size() <= 1) continue;
            // a-b친구 관계
            for (int b : lists.get(a)) {
                // b-c친구 관계
                for (int c : lists.get(b)) {
                    // a-c가 친구가 아니라면 스킵
                    if (friends[a][c] != 1) continue;
                    ans = Math.min(ans, friends[a][0] + friends[b][0] + friends[c][0] - 6);
                }
            }
        }
    }

    static void output() throws IOException {
        if (ans == INF) {
            bw.write("-1");
        } else {
            bw.write(String.valueOf(ans));
        }
        bw.flush();
    }
}
