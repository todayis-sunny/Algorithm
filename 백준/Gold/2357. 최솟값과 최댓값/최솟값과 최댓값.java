// 02357. [G1] 최솟값과 최댓값.
import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N, M;
    static int[] element;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        element = new int[N+1];
        SegmentTree segmentTree = new SegmentTree(N);
        for (int n = 1; n <= N; n++) {
            element[n] = Integer.parseInt(br.readLine());
        }
        segmentTree.minInit(1, N, 1);
        segmentTree.maxInit(1, N, 1);
        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int min = segmentTree.getMin(1, N, 1, from, to);
            int max = segmentTree.getMax(1, N, 1, from, to);
            bw.write(min + " " + max + "\n");
        }
        bw.flush();
        bw.close();
        br.close();

    }

    public static class SegmentTree {
        int[] minTree;
        int[] maxTree;
        int size;

        public SegmentTree(int size) {
            int h = (int) Math.ceil(Math.log(size) / Math.log(2)) + 1;
            this.size = (int) Math.pow(2, h);
            minTree = new int[this.size];
            maxTree = new int[this.size];
        }

        public int minInit(int start, int end, int node) {
            if (start == end) {
                return minTree[node] = element[start];
            }
            int mid = (start + end) / 2;
            return minTree[node] = Math.min(
                    minInit(start, mid, node * 2),
                    minInit(mid + 1, end, node * 2 + 1)
            );
        }

        public int maxInit(int start, int end, int node) {
            if (start == end) {
                return maxTree[node] = element[start];
            }
            int mid = (start + end) / 2;
            return maxTree[node] = Math.max(
                    maxInit(start, mid, node * 2),
                    maxInit(mid + 1, end, node * 2 + 1)
            );
        }

        public int getMin(int start, int end, int node, int from, int to) {
            if (from > end || to < start) { // 범위 밖인 경우,
                return Integer.MAX_VALUE;
            }
            if (from <= start && to >= end) { // 범위 안인 경우,
                return minTree[node];
            }
            int mid = (start + end) / 2;
            return Math.min(getMin(start, mid, node * 2, from, to), getMin(mid + 1, end, node * 2 + 1, from, to));
        }

        public int getMax(int start, int end, int node, int from, int to) {
            if (from > end || to < start) { // 범위 밖인 경우,
                return Integer.MIN_VALUE;
            }
            if (from <= start && to >= end) { // 범위 안인 경우,
                return maxTree[node];
            }
            int mid = (start + end) / 2;
            return Math.max(getMax(start, mid, node * 2, from, to), getMax(mid + 1, end, node * 2 + 1, from, to));
        }
    }

}
