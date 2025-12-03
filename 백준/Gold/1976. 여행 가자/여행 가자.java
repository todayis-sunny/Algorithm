// 01976. [G4] 여행 가자.

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N, M;
    static int[] parent; // 음수 : 부모이자 랭크의 높이 표현, 양수 : 해당 부모를 기입
    static int[] plan;
    static boolean possible = true;
    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        // 여행가능 관계 입력
        parent = new int[N + 1];
        plan = new int[M];
        Arrays.fill(parent, -1);
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                String str = st.nextToken();
                // i >= j인 경우 스킵
                if (i >= j) continue;
                // 0인 경우 스킵
                if (str.equals("0")) continue;
                unionParent(i, j);
            }
        }
        // 여행 계획 입력
        st = new StringTokenizer(br.readLine());
        for (int m = 0; m < M; m++) {
            plan[m] = Integer.parseInt(st.nextToken());
        }
    }

    static void solve() {
        for (int i = 0; i < M - 1; i++) {
            // 새로 합쳐지면 집합이 아님
            if (unionParent(plan[i], plan[i + 1])) {
                possible = false;
                return;
            }
        }
    }

    static void output() throws IOException {
        bw.write(possible ? "YES" : "NO");
        bw.flush();
    }

    static boolean unionParent(int a, int b) {
        a = findParent(a);
        b = findParent(b);
        // 이미 둘이 집합인 경우
        if (a == b) return false;
        // b의 랭크가 더 큰 경우 (음수) -> a의 랭크가 더 크게 서로 스왑
        if (parent[b] < parent[a]) {
            int c = a;
            a = b;
            b = c;
        }
        // 두 랭크가 같은 경우
        else if (parent[a] == parent[b]) {
            parent[a]--;
        }
        parent[b] = a;
        return true;
    }

    static int findParent(int x) {
        if (parent[x] < 0) {
            return x;
        }
        return parent[x] = findParent(parent[x]);
    }
}
/*
 # 접근 방법
 도시하나를 찍고 해당 도시로부터 여행을 희망하는 도시에 다 방문할수 있는가 체크한다.

 # 풀이 방법
 유니온 파인드로 접근하여 동일한지 체크한다.
 */
