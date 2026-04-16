// 02043. [G1] 구간 합 구하기.

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static long[] element;
    static int N, M, K;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        element = new long[N + 1];
        SegmentTree segmentTree = new SegmentTree(N);
        for (int n = 1; n <= N; n++) {
            element[n] = Long.parseLong(br.readLine());
        }
        segmentTree.init(1, N, 1);
        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            int op = Integer.parseInt(st.nextToken());
            if (op == 1) {
                int idx = Integer.parseInt(st.nextToken());
                long num = Long.parseLong(st.nextToken());
                long diff = num - element[idx];
                segmentTree.update(1, N, 1, idx, diff);
                element[idx] = num;
            } else {
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                long sum = segmentTree.getSum(1, N, 1, from, to);
                bw.write(sum + "\n");
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }

    public static class SegmentTree {
        long[] tree;
        int size;

        public SegmentTree(int size) {
            int h = (int) Math.ceil(Math.log(size) / Math.log(2)) + 1;
            this.size = (int) Math.pow(2, h);
            tree = new long[this.size];
        }

        public long init(int start, int end, int node) {
            if (start == end) {
                return tree[node] = element[start];
            }
            int mid = (start + end) / 2;
            return tree[node] = init(start, mid, node * 2) + init(mid + 1, end, node * 2 + 1);
        }

        public void update(int start, int end, int node, int idx, long diff) {
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

        public long getSum(int start, int end, int node, int from, int to) {
            if (from > end || to < start) { // 범위 밖인 경우,
                return 0;
            }
            if (from <= start && to >= end) { // 범위 안인 경우,
                return tree[node];
            }
            int mid = (start + end) / 2;
            return getSum(start, mid, node * 2, from, to) + getSum(mid + 1, end, node * 2 + 1, from, to);
        }
    }
}