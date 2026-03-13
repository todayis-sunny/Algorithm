// 17071. [P5] 숨바꼭질 5.

import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N, K;
    static int[] sister = new int[500_001]; // [i] = t : 여동생이 해당 위치(i)에 도달하는 시각(t).
    static int ans = -1;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
    }

    static void solve() {
        // 0. 같은 위치인 경우 종료.
        if (N == K) {
            ans = 0;
            return;
        }
        // 1. 동생의 위치에 시간을 기입.
        Arrays.fill(sister, -1);
        for (int i = K, d = 0; i <= 500_000; i += d) {
            sister[i] = d++;
        }
        // 2. 수빈이가 움직여서 확인하기.
        int[][] visited = new int[2][500_001];
        Arrays.fill(visited[0], -1);
        Arrays.fill(visited[1], -1);
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(N, 0));
        visited[0][N] = 0;
        while (!queue.isEmpty()) {
            Node curr = queue.poll();
            // 이미 방문한 곳이라도 동생이 나중에 이곳으로 올 수 있기 때문.
            int[] nextIndexArray = {curr.idx - 1, curr.idx + 1, curr.idx * 2};

            for (int nextIdx : nextIndexArray) {
                if (nextIdx < 0 || nextIdx > 500_000) continue;

                int nextTime = curr.time + 1;
                int nextParity = nextTime % 2;

                if (visited[nextParity][nextIdx] == -1) {
                    visited[nextParity][nextIdx] = nextTime;
                    queue.offer(new Node(nextIdx, nextTime));
                }
            }
        }
        // 동생이 i 위치에 t 시각에 도착할 때,
        // 수빈이가 그 위치에 t 보다 일찍(혹은 같게) 도착했고 홀짝이 같다면 성공.
        for (int i = 0; i <= 500_000; i++) {
            int t = sister[i];
            if (t == -1) continue; // 동생이 안 가는 곳.

            int subin = visited[t % 2][i];
            if (subin != -1 && subin <= t) {
                // 여러 후보 중 동생이 가장 빨리 도달하는 시간을 찾아야 함.
                if (ans == -1 || ans > t) {
                    ans = t;
                }
            }
        }

    }

    static void output() throws IOException {
        bw.write(String.valueOf(ans));
        bw.flush();
    }

    static class Node {
        int idx;
        int time;

        public Node(int idx, int time) {
            this.idx = idx;
            this.time = time;
        }
    }
}
