// 02180. [P5] 소방서의 고민.

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N;
    static long time = 0;
    static Task[] tasks;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        tasks = new Task[N];
        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            tasks[n] = new Task(a, b);
        }
    }

    static void solve() {
        Arrays.sort(tasks, (t1, t2) -> {

            // 1. t1.a가 0일 때
            if (t1.a == 0) {
                // t2.a도 0이면 (0,x) vs (0,y)
                if (t2.a == 0) {
                    // C++의 3번째, 4번째 규칙에 따라 b를 비교해야 함
                    if (t1.b == 0 && t2.b == 0) {
                        return 0; // (0,0) vs (0,0) -> 0
                    }
                    return 0; // t1 == t2
                }
                // t1.a만 0이면 t1이 더 큼 (C++의 return false)
                else {
                    return 1; // t1 > t2 (뒤로 보냄)
                }
            }

            // 2. t1.a는 0이 아니고, t2.a만 0일 때
            if (t2.a == 0) {
                return -1; // t1 < t2 (앞으로 보냄)
            }

            // --- 여기서부터는 t1.a와 t2.a가 둘 다 0이 아님 ---

            // 3. 둘 다 0이 아니고, b값이 둘 다 0일 때
            if (t1.b == 0 && t2.b == 0) {
                return Integer.compare(t1.a, t2.a);
            }

            // 4. 메인 비교 로직
            else {
                long val1 = (long) t1.b * t2.a;
                long val2 = (long) t1.a * t2.b;
                return Long.compare(val1, val2);
            }
        });

        for (Task task : tasks) {
            long nextTime = time + time * task.a + (long) task.b;
            time = nextTime % 40_000;
        }
    }

    static void output() throws IOException {
        bw.write(String.valueOf(time));
        bw.flush();
    }

    static class Task {
        int a, b;

        public Task(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }
}
