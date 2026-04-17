// 02243. [P5] 사탕상자.
import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static final int LIMIT = 1_000_001;
    static int N, A, B, C;
    static int memo;
    static SegmentTree segmentTree;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        sb = new StringBuilder();
        segmentTree = new SegmentTree(LIMIT);

    }

    static void solve() throws IOException {
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            if (A == 1) { // 1 : 사탕을 꺼내는 경우
                B = Integer.parseInt(st.nextToken()); // 꺼낼 사탕의 순위
                segmentTree.find(1, LIMIT, 1, B);
                sb.append(memo).append("\n");
            } else { // 2 : 사탕을 넣는 경우
                B = Integer.parseInt(st.nextToken()); // 맛
                C = Integer.parseInt(st.nextToken()); // 개수
                segmentTree.update(1, LIMIT, 1, B, C);
            }
        }
    }

    static void output() throws IOException {
        System.out.println(sb);
    }

    static class SegmentTree {
        int[] tree;
        int size;
        int height;

        public SegmentTree(int size) {
            this.height = (int) Math.ceil(Math.log(size) / Math.log(2)) + 1;
            this.size = (int) Math.pow(2, this.height);
            this.tree = new int[this.size];
        }

        public void update(int start, int end, int node, int idx, int diff) {
            if (idx >= start && idx <= end) {
                tree[node] += diff;
            } else {
                return;
            }
            if (start == end) {
                return;

            }
            int mid = (start + end) / 2;
            update(start, mid, node * 2, idx, diff);
            update(mid + 1, end, node * 2 + 1, idx, diff);
        }

        public void find(int start, int end, int node, int rank) {
            tree[node] -= 1; // 사탕이 하나 빠지므로
            if (start == end) { // 최하단에 도달하면 사탕의 맛을 반환
                memo = start;
            }
            int mid = (start + end) / 2;

            if (node * 2 >= size) {
                return;
            }

            if (rank <= tree[node * 2]) {
                find(start, mid, node * 2, rank);
            } else {
                find(mid + 1, end, node * 2 + 1, rank - tree[node * 2]);
            }
        }
    }
}
