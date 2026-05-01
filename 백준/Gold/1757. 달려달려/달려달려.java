// 01757. [G4] 달려달려.

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N, M;
    static int[] prefixSum; // 1-based [i] : i분까지의 누적합(누적거리)
    static int[] dp; // 1-based, [i] : i분에 지침 지수가 정확이 0인 상태에서 가질수 있는 최대 이동 거리

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        prefixSum = new int[N + 1];
        dp = new int[N + 1];
        for (int n = 1; n <= N; n++) {
            prefixSum[n] = prefixSum[n - 1] + Integer.parseInt(br.readLine());
        }
    }


    static void solve() {
        for (int i = 0; i < N; i++) {
            // 최대 달릴수 있는시간  min(지칠때까지, 현재남은시간/2)
            int limit = Math.min(M, (N - i) / 2);
            // 현재 i분일때 j분만큼 달리기
            for (int j = 1; j <= limit; j++) {
                // j분을 달리면 j분을 휴식, k = 2j만큼 소요
                int k = j * 2; 
                // i+1분 ~ j분까지 달리기
                dp[i + k] = Math.max(dp[i + k], dp[i] + (prefixSum[i + j] - prefixSum[i]));
            }
            // 그냥 휴식한번 하기
            dp[i + 1] = Math.max(dp[i], dp[i + 1]);
        }
    }

    static void output() throws IOException {
        bw.write(String.valueOf(dp[N]));
        bw.flush();
    }
}

/*
 # 풀이
 - 누적합
 - 1차원 DP

 */
