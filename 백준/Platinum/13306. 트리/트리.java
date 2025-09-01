// 13306. [P5] 트리.

import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    // N: 정점의 개수, Q: 질의의 개수
    static int N, Q;
    static final int DELETE = 0, QUESTION = 1;
    static int[] init, parent; //
    static Query[] queries; // 입력 쿼리
    static Stack<Boolean> answer = new Stack<>();

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        // N, Q 입력
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        init = new int[N + 1]; // 1-based
        parent = new int[N + 1]; // 1-based
        init[1] = 1; // 1은 리프 노드
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }
        // 부모 정점 표기
        for (int c = 2; c <= N; c++) {
            int p = Integer.parseInt(br.readLine());
            init[c] = p;
        }
        // (N - 1) + Q 개의 형태 대입
        queries = new Query[N - 1 + Q];
        for (int i = 0; i < N - 1 + Q; i++) {
            st = new StringTokenizer(br.readLine());
            int key = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            if (key == DELETE) {
                queries[i] = new Query(key, a);
            } else {
                int b = Integer.parseInt(st.nextToken());
                queries[i] = new Query(key, a, b);
            }
        }

    }

    static void solve() {
        for (int i = N - 1 + Q - 1; i >= 0; i--) {
            Query query = queries[i];
            if (query.key == DELETE) {
                int c = query.a;
                int p = find(init[c]);
                union(p, c);
            } else {
                int a = query.a;
                int b = query.b;
                if (find(a) == find(b)) {
                    answer.push(true);
                } else {
                    answer.push(false);
                }
            }
        }
    }

    static void output() throws IOException {
        while (!answer.isEmpty()) {
            if (answer.pop()) {
                bw.write("YES\n");
            } else {
                bw.write("NO\n");
            }
        }
        bw.flush();
    }

    static void union(int p, int c) {
        parent[c] = p;
    }
    static int find(int x){
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    static class Query {
        int key, a, b;
        Query(int key, int a, int b) {
            this.key = key;
            this.a = a;
            this.b = b;
        }
        Query(int key, int a) {
            this.key = key;
            this.a = a;
        }
    }
}
