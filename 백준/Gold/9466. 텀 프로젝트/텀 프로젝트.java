// 09466. [G3] 텀 프로젝트.

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int TC, N, cnt;
    static int[] wanted; // 1-based
    static boolean[] visited, finished;

    public static void main(String[] args) throws IOException {
        input();
        output();
    }

    static void input() throws IOException {
        TC = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < TC; tc++) {
            N = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            wanted = new int[N + 1];
            visited = new boolean[N + 1];
            finished = new boolean[N + 1];
            for (int n = 1; n <= N; n++) {
                wanted[n] = Integer.parseInt(st.nextToken());
            }
            cnt = 0;
            solve();
        }
    }

    static void solve() {
        for (int n = 1; n <= N; n++) {
            dfs(n);
        }
        sb.append(N - cnt).append("\n");

    }

    static void output() throws IOException {
        bw.write(sb.toString());
        bw.flush();
    }

    static void dfs(int curr) {
        visited[curr] = true;
        int next = wanted[curr];
        // 학생이 탐색되지 않은 경우
        if (!visited[next]) {
            dfs(next);
        } else {
            if (!finished[next]) {
                // 노드가 종료되려면 싸이클을 무조건 거침
                cnt++;
                while (next != curr) {
                    cnt++;
                    next = wanted[next];
                }
            }
        }
        finished[curr] = true;
    }
}
