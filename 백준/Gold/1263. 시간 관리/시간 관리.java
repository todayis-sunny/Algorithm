// 01263. [G5] 시간 관리.

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N;
    static Work[] works;
    static int result = 1_000_001;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        works = new Work[N];
        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            works[n] = new Work(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
    }

    static void solve() {
        Arrays.sort(works, (w1, w2) -> w2.deadline - w1.deadline);
        for (int i = 0; i < N; i++) {
            // 현재시간이 데드라인보다 미래라면
            if (result > works[i].deadline) {
                // 현재시간을 데드라인으로 수정
                result = works[i].deadline;
            }
            result -= works[i].time;
            // 음수가 되는 순간 종료
            if (result < 0) {
                result = -1;
                return;
            }
        }
    }

    static void output() throws IOException {
        bw.write(String.valueOf(result));
        bw.flush();
    }

    static class Work {
        int time;
        int deadline;

        public Work(int time, int deadline) {
            this.time = time;
            this.deadline = deadline;
        }
    }
}
