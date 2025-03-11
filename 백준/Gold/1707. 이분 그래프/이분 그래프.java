// 01707. [G4] 이분 그래프.

import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int TC;
    static int V, E;
    static List<Integer>[] list;
    static int[] color;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        input();
        output();
    }

    static void input() throws IOException {
        TC = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < TC; tc++) {
            st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());

            list = new ArrayList[V + 1];
            for (int i = 1; i <= V; i++) {
                list[i] = new ArrayList<>();
            }

            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                list[a].add(b);
                list[b].add(a);
            }
            color = new int[V + 1];
            isBipartiteGraph();
        }
    }

    static void output() throws IOException {
        bw.write(sb.toString());
        bw.flush();
    }

    static void isBipartiteGraph() {
        Queue<Integer> q = new LinkedList<>();

        for (int i = 1; i <= V; i++) {
            if (color[i] == 0) {
                color[i] = 1;
                q.offer(i);
            }
            while (!q.isEmpty()) {
                int curr = q.poll();

                for (int next : list[curr]) {
                    if (color[next] == color[curr]) {
                        sb.append("NO\n");
                        return;
                    }
                    if (color[next] == 0) {
                        q.offer(next);
                        if (color[curr] == 1) {
                            color[next] = 2;
                        } else {
                            color[next] = 1;
                        }
                    }
                }
            }
        }
        sb.append("YES\n");
    }
}
