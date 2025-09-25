// 14267. [G4] 회사 문화 1.

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N, M; // N: 직원 수, M: 칭찬의 횟수
    static List<List<Integer>> subordinate = new ArrayList<>(); // 부하 직원 1-based
    static int[] compliments; // 칭찬의 수치 1-based
    static long[] total; // 칭찬의 수치 합 1-based

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        // 1. N, M 입력
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        // 2. 직속상사 입력
        for (int i = 0; i <= N; i++) {
            subordinate.add(new ArrayList<>());
        }
        st = new StringTokenizer(br.readLine());
        st.nextToken(); // -1 버리기
        for (int n = 2; n <= N; n++) {
            int supervisor = Integer.parseInt(st.nextToken());
            subordinate.get(supervisor).add(n);
        }
        // 3. 칭찬 입력
        compliments = new int[N + 1];
        total = new long[N + 1];
        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int emp = Integer.parseInt(st.nextToken());
            int val = Integer.parseInt(st.nextToken());
            compliments[emp] += val;
        }
    }

    static void solve() {
        for (int i = 1; i <= N; i++) {
            if (compliments[i] == 0) continue;
            dfs(i, compliments[i]);
        }
    }

    static void output() throws IOException {
        for (int i = 1; i <= N; i++) {
            bw.write(total[i] + " ");
            bw.flush();
        }
    }

    static void dfs(int emp, int val) {
        total[emp] = val;
        for (int next : subordinate.get(emp)) {
            dfs(next, val + compliments[next]);
            compliments[next] = 0;
        }
    }
}
