// 24041. [G4] 성싶당 밀키트.

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    // N: 밀키트에 들어있는 재료의 수, G: 세균수의 합, K: 제외하는 재료의 허용되는 최대수
    static int N, G, K;
    static Ingredients[] ingredients;
    static int notImportantCount = 0;
    static long ans;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        ingredients = new Ingredients[N];
        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            long s = Long.parseLong(st.nextToken()); // 부패속도
            int l = Integer.parseInt(st.nextToken()); // 유통기한
            int o = Integer.parseInt(st.nextToken()); // 중요한 재료인지 (0: 중요, 1: 빼도 무방)
            notImportantCount += o;
            ingredients[n] = new Ingredients(s, l, o);
        }
    }

    static void solve() {
        long left = 1, right = Integer.MAX_VALUE - 1;
        while (left <= right) {
            long mid = (left + right) / 2;
            // 세균수를 최소인것부터 정렬하기
            Arrays.sort(ingredients, (i1, i2) -> {
                long val1 = i1.speed * Math.max(1, mid - i1.date);
                long val2 = i2.speed * Math.max(1, mid - i2.date);

                return Long.compare(val2, val1);
            });
            // 총 세균 수
            long totalGerms = calculateTotalGerms(mid);
            // 안심하고 먹기 가능
            if (totalGerms <= G) {
                left = mid + 1;
            }
            // 안심하고 먹기 불가능
            else {
                right = mid - 1;
            }
        }
        ans = right;
    }

    static void output() throws IOException {
        bw.write(String.valueOf(ans));
        bw.flush();
    }

    static long calculateTotalGerms(long day) {
        long totalGerms = 0;
        int k = K;
        for (Ingredients igd : ingredients) {
            long germs = igd.speed * Math.max(1, day - igd.date);
            if (!igd.important && k > 0) {
                k--;
                continue;
            }
            totalGerms += germs;
        }
        return totalGerms;
    }

    static class Ingredients {
        long speed; // 부패속도
        int date; // 유통기한
        boolean important;

        public Ingredients(long speed, int date, int important) {
            this.speed = speed;
            this.date = date;
            this.important = important == 0;
        }
    }
}
