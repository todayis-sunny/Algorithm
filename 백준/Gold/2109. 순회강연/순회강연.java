// 02109. [G3] 순회강연.

import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N;
    static Lecture[] lectures;
    static PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
    static int ans = 0;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        lectures = new Lecture[N + 1];
        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            lectures[n] = new Lecture(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        lectures[N] = new Lecture(0, 0);
    }

    static void solve() {
        Arrays.sort(lectures, (l1, l2) -> l2.deadline - l1.deadline);

        int day = lectures[0].deadline;
        int idx = 0;
        while (idx < N) {
            // 같은 날끼리 넣기
            while (day == lectures[idx].deadline) {
                pq.offer(lectures[idx++].pay);
            }
            int cnt = day - lectures[idx].deadline;
            while (!pq.isEmpty() && cnt-- > 0) {
                ans += pq.poll();
            }
            day = lectures[idx].deadline;
        }
    }

    static void output() throws IOException {
        bw.write(String.valueOf(ans));
        bw.flush();
    }

    static class Lecture {
        int pay;
        int deadline;

        public Lecture(int pay, int deadline) {
            this.pay = pay;
            this.deadline = deadline;
        }
    }
}
