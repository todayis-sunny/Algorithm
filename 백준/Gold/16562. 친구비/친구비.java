// 16562. [G2] 친구비.

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    // N: 학생 수, M: 친구관계 수, K: 가지고 있는 돈
    static int N, M, K;
    static int[] parent; // 유니온 파인드
    static int[] cost; // 친구비
    static int ans = 0;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        // 1-based
        parent = new int[N + 1];
        cost = new int[N + 1];
        // 대장친구 초기화
        for (int n = 1; n <= N; n++) {
            parent[n] = n;
        }
        // 친구비 입력
        st = new StringTokenizer(br.readLine());
        for (int n = 1; n <= N; n++) {
            cost[n] = Integer.parseInt(st.nextToken());
        }
        // 친구 관계 입력
        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            unionParent(a, b);
        }
    }

    static void solve() {
        // 친구비 지출하여 친구 사귀기
        for (int n = 1; n <= N; n++) {
            int friend = findParent(n); // 친구그룹의 대장친구 탐색
            ans += cost[friend]; // 정답에 비용 추가
            K -= cost[friend]; // 가진 돈에서 비용 지출
            cost[friend] = 0; // 이미 사귄 친구그룹이므로 0으로 초기화
            if (K < 0) { // 비용이 마이너스가 되는 순간 탈출
                ans = -1; // 정답을 불가능으로 초기화
                break;
            }
        }
    }

    static void output() throws IOException {
        if (ans == -1) { // 모든 친구를 사귀지 못한 경우
            bw.write("Oh no");
        } else { // 모든 친구를 사귄 경우
            bw.write(String.valueOf(ans));
        }
        bw.flush();
    }

    static void unionParent(int a, int b) {
        a = findParent(a);
        b = findParent(b);
        if (a < b) {
            parent[b] = a;
            setMinCost(a, b); // 비용조정 추가
        } else {
            parent[a] = b;
            setMinCost(b, a); // 비용조정 추가
        }

    }

    static int findParent(int x) {
        if (x == parent[x]) {
            return x;
        }
        return parent[x] = findParent(parent[x]);
    }

    static void setMinCost(int parent, int child) {
        cost[parent] = Math.min(cost[parent], cost[child]);
    }
}
