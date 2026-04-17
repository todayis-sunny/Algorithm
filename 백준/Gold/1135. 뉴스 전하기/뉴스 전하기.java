// 01135. [G2] 뉴스 전하기.

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N, ans;
    static List<Integer>[] employees;
    static int[] dp;
    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        employees = new ArrayList[N];
        dp = new int[N];
        for (int i = 0; i < N; i++) {
            employees[i] = new ArrayList<>();
            int superior = Integer.parseInt(st.nextToken());
            if (superior == -1) continue;
            employees[superior].add(i);
        }
    }

    static void solve() {
        ans = dfs(0);
    }

    static void output() throws IOException {
        bw.write(String.valueOf(ans));
        bw.flush();
    }

    static int dfs(int emp) {
        for (int next : employees[emp]) {
            dp[next] = 1 + dfs(next);
        }
        employees[emp].sort((e1, e2) -> dp[e2] - dp[e1]);
        int res = 0;
        for (int i = 0; i < employees[emp].size(); i++) {
            int next = employees[emp].get(i);
            dp[next] += i;
            res = Math.max(res, dp[next]);
        }
        return res;
    }
}
