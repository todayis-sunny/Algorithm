// 20311. [G5] 화학 실험.
import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N, K;
    static int[] examiner; // 시험관
    static boolean possible = true; // 가능성
    static PriorityQueue<Examiner> pq;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        bw.flush();
    }

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        pq = new PriorityQueue<>(Comparator.comparing(Examiner::getCnt).reversed());
        examiner = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        int check = (N % 2 == 0) ? N / 2 : (N + 1) / 2;
        for (int k = 1; k <= K; k++) {
            int cnt = Integer.parseInt(st.nextToken());
            if (cnt > check) {
                possible = false;
                bw.write("-1");
                return;
            }
            pq.offer(new Examiner(k, cnt));
        }
    }

    static void solve() throws IOException {
        if (possible) {
            int prevId = 0;
            while (!pq.isEmpty()) {
                Examiner wait = null;
                Examiner curr;
                if (prevId == pq.peek().id) {
                    wait = pq.poll();
                }
                curr = pq.poll();
                bw.write(curr.id + " ");
                prevId = curr.id;
                if (wait != null) {
                    pq.offer(wait);
                }
                if (--curr.cnt > 0) {
                    pq.offer(curr);
                }
            }
        }
    }

    public static class Examiner {
        int id;
        int cnt;

        public Examiner(int id, int cnt) {
            this.id = id;
            this.cnt = cnt;
        }

        public int getCnt() {
            return cnt;
        }
    }
}
