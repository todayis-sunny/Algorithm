// 16437. [G3] 양 구출 작전.

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N;
    static List<List<Integer>> adj = new ArrayList<>(); // 1-based;
    static long ans = 0L;
    static int[] info; // 1-based;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        info = new int[N + 1];
        for (int n = 0; n <= N; n++) {
            adj.add(new ArrayList<>());
        }

        for (int child = 2; child <= N; child++) {
            st = new StringTokenizer(br.readLine());
            char ch = st.nextToken().charAt(0);
            int count = Integer.parseInt(st.nextToken());
            int parent = Integer.parseInt(st.nextToken());
            if (ch == 'S') {
                info[child] = count;
            } else {
                info[child] = -count;
            }
            adj.get(parent).add(child);
        }
    }

    static void solve() {
        ans = dfs(1);
    }

    static void output() throws IOException {
        bw.write(String.valueOf(ans));
        bw.flush();
    }

    static long dfs(int node) {
        long curr = info[node];
        for (int next : adj.get(node)) {
            curr += dfs(next);
        }
        return Math.max(curr, 0);
    }
}
