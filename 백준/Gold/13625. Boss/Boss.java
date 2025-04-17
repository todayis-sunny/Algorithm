// 13625. [G3] Boss.

import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int N, M, I; // 직원 수, 직속 상관 관계 수, 지시 수
    static Node[] nodes; // 노드관계 (껍데기)
    static int[] empAge; // 직원 나이
    static int[] empToNode; // 직원이 어떤 노드에 있는지
    static List<List<Integer>> adj = new ArrayList<>();
    static List<List<Integer>> revAdj = new ArrayList<>(); // 역인접행렬 (누가 나의 상관인지) -> 노드 관계로 표현
    static final int NOT_BOSS = -1;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        I = Integer.parseInt(st.nextToken());
        // 초기화 영역
        nodes = new Node[N + 1];
        empToNode = new int[N + 1];
        empAge = new int[N + 1]; // (1-based)
        for (int n = 0; n <= N; n++) {
            adj.add(new ArrayList<>());
            revAdj.add(new ArrayList<>());
        }
        // 나이 입력
        st = new StringTokenizer(br.readLine());
        for (int n = 1; n <= N; n++) {
            empAge[n] = Integer.parseInt(st.nextToken());
            nodes[n] = new Node(n);
            empToNode[n] = n;
        }
        // 관계 입력
        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken()); // 선임
            int to = Integer.parseInt(st.nextToken()); // 후임
            revAdj.get(to).add(from); // 후임의 선임을 넣어줌
        }
        // 쿼리 입력
        for (int i = 0; i < I; i++) {
            st = new StringTokenizer(br.readLine());
            String cmd = st.nextToken();
            if (cmd.equals("T")) {
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                swap(a, b);
            } else {
                int emp = Integer.parseInt(st.nextToken());
                int minAge = bfs(emp);
                if (minAge == NOT_BOSS) {
                    sb.append("*\n");
                } else {
                    sb.append(minAge).append("\n");
                }
            }
        }

    }

    static void solve() {

    }

    static void output() throws IOException {
        bw.write(sb.toString());
        bw.flush();
    }

    static void swap(int a, int b) {
        int aN = empToNode[a];
        int bN = empToNode[b];
        // Node의 직원을 교환
        int tmp = nodes[aN].emp;
        nodes[aN].emp = nodes[bN].emp;
        nodes[bN].emp = tmp;
        // 직원의 노드 번호를 교환
        int tmpToNode = empToNode[a];
        empToNode[a] = empToNode[b];
        empToNode[b] = tmpToNode;
    }

    static int bfs(int start) {
        int findNodeIdx = empToNode[start];
        int res = Integer.MAX_VALUE;
        boolean[] visited = new boolean[N + 1];
        Queue<Integer> q = new LinkedList<>();
        visited[findNodeIdx] = true;
        for (int next : revAdj.get(findNodeIdx)) {
            visited[next] = true;
            q.offer(next);
        }
        if (q.isEmpty()) {
            return NOT_BOSS;
        }
        while (!q.isEmpty()) {
            int curr = q.poll();
            int emp = nodes[curr].emp;
            res = Math.min(res, empAge[emp]);
            for (int next : revAdj.get(curr)) {
                if (visited[next]) {
                    continue;
                }
                visited[next] = true;
                q.offer(next);
            }
        }
        return res;
    }

    static class Node {
        int emp; // 직원 번호

        Node(int emp) {
            this.emp = emp;
        }
    }
}
