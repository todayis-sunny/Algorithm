// 01106. [G4] 호텔.

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static final int INF = Integer.MAX_VALUE;
    static int[] dp; // dp[i] = k : i명의 고객을 모을 때 필요한 최소 비용 k
    static int C, N; // C: 최소 필요 고객, N: 도시의 개수
    static int limit;
    static Publicity[] publicises;
    static int ans = INF;


    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        limit = C + 100;
        dp = new int[limit + 1];
        publicises = new Publicity[N];
        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            int cost = Integer.parseInt(st.nextToken());
            int people = Integer.parseInt(st.nextToken());
            publicises[n] = new Publicity(cost, people);
        }
    }

    static void solve() {
        // 0. dp 초기화
        Arrays.fill(dp, INF);
        dp[0] = 0;
        // 1. dp 채우기
        for (Publicity pub : publicises) {
            // 뒤에서 부터 채우기
            for (int i = limit; i >= pub.people; i--) {
                // 배수로 체크
                for (int j = 1; j * pub.people <= i; j++) {
                    if (dp[i - pub.people * j] == INF) continue;
                    dp[i] = Math.min(dp[i], dp[i - pub.people * j] + pub.cost * j);
                }
            }
        }
    }

    static void output() throws IOException {
        for (int i = C; i <= limit; i++) {
            ans = Math.min(ans, dp[i]);
        }
        bw.write(String.valueOf(ans));
        bw.flush();
    }

    static class Publicity {
        int cost, people;

        public Publicity(int cost, int people) {
            this.cost = cost;
            this.people = people;
        }
    }
}
