// 10800. [G2] 컬러볼.

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N;
    static int[] answer; // 정답 배열
    static int[] psColor; // 컬러 누적합
    static List<Ball> balls = new ArrayList<>(); // 공 list

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        answer = new int[N];
        psColor = new int[N + 1]; // 1-based

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int color = Integer.parseInt(st.nextToken());
            int size = Integer.parseInt(st.nextToken());
            balls.add(new Ball(i, color, size));
        }
    }

    static void solve() {
        int sum = 0; // 최종 누적합
        balls.sort(Comparator.comparing(Ball::getSize).thenComparing(Ball::getColor)); // 사이즈 기준으로 정렬, 컬러기준으로 정렬
        Ball prev = new Ball(-1, -1, 0); // 더미 데이터
        int cnt = 1; // 셈 갯수
        for (Ball curr : balls) {
            answer[curr.idx] = sum - psColor[curr.color];
            if (curr.size == prev.size) { // 이전 공과 현재 공의 크기가 같다면
                if (curr.color == prev.color) { // 이전 공과 현재 공의 색상이 같다면
                    answer[curr.idx] = answer[prev.idx];
                } else {
                    answer[curr.idx] -= cnt * prev.size;
                }
                cnt++;
            } else {
                cnt = 1;
            }
            sum += curr.size;
            psColor[curr.color] += curr.size;
            prev = curr;

        }
    }

    static void output() throws IOException {
        for (int ans : answer) {
            bw.write(ans + "\n");
        }
        bw.flush();
    }

    static class Ball {
        int idx;
        int color;
        int size;

        public Ball(int idx, int color, int size) {
            this.idx = idx;
            this.color = color;
            this.size = size;
        }

        public int getColor() {
            return color;
        }

        public int getSize() {
            return size;
        }
    }
}
