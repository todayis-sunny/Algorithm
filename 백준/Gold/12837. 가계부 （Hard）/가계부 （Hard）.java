// 12873. [S3] 기념품.

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int N, Q;
    static int[][] qureyList;
    static SegmentTree segmentTree;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        // 쿼리 초기화
        qureyList = new int[Q][3];
        for (int q = 0; q < Q; q++) {
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 3; i++) {
                qureyList[q][i] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static void solve() {
        segmentTree = new SegmentTree(N);
        for (int[] q : qureyList) {
            if (q[0] == 1) {
                segmentTree.update(1, N, 1, q[1], q[2]);
            } else {
                sb.append(segmentTree.query(1, N, 1, q[1], q[2])).append("\n");
            }
        }
    }

    static void output() throws IOException {
        bw.write(sb.toString());
        bw.flush();
    }

    static class SegmentTree {
        long[] tree;
        int size;

        public SegmentTree(int size) {
            int h = (int) Math.ceil(Math.log(size) / Math.log(2)) + 1;
            this.size = (int) Math.pow(2, h);
            this.tree = new long[this.size];
        }

        // 해당 구간 업데이트
        void update(int start, int end, int node, int idx, long diff) {
            // 해당 구간이 포함되면 diff 적용
            if (idx >= start && idx <= end) {
                tree[node] += diff;
            } else {
                return;
            }
            // 더 이상 구간을 탐색할게 없으면 종료
            if (start == end) {
                return;
            }
            int mid = (start + end) / 2;
            update(start, mid, node * 2, idx, diff);
            update(mid + 1, end, node * 2 + 1, idx, diff);
        }

        // 해당 쿼리 계산 : 구간합 (from ~ to)
        long query(int start, int end, int node, int from, int to) {
            // 범위 밖의 경우
            if (end < from || start > to) {
                return 0;
            }
            // 범위 안인 경우
            if (start >= from && end <= to) {
                return tree[node];
            }
            int mid = (start + end) / 2;
            return query(start, mid, node * 2, from, to) + query(mid + 1, end, node * 2 + 1, from, to);
        }
    }
}

/*
 # 접근 방법
 - 업데이트와 구간합 쿼리를 중간마다 요구한다.
 - 연산량을 줄일수 있는 방법에 대해 생각한다.
 
 # 풀이방법
 - 최적의 풀이로 세그먼트 트리가 적합하다.
 - 중간마다 변화하는 누적합을 계산하기 위해서 update와 구간합을 구하는 query를 작성한다.
 */
