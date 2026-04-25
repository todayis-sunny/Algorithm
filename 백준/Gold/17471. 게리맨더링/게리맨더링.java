// 17471. [G3] 게리맨더링.

import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static final int INF = Integer.MAX_VALUE;
    static int N; // 도시수
    static int[] population; // 인구수
    static List<List<Integer>> adj = new ArrayList<>(); // 1-based;
    static int ans = INF;
    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        // 인구수 초기화
        population = new int[N + 1]; // 1-based
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            population[i] = Integer.parseInt(st.nextToken());
        }
        // 인접행렬 초기화
        for (int i = 0; i <= N; i++) { // 1-based 이므로 N + 1
            adj.add(new ArrayList<>());
        }
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int cnt = Integer.parseInt(st.nextToken());
            for (int j = 0; j < cnt; j++) {
                int city = Integer.parseInt(st.nextToken());
                adj.get(i).add(city);
            }
        }
    }
    static void solve() {
        for (int bit = 1; bit < (1 << (N - 1)); bit++) {
            check(bit);
            if (ans == 0) return;
        }
    }

    static void output() throws IOException {
        if (ans == INF) {
            ans = -1;
        }
        bw.write(String.valueOf(ans));
        bw.flush();
    }

    static void check(int bit) {
        // 각 선거구가 적어도 하나의 구역을 포함하지 않은 경우
        if (Integer.bitCount(bit) == N || Integer.bitCount(bit) == 0) {
            return;
        }
        int p0 = 0, p1 = 0;
        for (int i = 0; i < N; i++) {
            int mask = (1 << i);
            if ((bit & mask) != 0) { // p0 인구수 증가
                p0 += population[i + 1];
            } else {
                p1 += population[i + 1]; // p1 인구수 증가
            }
        }
        // 해당 인구수가 최적이 될수 없다면 종료
        int p = Math.abs(p0 - p1);
        if (p >= ans) return;
        if(dfs(bit)) {
            ans = p;
        }
    }

    static boolean dfs(int bit) {
        boolean[] visited0 = new boolean[N + 1];
        boolean[] visited1 = new boolean[N + 1];
        visited0[0] = true;
        visited1[0] = true;
        for (int i = 0; i < N; i++) {
            int mask = (1 << i);
            if ((bit & mask) != 0) { // 해당 도시는 1의 도시 이므로 0의 방문 여부에서는 방문 처리
                visited0[i + 1] = true;
            } else {
                visited1[i + 1] = true; // 해당 도시는 0의 도시 이므로 1의 방문 여부에서는 방문 처리
            }
        }
        return find(visited0) && find(visited1);
    }

    static boolean find(boolean[] visited) {
        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                return bfs(i, visited);
            }
        }
        return false;
    }

    static boolean bfs(int city, boolean[] visited) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(city);
        visited[city] = true;
        while (!q.isEmpty()) {
            int curr = q.poll();
            for (int next : adj.get(curr)) {
                if (!visited[next]) {
                    visited[next] = true;
                    q.offer(next);
                }
            }
        }
        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                return false;
            }
        }
        return true;
    }
}
