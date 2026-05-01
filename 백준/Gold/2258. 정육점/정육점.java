// 02258. [G3] 정육점.

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N, M;
    static Meet[] meets;
    static long ans = Long.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        meets = new Meet[N];
        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            meets[n] = new Meet(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
    }

    static void solve() {
        // 고기를 정렬 가격 오름차순 (동일 가격 무게 내림차순)
        Arrays.sort(meets, (m1, m2) -> {
            if (m1.price == m2.price) {
                return m2.weight - m1.weight;
            }
            return m1.price - m2.price;
        });
        int currPrice = 0;
        int totalWeight = 0, totalPrice = 0;
        for (Meet meet: meets) {
            // 가격이 더 높은경우
            if (meet.price > currPrice) {
                totalPrice = currPrice = meet.price;
            }
            // 가격이 동일한경우
            else if (meet.price == currPrice) {
                totalPrice += meet.price;
            }
            totalWeight += meet.weight;
            // 목표 무게의 구매 이상인 경우
            if (totalWeight >= M) {
                ans = Math.min(ans, totalPrice);
            }
        }
    }

    static void output() throws IOException {
        if (ans == Long.MAX_VALUE) bw.write("-1");
        else bw.write(String.valueOf(ans));
        bw.flush();
    }

    static class Meet {
        int weight;
        int price;

        public Meet(int weight, int price) {
            this.weight = weight;
            this.price = price;
        }

    }
}
