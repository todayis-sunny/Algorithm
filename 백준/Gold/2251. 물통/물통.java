// 02251. [G4] 물통.

import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int A, B, C;
    static Set<Integer> set = new HashSet<>();
    // visited[b][c] : b용량, c용량 확인 여부
    static boolean[][] visited = new boolean[201][201];

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        // 물통의 크기 입력
        st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
    }

    static void solve() {
        dfs(0, 0, C);
    }

    static void output() throws IOException {
        List<Integer> list = new ArrayList<>(set);
        Collections.sort(list);
        for (int w : list) {
            bw.write(w + " ");
        }
        bw.flush();
    }

    static void dfs(int a, int b, int c) {
        // 첫 번째 물통이 비어 있을 때, 세번 째 물통의 용량
        if (a == 0) {
            set.add(c);
        }

        // 부울수 있는 물의 양 : 한 물통이 빌때까지, 한 물통이 꽉찰때까지
        int k;
        // 새로운 각 물통의 양
        int na, nb, nc;
        // 첫 번째 물통을 사용
        if (a != 0) {
            // 두 번째 물통에 붓기
            k = Math.min(a, B - b);
            na = a - k;
            nb = b + k;
            if (!visited[nb][c]) {
                visited[nb][c] = true;
                dfs(na, nb, c);
            }
            // 세 번째 물통에 붓기
            k = Math.min(a, C - c);
            na = a - k;
            nc = c + k;
            if (!visited[b][nc]) {
                visited[b][nc] = true;
                dfs(na, b, nc);
            }
            // 세 번째 물통에 붓기
        }
        // 두 번째 물통을 사용
        if (b != 0) {
            // 첫 번째 물통에 붓기
            k = Math.min(b, A - a);
            na = a + k;
            nb = b - k;
            if (!visited[nb][c]) {
                visited[nb][c] = true;
                dfs(na, nb, c);
            }
            // 세 번째 물통에 붓기
            k = Math.min(b, C - c);
            nb = b - k;
            nc = c + k;
            if (!visited[nb][nc]) {
                visited[nb][nc] = true;
                dfs(a, nb, nc);
            }
        }
        // 세 번째 물통을 사용
        if (c != 0) {
            // 첫 번째 물통에 붓기
            k = Math.min(c, A - a);
            na = a + k;
            nc = c - k;
            if (!visited[b][nc]) {
                visited[b][nc] = true;
                dfs(na, b, nc);
            }
            // 두 번째 물통에 붓기
            k = Math.min(c, B - b);
            nb = b + k;
            nc = c - k;
            if (!visited[nb][nc]) {
                visited[nb][nc] = true;
                dfs(a, nb, nc);
            }
        }
    }
}
