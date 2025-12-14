// 17845. [G5] 수강 과목.

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N, K;
    static Subject[] subjects;
    static int[] dp;
    static int ans = 0;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        dp = new int[N + 1];
        subjects = new Subject[K];
        for (int k = 0; k < K; k++) {
            st = new StringTokenizer(br.readLine());
            subjects[k] = new Subject(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
    }

    static void solve() {
        dp[0] = 1;
        for (Subject sub : subjects) {
            for (int t = N - sub.time; t >= 0; t--) {
                if (dp[t] == 0) continue;
                dp[t + sub.time] = Math.max(dp[t + sub.time], dp[t] + sub.importance);
            }
        }
        for (int t = N; t >= 0; t--) {
            ans = Math.max(ans, dp[t]);
        }
        ans -= 1;
    }

    static void output() throws IOException {
        bw.write(String.valueOf(ans));
        bw.flush();
    }

    static class Subject {
        int importance;
        int time;

        public Subject(int importance, int time) {
            this.importance = importance;
            this.time = time;
        }
    }
}
/*
 # 풀이
 - 0/1 knapsack 문제로 접근
 */
