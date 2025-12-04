// 01222. [G2] 홍준 프로그래밍 대회.

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static final int LIMIT = 2_000_000;
    static int N;
    static int[] count = new int[LIMIT + 1];
    static long result = 0;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }


    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        // 들어오는 수를 그대로 체크
        for (int n = 0; n < N; n++) {
            count[Integer.parseInt(st.nextToken())]++;
        }
    }

    static void solve() {
        // 사람수 단위를 1부터 늘리기
        for (int peo = 1; peo <= LIMIT; peo++) {
            // 결성되는 팀의 수
            long teamCnt = 0;
            // 단위의 배수만큼 체크
            for (int mul = peo; mul <= LIMIT; mul += peo) {
                teamCnt += count[mul];
            }
            // 출전 팀이 2팀 미만이면 스킵
            if (teamCnt < 2) continue;
            result = Math.max(result, peo * teamCnt);
        }
    }

    static void output() throws IOException {
        bw.write(String.valueOf(result));
        bw.flush();
    }
}

