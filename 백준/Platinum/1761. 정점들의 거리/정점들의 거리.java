// 01761. [P5] 정점들의 거리.

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N, M, H;
    static List<Node>[] list;
    static int[][] dp;
    static int[] dis, depth;

    public static void main(String[] args) throws IOException {
        input();
        bw.flush();
    }

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        list = new List[N + 1];
        for (int i = 0; i < N+1; i++) {
            list[i] = new ArrayList<>();
        }
        for (int i = 0; i < N-1; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            list[from].add(new Node(to, w));
            list[to].add(new Node(from, w));
        }
        H = getTreeH();
        depth = new int[N+1];
        dis = new int[N+1];
        dp = new int[N+1][H];

        init(1, 1, 0);
        fillParents();

        M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            int res = lca(a, b);
            bw.write(dis[a] + dis[b] - 2 * dis[res] + "\n");
        }
    }

    static int getTreeH() {
        return (int) Math.ceil(Math.log(N) / Math.log(2)) + 1;
    }

    static void init(int curr, int h, int pa) {
        depth[curr] = h;
        for (Node next : list[curr]) {
            if (next.to != pa) {
                dis[next.to] = dis[curr] + next.w;
                init(next.to, h+1, curr);
                dp[next.to][0] = curr;
            }
        }
    }

    static void fillParents() {
        for (int i = 1; i < H; i++) {
            for (int j = 1; j < N+1; j++) {
                dp[j][i] = dp[dp[j][i-1]][i-1];
            }
        }
    }

    static int lca(int a, int b) {
        int aH = depth[a];
        int bH = depth[b];

        if (aH < bH) {
            int temp = a;
            a = b;
            b = temp;
        }

        for (int i = H-1; i >= 0; i--) {
            if(Math.pow(2, i) <= depth[a] - depth[b]) {
                a = dp[a][i];
            }
        }

        if (a == b) {
            return a;
        }

        for (int i = H -1; i>= 0; i--) {
            if (dp[a][i] != dp[b][i]) {
                a = dp[a][i];
                b = dp[b][i];
            }
        }
        return dp[a][0];
    }

    static class Node {
        int to, w;

        public Node(int to, int w) {
            this.to = to;
            this.w = w;
        }
    }
}
