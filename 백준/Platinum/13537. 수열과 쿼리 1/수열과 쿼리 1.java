// 13537. [P3] 수열과 쿼리 1.

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int N, M;
    static MergeSortTree mergeSortTree;
    static int[] elements;
    static Query[] queries;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        elements = new int[N + 1];
        for (int n = 1; n <= N; n++) {
            elements[n] = Integer.parseInt(st.nextToken());
        }
        M = Integer.parseInt(br.readLine());
        queries = new Query[M];
        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            queries[m] = new Query(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
    }

    static void solve() {
        mergeSortTree = new MergeSortTree(N);
        for (int idx = 1; idx <= N; idx++) {
            mergeSortTree.update(1, N, 1, idx);
        }
        for (List<Integer> list : mergeSortTree.tree) {
            Collections.sort(list);
        }
        for (int m = 0; m < M; m++) {
            int count = mergeSortTree.getCount(1, N, 1, queries[m].from, queries[m].to, queries[m].value);
            sb.append(count).append("\n");
        }
    }

    static void output() throws IOException {
        bw.write(sb.toString());
        bw.flush();
    }

    static class MergeSortTree {
        int size;
        List<List<Integer>> tree = new ArrayList<>();

        public MergeSortTree(int size) {
            int height = (int) Math.ceil(Math.log(size) / Math.log(2)) + 1;
            this.size = 1 << height;
            for (int i = 0; i < this.size; i++) {
                tree.add(new ArrayList<>());
            }
        }

        void update(int start, int end, int node, int idx) {
            if (start > idx || end < idx) return;
            tree.get(node).add(elements[idx]);
            if (start == end) return;
            int mid = (start + end) / 2;
            update(start, mid, node * 2, idx);
            update(mid + 1, end, node * 2 + 1, idx);
        }

        public int getCount(int start, int end, int node, int from, int to, int value) {
            if (end < from || start > to) { // 범위 밖인 경우,
                return 0;
            }
            if (start >= from && end <= to) { // 범위 안인 경우,
                int count = upperBound(tree.get(node), value); // value이하인 원소 개수
                return tree.get(node).size() - count;
            }
            int mid = (start + end) / 2;
            return getCount(start, mid, node * 2, from, to, value) + getCount(mid + 1, end, node * 2 + 1, from, to, value);
        }

        static int upperBound(List<Integer> list, int value) {
            int left = 0;
            int right = list.size() - 1;
            while (left <= right) {
                int mid = (left + right) / 2;
                if (list.get(mid) <= value) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            return left;
        }
    }

    static class Query {
        int from, to, value;

        public Query(int from, int to, int value) {
            this.from = from;
            this.to = to;
            this.value = value;
        }
    }
}
