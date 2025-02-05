// 14428. [G1] 수열과 쿼리 16.

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static Segment segment;
    static int[] element;
    static int N, M;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        element = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            element[i] = Integer.parseInt(st.nextToken());
        }
        segment = new Segment(N);
        segment.init(1, N, 1);
        M = Integer.parseInt(br.readLine());
    }

    static void solve() throws IOException {
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            if (a == 1) {
                segment.update(1, N, 1, b, new Data(c, b));
            } else {
                int res = segment.getMindIndex(b, c);
                bw.write(res + "\n");
            }
        }
    }

    static void output() throws IOException {
        bw.flush();
    }

    static class Segment {
        Data[] tree;
        int size;

        public Segment(int size) {
            int h = (int) Math.ceil(Math.log(size) / Math.log(2)) + 1;
            this.size = (int) Math.pow(2, h);
            this.tree = new Data[this.size];
        }

        Data init(int start, int end, int node) {
            if (start == end) {
                if (start >= size) { // 잉여 데이터 추가
                    return tree[node] = new Data(Integer.MAX_VALUE, -1);
                }
                return tree[node] = new Data(element[start], start);
            }
            int mid = (start + end) / 2;
            return tree[node] = compare(init(start, mid, 2 * node), init(mid + 1, end, 2 * node + 1));
        }

        void update(int start, int end, int node, int idx, Data data) {
            if (idx < start || idx > end) { // 범위 밖인 경우
                return;
            }
            if (start == end) { // 최하단 노드인 경우
                tree[node] = data;
                return;
            }
            int mid = (start + end) / 2;
            update(start, mid, 2 * node, idx, data);
            update(mid + 1, end, 2 * node + 1, idx, data);
            tree[node] = compare(tree[2 * node], tree[2 * node + 1]);
        }

        private Data query(int start, int end, int node, int from, int to) {
            if (end < from || start > to) { // 범위 밖인 경우 잉여 데이터
                return new Data(Integer.MAX_VALUE, -1);
            }
            if (start >= from && end <= to) { // 범위 안인 경우,
                return tree[node];
            }
            // 걸치는 경우
            int mid = (start + end) / 2;
            Data left = query(start, mid, 2 * node, from, to);
            Data right = query(mid + 1, end, 2 * node + 1, from, to);
            return compare(left, right);
        }

        int getMindIndex(int from, int to) {
            Data res = query(1, N, 1, from, to);
            return res.index;
        }
    }

    static class Data {
        int value, index;

        public Data(int value, int index) {
            this.value = value;
            this.index = index;
        }
    }

    // 최소값 비교 + 값이 같으면 인덱스가 작은 쪽
    static Data compare(Data a, Data b) {
        if (a.value < b.value) {
            return a;
        } else if (a.value > b.value) {
            return b;
        } else {
            // 값이 같은 경우
            return (a.index < b.index) ? a : b;
        }
    }
}
