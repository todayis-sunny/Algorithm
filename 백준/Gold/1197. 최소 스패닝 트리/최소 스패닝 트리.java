// 01197. [G4] 최소 스패닝 트리.
import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int V, E, A, B, C, answer;
    static int[] parent;
    static ArrayList<Edge> edges = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        answer = 0;
        st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        parent = new int[V+1];
        for (int v = 1; v <= V; v++) {
            parent[v] = v;
        }
        for (int e = 0; e < E; e++) {
            st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            edges.add(new Edge(A, B, C));
        }
        edges.sort(Comparator.comparing(Edge::getWeight));
        for (int e = 0; e < E; e++) {
            A = edges.get(e).nodeA;
            B = edges.get(e).nodeB;
            C = edges.get(e).weight;
            if (findParent(A) != findParent(B)) {
                unionParent(A, B);
                answer += C;
            }
        }
        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }
    static int findParent(int x) {
        if (x == parent[x]) {
            return x;
        }
        return parent[x] = findParent(parent[x]);
    }

    static void unionParent(int a, int b) {
        a = findParent(a);
        b = findParent(b);
        if (a < b) {
            parent[b] = a;
        } else {
            parent[a] = b;
        }
    }

    static class Edge {
        int nodeA;
        int nodeB;
        int weight;

        public Edge(int nodeA, int nodeB, int weight) {
            this.nodeA = nodeA;
            this.nodeB = nodeB;
            this.weight = weight;
        }

        public int getWeight() {
            return weight;
        }
    }

}
