// 23843. [G5] 콘센트.

import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N, M; // N: 전자기기 개수, M: 사용 가능한 콘센트 개수
    static int ans;
    static int[] device = new int[16];
    static PriorityQueue<Integer> pq = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for (int n = 0; n < N; n++) {
            int idx = log2(Integer.parseInt(st.nextToken()));
            device[idx] += 1;
        }
    }

    static void solve() {
        for (int m = 0; m < M; m++) {
            pq.offer(0);
        }
        for (int i = 15; i >= 0; i--) {
            while (device[i]-- > 0) {
                int outlet = pq.poll();
                outlet += (1 << i);
                pq.offer(outlet);
            }
        }
        while (!pq.isEmpty()) {
            ans = pq.poll();
        }
    }

    static void output() throws IOException {
        bw.write(String.valueOf(ans));
        bw.flush();
    }

    static int log2(int num) {
        return (int) (Math.log10(num) / Math.log10(2));
    }
}
