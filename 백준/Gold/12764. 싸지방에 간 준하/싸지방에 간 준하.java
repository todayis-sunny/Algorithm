// 12764. [G3] 싸지방에 간 준하

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N, ans = 0;
    static People[] peoples;
    static int[] use, endTime; // 사용 횟수, 종료 시간

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        peoples = new People[N];
        use = new int[N];
        endTime = new int[N];
        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            peoples[n] = new People(start, end);
        }
        // 사용 시간 오름차순 정렬
        Arrays.sort(peoples, Comparator.comparingInt(p -> p.start));
    }

    static void solve() {
        PL:
        for (People people : peoples) {
            CL:
            for (int i = 0; i < N; i++) {
                if (people.start >= endTime[i]) {
                    endTime[i] = people.end;
                    if (use[i]++ == 0) {
                        ans++;
                    }
                    continue PL;
                }
            }
        }
    }

    static void output() throws IOException {
        bw.write(String.valueOf(ans));
        bw.newLine();
        for (int i = 0; i < ans; i++) {
            bw.write(use[i] + " ");
        }
        bw.flush();
    }

    static class People {
        int start, end;

        public People(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
