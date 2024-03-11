// 01922. [G4] 네트워크 연결.
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N, M, a, b, c, answer;
    static int[] parent;
    static ArrayList<Edge> edges = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        answer = 0;
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        parent = new int[N+1];

        // 부모 테이블상에서, 부모를 자기 자신으로 초기화.
        for (int n = 1; n <= N; n++) {
            parent[n] = n;
        }

        // 모든 간선에 대한 정보를 입력 받기.
        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            edges.add(new Edge(a, b, c));
        }
        // 간선을 비용순으로 정렬.
        Collections.sort(edges);

        // 간선을 하나씩 확인하며
        for (int m = 0; m < M; m++) {
            a = edges.get(m).nodeA;
            b = edges.get(m).nodeB;
            c = edges.get(m).cost;
            // 사이클이 발생하지 않는 경우에만 집합에 포함.
            if (findParent(a) != findParent(b)) {
                unionParent(a, b);
                answer += c;
            }
        }
        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }

    // 특정 원소가 속한 집합을 찾기.
    public static int findParent(int x) {
        // 루트 노드가 아니라면, 루트 노드를 찾을 때까지 재귀적으로 호출.
        if (x == parent[x]) {
            return x;
        }
        return parent[x] = findParent(parent[x]);
    }

    // 두 원소가 속한 집합을 합치기
    public static void unionParent(int a, int b) {
        a = findParent(a);
        b = findParent(b);
        if (a < b) {
            parent[b] = a;
        } else {
            parent[a] = b;
        }
    }
    static class Edge implements Comparable<Edge> {

        int nodeA;
        int nodeB;
        int cost;

        public Edge(int nodeA, int nodeB, int cost) {
            this.nodeA = nodeA;
            this.nodeB = nodeB;
            this.cost = cost;
        }
        @Override
        public int compareTo(Edge other) {
            if (this.cost < other.cost) {
                return -1;
            }
            return 1;
        }
    }

}