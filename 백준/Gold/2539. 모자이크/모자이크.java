// 02539. [G4] 모자이크.

import java.io.*;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int R, C; // R: 행의 개수, C: 열의 개수
    static int N, M; // N: 색종이 개수, M: 잘못 칠해진 칸의 개수
    static TreeSet<Integer> treeSet = new TreeSet<>();
    static int limit = 0; // 높이 최대치 -> 색종이의 최소값이 될 예정
    static int ans = 0;
    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            // 1-based -> 0-based
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            limit = Math.max(limit, r);
            treeSet.add(c);
        }
    }

    static void solve() {
        int left = limit;
        int right = Math.max(limit, treeSet.last());
        while (left <= right) {
            int mid = (left + right) / 2;
            if (parametricSearch(mid)) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        ans = left;
    }

    static void output() throws IOException {
        bw.write(String.valueOf(ans));
        bw.flush();
    }

    /**
     * 제공된 사이즈로 제한된 개수로 성공하는지 체크
     * @param size 색종이 사이즈
     * @return 성공 여부
     */
    static boolean parametricSearch(int size) {
        int end = 0; // 종료 지점
        int cnt = 0; //
        for (int x : treeSet) {
            if (x > end) {
                end = x + size - 1;
                cnt++;
            }
            if (cnt > N) { // 제한된 개수를 초과
                return false;
            }
        }
        return true;
    }
}
