import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static final int WIN = 1, UNKNOW = 0, LOSE = -1;
    static int N, M, X;
    static List<List<Integer>> winList = new ArrayList<>();
    static List<List<Integer>> loseList = new ArrayList<>();
    static int high, low;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        for (int n = 0; n <= N; n++) {
            winList.add(new ArrayList<>());
            loseList.add(new ArrayList<>());
        }

        // 대소관계 입력
        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            winList.get(b).add(a); // b에게 이긴 사람
            loseList.get(a).add(b); // a에게 진 사람
        }
    }

    static void solve() {
        // 상대적으로 이긴 경우 최저 등수가 점점 높아짐
        high = 1 + bfs(LOSE);
        // 상대적으로 진 경우 최고 등수가 점점 떨어짐
        low = N - bfs(WIN);
    }

    static void output() throws IOException {
        bw.write(high + " " + low);
        bw.flush();
    }

    static int bfs(int state) {
        int cnt = 0;

        boolean[] visited = new boolean[N + 1];
        Queue<Integer> queue = new LinkedList<>();
        List<List<Integer>> list;
        // 몇 명에게 이겼는지 확인
        if (state == WIN) {
            list = loseList;
        }
        // 몇 명에게 졌는지 확인
        else {
            list = winList;
        }
        queue.offer(X);
        visited[X] = true;
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            for (int next: list.get(curr)) {
                // 이미 방문 했으면 스킵
                if (visited[next]) continue;
                // 방문 안했으면 더 탐색 및 인원수 세기
                queue.offer(next);
                visited[next] = true;
                cnt++;
            }
        }
        return cnt;
    }

}
/*
 # 접근 방법
 - 대상 x로 부터 y(이긴사람 or 진사람)를 리스트에 넣기
 - bfs로 접근하기

 # 풀이 방법
 - bfs로 상대적으로 이긴사람과 상대적으로 진사람의 수를 파악
 */


/*
 풀이날짜 2025. 12. 04
 소요시간 00h 00m
 */
