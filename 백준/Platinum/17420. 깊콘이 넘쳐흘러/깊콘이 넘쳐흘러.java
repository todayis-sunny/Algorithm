// 17420. [P5] 깊콘이 넘쳐흘러.

import java.io.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N;
    static Gifticon[] gifticonList;
    static long ans = 0L;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        gifticonList = new Gifticon[N];
        st = new StringTokenizer(br.readLine());
        for (int n = 0; n < N; n++) {
            int rest = Integer.parseInt(st.nextToken());
            gifticonList[n] = new Gifticon(rest, 0);
        }
        st = new StringTokenizer(br.readLine());
        for (int n = 0; n < N; n++) {
            gifticonList[n].plan = Integer.parseInt(st.nextToken());
        }
    }

    static void solve() {
        // 계획이 빠른순, 남은기간이 적은순
        Arrays.sort(gifticonList, Comparator
                .comparingInt((Gifticon g) -> g.plan)
                .thenComparingInt((Gifticon g) -> g.rest)
        );
        int restMax = gifticonList[0].rest; // 현재그룹까지의 최대기한
        int planMax = gifticonList[0].plan; // 이전그룹에서의 최대기한
        for (int i = 0; i < N; i++) {
            // 현재 기프티콘의 남은기간이 사용해야할 기프티콘의 남은기간보다 더 적은 경우
            if (gifticonList[i].rest < planMax) {
                // 연장처리
                int cnt = ((planMax - gifticonList[i].rest) + 29) / 30; // 올림처리
                gifticonList[i].rest += cnt * 30;
                ans += cnt;
            }
            // 현재그룹 내에서의 최대기한 갱신
            restMax = Math.max(restMax, gifticonList[i].rest);
            // 다음 기프티콘과 계획일이 다른 경우 (그룹이 변경되는 경우)
            if (i + 1 < N && gifticonList[i].plan != gifticonList[i + 1].plan) {
                planMax = Math.max(restMax, gifticonList[i + 1].plan);
            }
        }
    }

    static void output() throws IOException {
        bw.write(String.valueOf(ans));
        bw.flush();
    }

    static class Gifticon {
        int rest;
        int plan;

        public Gifticon(int rest, int plan) {
            this.rest = rest;
            this.plan = plan;
        }
    }
}
