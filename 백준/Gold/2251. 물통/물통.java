// 02251. [G4] 물통.

import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int[] limit = new int[3];
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
        limit[0] = Integer.parseInt(st.nextToken());
        limit[1] = Integer.parseInt(st.nextToken());
        limit[2] = Integer.parseInt(st.nextToken());
    }

    static void solve() {
        dfs(new int[]{0, 0, limit[2]});
    }

    static void output() throws IOException {
        List<Integer> list = new ArrayList<>(set);
        Collections.sort(list);
        for (int w : list) {
            bw.write(w + " ");
        }
        bw.flush();
    }

    static void dfs(int[] state) {
        // 첫 번째 물통이 비어 있을 때, 세번 째 물통의 용량
        if (state[0] == 0) {
            set.add(state[2]);
        }

        // 부울수 있는 물의 양 : 한 물통이 빌때까지, 한 물통이 꽉찰때까지
        int possible;
        for (int i = 0; i < 3; i++) {
            // 물이 없으면 스킵
            if (state[i] == 0) continue;
            for (int j = 0; j < 3; j++) {
                // 같은 물통을 가리키면 스킵
                if (i == j) continue;
                possible = Math.min(state[i], limit[j] - state[j]);
                // 새로운 상태 만들기
                int[] newState = new int[3];
                for (int k = 0; k < 3; k++) {
                    // 물 감소
                    if (k == i) {
                        newState[k] = state[k] - possible;
                    }
                    // 물 증가
                    else if (k == j) {
                        newState[k] = state[k] + possible;
                    } else {
                        newState[k] = state[k];
                    }
                }
                if (!visited[newState[1]][newState[2]]) {
                    visited[newState[1]][newState[2]] = true;
                    dfs(newState);
                }
            }
        }
    }
}
