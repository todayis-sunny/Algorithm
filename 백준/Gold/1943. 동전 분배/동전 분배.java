// 01943. [G2] 동전 분배.

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int N;

    public static void main(String[] args) throws IOException {

        for (int i = 0; i < 3; i++) {
            if (solve()) {
                sb.append("1\n");
            } else {
                sb.append("0\n");
            }
        }
        output();
    }

    static boolean solve() throws IOException {
        String line = br.readLine();
        if (line == null) return false;
        N = Integer.parseInt(line);

        Coin[] coins = new Coin[N];
        int total = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int money = Integer.parseInt(st.nextToken());
            int count = Integer.parseInt(st.nextToken());
            coins[i] = new Coin(money, count);
            total += money * count;
        }

        // 1. 총합이 홀수면 반으로 나눌 수 없음
        if (total % 2 != 0) return false;

        int target = total / 2;
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;

        // 2. DP 업데이트
        for (Coin coin : coins) {
            int money = coin.money;
            int count = coin.count;

            // 역순 탐색: 현재 동전이 이전 단계의 결과에만 영향을 주도록 함
            for (int k = target; k >= money; k--) {
                if (dp[k - money]) {
                    // 현재 금액(k)에서 해당 동전을 1개부터 count개까지 추가
                    for (int j = 0; j < count && (k + j * money) <= target; j++) {
                        if (dp[k + j * money]) continue;
                        dp[k + j * money] = true;
                    }
                }
                // 목표 금액 달성 시 즉시 반환
                if (dp[target]) return true;
            }
        }

        return dp[target];
    }

    static void output() throws IOException {
        bw.write(sb.toString());
        bw.flush();
    }
    
    static class Coin {
        int money, count;
        Coin(int money, int count) {
            this.money = money;
            this.count = count;
        }
    }
}
