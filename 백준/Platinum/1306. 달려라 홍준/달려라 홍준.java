// 01306. [P5] 달려라 홍준.

import java.io.*;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int N, M;
    static int[] ad;
    static PriorityQueue<Advertisement> pq = new PriorityQueue<>(Comparator.comparing(Advertisement::getLight).reversed());

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        ad = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int n = 0; n < N; n++) {
            ad[n] = Integer.parseInt(st.nextToken());
        }
    }

    static void solve() {
        // 초기 광고 삽입
        for (int i = 0; i < 2 * M - 1; i++) {
            pq.offer(new Advertisement(ad[i], i));
        }
        sb.append(pq.peek().light).append(" ");
        // 달리기
        int left = 1, right = 2 * M - 1;
        while (right < N) {
            pq.offer(new Advertisement(ad[right], right));
            while (pq.peek().idx < left || pq.peek().idx > right) {
                pq.poll();
            }
            sb.append(pq.peek().light).append(" ");
            left++;
            right++;
        }
    }

    static void output() throws IOException {
        bw.write(sb.toString());
        bw.flush();
    }

    static class Advertisement {
        int light, idx;

        public Advertisement(int light, int idx) {
            this.light = light;
            this.idx = idx;
        }

        public int getLight() {
            return light;
        }

        public int getIdx() {
            return idx;
        }
    }

}
