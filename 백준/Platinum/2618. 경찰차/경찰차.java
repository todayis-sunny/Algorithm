// 02618. [P4] 경찰차.

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N, W;
    static int[][] dp;
    static boolean[][] route;
    static Node[] events;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        W = Integer.parseInt(br.readLine());
        events = new Node[W + 2];
        dp = new int[W + 2][W + 2];
        route = new boolean[W + 2][W + 2]; // false : 경찰차1 | true : 경찰차2
        events[0] = new Node(1, 1);
        events[1] = new Node(N, N);
        for (int w = 0; w < W+2; w++) {
            Arrays.fill(dp[w], -1);
        }

        for (int w = 2; w < W + 2; w++) {
            st = new StringTokenizer(br.readLine());
            events[w] = (new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        bw.write(solve(0, 1) + "\n");
        int e1 = 0;
        int e2 = 1;
        for (int w = 2; w < W+2; w++) {
            if (!route[e1][e2]) {
                bw.write("1\n");
                e1 = w;
            } else {
                bw.write("2\n");
                e2 = w;
            }
        }
        bw.flush();
    }

    /**
     * 이동하는 거리를 구함
     *
     * @param e1 사건1
     * @param e2 사건2
     * @return 사건2에서 사건1으로 이동하는 거리
     */
    static int getDist(int e1, int e2) {
        return Math.abs(events[e2].x - events[e1].x) + Math.abs(events[e2].y - events[e1].y);
    }

    /**
     * 두 경철차가 각각 최근 사건1, 사건2을 해결했을 때 남은 사건의 최소 이동거리
     * @param e1 사건1
     * @param e2 사건2
     * @return 최소 이동거리
     */
    static int solve(int e1, int e2) {
        // 다음 사건
        int next = Math.max(e1, e2) + 1;
        // 다음 사건이 없음
        if (next == W + 2) {
            return 0;
        }
        if (dp[e1][e2] != -1) {
            return dp[e1][e2];
        }
        // 해당 사건을 첫 번째 or 두 번째 경찰차가 해결
        int sol1 = solve(next, e2) + getDist(e1, next);
        int sol2 = solve(e1, next) + getDist(e2, next);
        if (sol1 < sol2) {
            dp[e1][e2] = sol1;
        } else {
            route[e1][e2] = true;
            dp[e1][e2] = sol2;
        }
        return dp[e1][e2];
    }

    static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
