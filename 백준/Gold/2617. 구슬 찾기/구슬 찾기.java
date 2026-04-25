// 02617. [G4] 구슬 찾기.

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N, M; // N: 구술의 수, M: 관계의 수
    static int[][] relationship;
    static final int LIGHT = -1, UNKNOWN = 0, HEAVY = 1;
    static int ans = 0;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        // 구슬과 관계 개수 입력
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        // 구슬 관계 배열 초기화
        relationship = new int[N + 1][N + 1]; // 1 -based
        // 관계 입력
        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int heavy = Integer.parseInt(st.nextToken());
            int light = Integer.parseInt(st.nextToken());
            relationship[heavy][light] = HEAVY;
            relationship[light][heavy] = LIGHT;
        }
    }

    static void solve() {
        for (int bead = 1; bead <= N; bead++) {
            ans += bfs(bead);
        }
    }

    static void output() throws IOException {
        bw.write(String.valueOf(ans));
        bw.flush();
    }

    static int bfs(int bead) {
        boolean[] visited = new boolean[N + 1];
        Queue<Integer> lightQ = new LinkedList<>();
        lightQ.offer(bead);
        visited[bead] = true;
        int light = 0;
        while (!lightQ.isEmpty()) {
            int curr = lightQ.poll();
            for (int next = 1; next <= N; next++) {
                // 방문한 경우 스킵
                if (visited[next]) continue;
                // 더 가벼운 경우 탐색
                if (relationship[curr][next] == LIGHT) {
                    lightQ.offer(next);
                    visited[next] = true;
                    light++;
                }
            }
        }
        if (light > N / 2) return 1;
        int heavy = 0;
        Queue<Integer> heavyQ = new LinkedList<>();
        heavyQ.offer(bead);
        while (!heavyQ.isEmpty()) {
            int curr = heavyQ.poll();
            for (int next = 1; next <= N; next++) {
                // 방문한 경우 스킵
                if (visited[next]) continue;
                // 더 무거운 경우 탐색
                if (relationship[curr][next] == HEAVY) {
                    heavyQ.offer(next);
                    visited[next] = true;
                    heavy++;
                }

            }
        }
        if (heavy > N / 2) return 1;
        return 0;
    }
}
