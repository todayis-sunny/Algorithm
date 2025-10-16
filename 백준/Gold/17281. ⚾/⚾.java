// 17281. [G4] ⚾.

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static final int AT1 = 1, AT2 = 2, AT3 = 3, HR = 4, OUT = 0;
    static int N;
    static int ans = 0;
    static int[][] player;
    static int[] lineUp = new int[10];

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        player = new int[N + 1][10];
        lineUp[4] = 1;
        // 선수별 성적 입력
        for (int n = 1; n <= N; n++) {
            st = new StringTokenizer(br.readLine());
            for (int p = 1; p <= 9; p++) {
                player[n][p] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static void solve() {
        dfs(2);
    }

    static void output() throws IOException {
        bw.write(String.valueOf(ans));
        bw.flush();
    }

    static void dfs(int p) {
        // 마지막 선수까지 배치하면 종료
        if (p == 10) {
            simulation();
            return;
        }
        ;

        for (int e = 1; e <= 9; e++) {
            // 4번 타자는 항상 1번이므로 스킵
            if (e == 4) continue;
            // 타자가 배정되어 있으면 스킵
            if (lineUp[e] != 0) continue;
            lineUp[e] = p; // 타자 배정
            dfs(p + 1);
            lineUp[e] = 0; // 타자 미배정
        }
    }

    static void simulation() {
        int score = 0;
        int k = 0; // 0 - based -> 1 - based (1번타자 -> 9번타자)
        for (int e = 1; e <= N; e++) {
            int outCount = 0; // 이닝별 아웃 카운트 초기화
            int base = 0; // 1, 2, 3 루 (0 - based) 이닝별 베이스 초기화
            while (outCount < 3) { // 한 이닝에 아웃 카운트 체크
                int play = player[e][lineUp[(k % 9) + 1]]; // 0~8 이므로 +1 통해서 1~9
                switch (play) {
                    case AT1:
                        base = (base << 1) | 1;
                        break;
                    case AT2:
                        base = (base << 2) | 2;
                        break;
                    case AT3:
                        base = (base << 3) | 4;
                        break;
                    case HR:
                        base = base << 3;
                        score++; // 타자 1 득점
                        break;
                    case OUT:
                        outCount++;
                        break;
                }
                int referee = base & 0b111000; // 점수 집계 준비
                score += Integer.bitCount(referee);
                base = base & 0b000111; // (점수 득점하고)진출한 선수들 정리
                k++;
            }
        }
        ans = Math.max(ans, score);
    }

}
