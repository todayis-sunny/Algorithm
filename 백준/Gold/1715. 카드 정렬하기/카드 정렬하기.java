// 01715. [G4] 카드 정렬하기.

import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N, ans = 0;
    static PriorityQueue<Integer> pq;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        pq = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            int card = Integer.parseInt(br.readLine());
            pq.offer(card);
        }
    }

    static void solve() {
        while (pq.size() > 1) {
            int card1 = pq.poll();
            int card2 = pq.poll();
            int mix = card1 + card2;
            ans += (mix);
            pq.offer(mix);
        }
    }

    static void output() throws IOException {
        bw.write(String.valueOf(ans));
        bw.flush();
    }
}
