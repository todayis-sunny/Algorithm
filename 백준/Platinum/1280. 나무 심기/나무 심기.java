// 01280. [P4] 나무 심기.

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static final int MAX = 200_001;
    static final int MOD = 1_000_000_007;
    static int N;
    static int[] tree;
    static FenwickTree cntTree, sumTree;
    static long ans = 1; // 곱을 구할 예정

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        // 1. 펜윅트리 초기화(나무의 개수, 위치의 합)
        cntTree = new FenwickTree(MAX);
        sumTree = new FenwickTree(MAX);
        // 2. 좌표 정보 입력
        tree = new int[N];
        for (int n = 0; n < N; n++) {
            // 0 ~ 200,000까지의 좌표가 올수 있으므로 1-based로 조정 필요
            // -> 1 ~ 200,001
            // 즉 (+1) 처리
            tree[n] = Integer.parseInt(br.readLine()) + 1;
        }
    }

    static void solve() {
        // 1. 첫 번째 나무는 위치만 업데이트
        cntTree.update(tree[0], 1);
        sumTree.update(tree[0], tree[0]);
        // 2. 그 이후 나무는 계산하면서 검사
        for (int i = 1; i < N; i++) {
            // a. 좌측 구간 계산 (개수 * 현재좌표) - (좌표합)
            long leftCnt = cntTree.query(tree[i] - 1);
            long leftSum = sumTree.query(tree[i] - 1);
            long leftDist = (leftCnt * tree[i] - leftSum) % MOD;

            // b. 우측 구간 계산 (좌표합) - (개수 * 현재좌표)
            long rightCnt = cntTree.query(MAX) - cntTree.query(tree[i]);
            long rightSum = sumTree.query(MAX) - sumTree.query(tree[i]);
            long rightDist = (rightSum - rightCnt * tree[i]) % MOD;

            // c. 현재 나무의 총 거리 합 계산 및 결과 곱하기
            long totalDist = (leftDist + rightDist) % MOD;
            ans = (ans * totalDist) % MOD;

            // d. 나무 심기 (트리 업데이트)
            cntTree.update(tree[i], 1);
            sumTree.update(tree[i], tree[i]);
        }
    }

    static void output() throws IOException {
        bw.write(String.valueOf(ans));
        bw.flush();
    }

    static class FenwickTree {
        long[] tree;
        int size;

        public FenwickTree(int size) {
            this.size = size;
            this.tree = new long[size + 1];
        }

        void update(int idx, int val) {
            while (idx <= size) {
                tree[idx] += val;
                idx += (idx & -idx);
            }
        }

        long query(int idx) {
            long res = 0;
            while (idx > 0) {
                res += tree[idx];
                idx -= (idx & -idx);
            }
            return res;
        }
    }
}
