// 02170. [G5] 선 긋기.

import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N, ans;
    static List<Line> lines;
    static PriorityQueue<Line> pq;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        bw.write(String.valueOf(ans));
        bw.flush();
    }

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        lines = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            lines.add(new Line(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        lines.sort(Comparator.comparingInt(Line::getX).thenComparingInt(Line::getReversedY));
    }

    static void solve() {
        ans = 0;
        int left = -1_000_000_001;
        int right = -1_000_000_001;
        for (Line line : lines) {
            if (line.x > right) {
                ans += right - left;
                left = line.x;
                right = line.y;
            } else if (line.x == left) { // 이미 시작점 부터 긴거를 앞으로 오게 했으니 무시.
                continue;
            } else if (line.x > left && line.y <= right){ // 새로운 시작점이지만 길이가 더 짧거나 같으면 무시.
                continue;
            } else { // 이외의 것들은 길이가 늘어남.
                right = line.y;
            }
        }
        ans += right - left;
    }

    static class Line {
        int x, y;

        public Line(int x, int y) {
            this.x = x;
            this.y = y;
        }
        public int getX() {
            return x;
        }
        // y는 내림차순 정렬
        public int getReversedY() {
            return -y;
        }
    }
}
