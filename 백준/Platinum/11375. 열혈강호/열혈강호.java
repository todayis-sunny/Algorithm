// 11375. [P4] 열혈강호.

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N, M; // N: 직원의 수, M: 해야할 일의 수
    static List<List<Integer>> data;
    static int[] task;
    static boolean[] check;
    static int ans;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        task = new int[M + 1];
        check = new boolean[M + 1];
        data = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            data.add(new ArrayList<>());
        }
        for (int i = 1; i <= N; i++) { // i번 직원이 어떤 업무들을 수행할 수 있는지 데이터 초기화
            st = new StringTokenizer(br.readLine());
            int cnt = Integer.parseInt(st.nextToken());
            for (int c = 0; c < cnt ; c++) {
                data.get(i).add(Integer.parseInt(st.nextToken()));
            }
        }
        ans = 0;
    }

    static void solve() {
        for (int emp = 1; emp <= N; emp++) {
            Arrays.fill(check, false);
            if (dfs(emp)) {
                ans++;
            }
        }
    }

    static void output() throws IOException {
        bw.write(String.valueOf(ans));
        bw.flush();
    }

    static boolean dfs(int curr) {
        for (int next : data.get(curr)) {
            if (check[next]) { // 이미 업무가 수행중인 경우는 무시
                continue;
            }
            check[next] = true;
            if (task[next] == 0 || dfs(task[next])) { // 아직 해당 일이 배정되지 않았거나, 해당 업무를 수행중인 다른 직원이 업무를 수행가능하다면
                task[next] = curr;
                return true;
            }
        }
        return false;
    }
}
