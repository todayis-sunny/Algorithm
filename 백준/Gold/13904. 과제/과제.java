// 13904. [G3] 과제.

import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N, ans = 0;
    static List<List<Integer>> list = new ArrayList<>();
    static PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i <= 1_000; i++) {
            list.add(new ArrayList<>());
        }
        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            int end = Integer.parseInt(st.nextToken());
            int score = Integer.parseInt(st.nextToken());
            list.get(end).add(score);
        }
    }

    static void solve() {
        for (int day = 1_000; day >= 1; day--) {
            for (int score: list.get(day)) {
                pq.offer(score);
            }
            if (!pq.isEmpty()) {
                ans += pq.poll();
            }
        }
    }

    static void output() throws IOException {
        bw.write(String.valueOf(ans));
        bw.flush();
    }
}
