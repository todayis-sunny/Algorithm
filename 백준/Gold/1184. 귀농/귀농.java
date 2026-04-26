// 01184. [G1] 귀농.

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N;
    static int[][] info;
    static int[][] psum; // 누적 합
    static int ans;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        // 땅의 크기 입력
        N = Integer.parseInt(br.readLine());
        info = new int[N + 1][N + 1];
        for (int r = 1; r <= N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 1; c <= N; c++) {
                info[r][c] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static void solve() {
        // 누적합 처리
        psum = new int[N + 1][N + 1];
        for (int r = 1; r <= N; r++) {
            for (int c = 1; c <= N; c++) {
                psum[r][c] = psum[r - 1][c] + psum[r][c - 1] - psum[r - 1][c - 1] + info[r][c];
            }
        }
        // 모든 점를 공유 꼭지점으로 가정
        for (int r = 1; r < N; r++) {
            for (int c = 1; c < N; c++) {
                // 케이스 1: 왼쪽 위(2사분면) vs 오른쪽 아래(4사분면)
                ans += countMatchingSums(r, c, true);
                // 케이스 2: 오른쪽 위(1사분면) vs 왼쪽 아래(3사분면)
                ans += countMatchingSums(r, c, false);
            }
        }
    }

    static void output() throws IOException {
        bw.write(String.valueOf(ans));
        bw.flush();
    }

    /**
     * @param row 공유 좌표 행
     * @param col 공유 좌표 열
     * @param type: true 면 (TopLeft vs BottomRight), false 면 (TopRight vs BottomLeft)
     */
    static int countMatchingSums(int row, int col, boolean type) {
        Map<Integer, Integer> sumMap = new HashMap<>();
        int count = 0;

        if (type) {
            // 1. 왼쪽 위 방향 모든 사각형 합 저장
            for (int i = 1; i <= row; i++) {
                for (int j = 1; j <= col; j++) {
                    int areaSum = getAreaSum(i, j, row, col);
                    sumMap.put(areaSum, sumMap.getOrDefault(areaSum, 0) + 1);
                }
            }
            // 2. 오른쪽 아래 방향 모든 사각형 합 확인
            for (int i = row + 1; i <= N; i++) {
                for (int j = col + 1; j <= N; j++) {
                    int areaSum = getAreaSum(row + 1, col + 1, i, j);
                    count += sumMap.getOrDefault(areaSum, 0);
                }
            }
        } else {
            // 1. 오른쪽 위 방향 모든 사각형 합 저장
            for (int i = 1; i <= row; i++) {
                for (int j = col + 1; j <= N; j++) {
                    int areaSum = getAreaSum(i, col + 1, row, j);
                    sumMap.put(areaSum, sumMap.getOrDefault(areaSum, 0) + 1);
                }
            }
            // 2. 왼쪽 아래 방향 모든 사각형 합 확인
            for (int i = row + 1; i <= N; i++) {
                for (int j = 1; j <= col; j++) {
                    int areaSum = getAreaSum(row + 1, j, i, col);
                    count += sumMap.getOrDefault(areaSum, 0);
                }
            }
        }
        return count;
    }

    static int getAreaSum(int r1, int c1, int r2, int c2) {
        return psum[r2][c2] - psum[r1 - 1][c2] - psum[r2][c1 - 1] + psum[r1 - 1][c1 - 1];
    }
}
