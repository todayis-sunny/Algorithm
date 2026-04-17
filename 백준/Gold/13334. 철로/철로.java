// 13334. [G2] 철로.
import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N, d, ans;
    static List<Pair> list;
    static PriorityQueue<Integer> pq;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        bw.write(String.valueOf(ans));
        bw.flush();
    }

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (a < b) {
                list.add(new Pair(a, b));
            } else {
                list.add(new Pair(b, a));
            }
        }
        d = Integer.parseInt(br.readLine());
        list.sort(Comparator.comparing(Pair::getEnd));
        ans = 0;
    }

    static void solve() {
        pq = new PriorityQueue<>();
        for (Pair p : list) {
            // 현재 pair의 끝점에서 d를 뺀 값보다 작은 시작점 제거
            while (!pq.isEmpty() && pq.peek() < p.end - d) {
                pq.poll();
            }
            // 현재 pair의 시작점이 범위 안에 들어오면 추가
            if (p.start >= p.end - d) {
                pq.offer(p.start);
            }
            // 최대 개수 갱신
            ans = Math.max(ans, pq.size());
        }

    }

    static class Pair {
        int start, end;

        public Pair(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public int getEnd() {
            return end;
        }
    }
}
