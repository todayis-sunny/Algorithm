// 11377. [P3] 열혈강호 3.

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N, M, K; // N: 직원의 수, M: 해야할 일의 수, K: 최대 2개 담당할 수 있는 직원의 수
    static List<List<Integer>> data;
    static int[] task;
    static boolean[] check;
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
        task = new int[M + 1];
        check = new boolean[M + 1];
        data = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            data.add(new ArrayList<>());
        }
        for (int i = 1; i <= N; i++) { // i번 직원이 어떤 업무들을 수행할 수 있는지
            st = new StringTokenizer(br.readLine());
            int cnt = Integer.parseInt(st.nextToken());
            for (int c = 0; c < cnt; c++) {
                data.get(i).add(Integer.parseInt(st.nextToken()));
            }
        }
    }

    static void solve() {
        for (int emp = 1; emp <= 2 * N; emp++) {
            Arrays.fill(check, false);
            if (emp <= N) {
                if (dfs(emp)) {
                    ans++;
                }
            } else {
                if (dfs(emp - N) && K > 0) {
                    K--;
                    ans++;
                }
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
            // 아직 해당 일이 배정되지 않았거나, 해당 업무를 수행중인 다른 직원이 업무를 수행가능하다면,
            if (task[next] == 0 || dfs(task[next])) {
                task[next] = curr;
                return true;
            }
        }
        return false;
    }
}
